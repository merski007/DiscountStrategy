package discountstrategy;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.text.NumberFormat;

/**
 *
 * @author mjmersenski
 */
public class Receipt {

    private Customer customer;
    private LineItem[] lineItemArray;
    private ReceiptDataAccessStrategy db;
    private static int receiptNumber = 0;
    private Date receiptDate;
    private ReceiptOutputStrategy output;
    private String dateFormat = "M/d/yyyy hh:mm a";

    public Receipt(String custId, ReceiptDataAccessStrategy db) {
        setDb(db);
        this.customer = findCustomer(custId);
        ++receiptNumber;
        receiptDate = new Date();
        lineItemArray = new LineItem[0];
    }

    private final Customer findCustomer(String custId) {
        return db.findCustomer(custId);
    }

    public final ReceiptDataAccessStrategy getDb() {
        return db;
    }

    public final void setDb(ReceiptDataAccessStrategy db) {
        if (db == null) {
            throw new IllegalArgumentException("db cannot be blank");
        }
        this.db = db;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LineItem[] getLineItemArray() {
        return lineItemArray;
    }

    public void setLineItemArray(LineItem[] lineItemArray) {
        this.lineItemArray = lineItemArray;
    }

    public static int getReceiptNumber() {
        return receiptNumber;
    }

    public static void setReceiptNumber(int receiptNumber) {
        Receipt.receiptNumber = receiptNumber;
    }

    public Date getReceiptDate() {
        return receiptDate;
    }

    public void setReceiptDate(Date receiptDate) {
        this.receiptDate = receiptDate;
    }

    public ReceiptOutputStrategy getOutput() {
        return output;
    }

    public void setOutput(ReceiptOutputStrategy output) {
        this.output = output;
    }

    public String getDateFormat() {
        return dateFormat;
    }

    public void setDateFormat(String dateFormat) {
        this.dateFormat = dateFormat;
    }

    public final void addLineItem(String prodId, int qty) {
        LineItem item = new LineItem(db, prodId, qty);
        addToLineItemArray(item);
    }

    private final void addToLineItemArray(LineItem item) {
        LineItem[] tempItems = new LineItem[lineItemArray.length + 1];
        System.arraycopy(lineItemArray, 0, tempItems, 0, lineItemArray.length);
        tempItems[lineItemArray.length] = item;
        lineItemArray = tempItems;
    }

    private final String getLineItem(int index) {
        return lineItemArray[index].toString();
    }

    public final String getReceiptDateFormatted() {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(receiptDate);
    }

    public final String outputReceipt() {
        StringBuilder builder = new StringBuilder();

        final String companyName = "Thank you for shopping at Northwinds";
        final String CRLF = "\n";
        final String CRLF2 = "\n\n";

        builder.append(companyName).append(CRLF);
        builder.append(getReceiptDateFormatted()).append(CRLF);
        builder.append("Receipt No. ").append(getReceiptNumber()).append(CRLF2);
        builder.append("Sold to: ").append(getCustomer().getCustName()).append(CRLF2);

        String[] header = {"Product Id", "Product Name", "Unit Price", "Qty", "Subtotal", "Discount"};
        String[][] rowData = new String[lineItemArray.length][header.length];
        for (int row = 0; row < lineItemArray.length; row++) {
            for (int col = 0; col < header.length; col++) {
                String tempProdInfo = "";
                switch (col) {
                    case 0:
                        tempProdInfo = lineItemArray[row].getProduct().getProdId();
                        break;
                    case 1:
                        tempProdInfo = lineItemArray[row].getProduct().getProdName();
                        break;
                    case 2:
                        tempProdInfo = Double.toString(lineItemArray[row].getProduct().getUnitCost());
                        break;
                    case 3:
                        tempProdInfo = Integer.toString(lineItemArray[row].getQty());
                        break;
                    case 4:
                        tempProdInfo = Double.toString(lineItemArray[row].getLineItemSubTotal());
                        break;
                    case 5:
                        tempProdInfo = Double.toString(lineItemArray[row].getProduct().calcDiscount(lineItemArray[row].getQty()));
                        break;
                    default:
                        throw new IllegalArgumentException("an error has occured yo");
                }
                //System.out.println(tempProdInfo);
                rowData[row][col] = tempProdInfo;
            }
        }

        for (String x : header) {
            builder.append(x).append("\t");
        }
        
        builder.append(CRLF);

        for (String item[] : rowData) {
            for(String itemDetail: item){
                builder.append(itemDetail);
                builder.append("\t");
            }
            builder.append(CRLF);
        }

        return builder.toString();
    }

    public static void main(String[] args) {
        ReceiptDataAccessStrategy db = new InMemoryDataAccess();
        Receipt receipt = new Receipt("100", db);
        ReceiptOutputStrategy co = new ConsoleOutput();
        ReceiptOutputStrategy guio = new GUIOutput();

//        System.out.println(receipt.getCustomer().getCustName());
//        System.out.println(receipt.getReceiptDateFormatted());
//        System.out.println(receipt.getReceiptNumber());

        receipt.addLineItem("A101", 2);
        receipt.addLineItem("B205", 1);

        System.out.println("");
//        String lineItems = "";
//        for(LineItem item : receipt.getLineItemArray()){
//            System.out.println(item.getProduct().getProdName());
//            lineItems += item.getProduct().getProdName() + "\n";
//        }

//        System.out.println("");
        //System.out.println(receipt.lineItemArray.length);
        co.receiptOutput(receipt.outputReceipt());
        //guio.receiptOutput(lineItems);
    }

}
