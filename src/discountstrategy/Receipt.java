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

    public final Customer getCustomer() {
        return customer;
    }

    public final void setCustomer(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("customer cannot be blank");
        }
        this.customer = customer;
    }

    public final LineItem[] getLineItemArray() {
        return lineItemArray;
    }

    public final void setLineItemArray(LineItem[] lineItemArray) {
        if (lineItemArray == null) {
            throw new IllegalArgumentException("lineItemArray cannot be blank");
        }
        this.lineItemArray = lineItemArray;
    }

    public final static int getReceiptNumber() {
        return receiptNumber;
    }

    public final static void setReceiptNumber(int receiptNumber) {
        if (receiptNumber <= 0) {
            throw new IllegalArgumentException("receiptNumber cannot be less then zero");
        }
        Receipt.receiptNumber = receiptNumber;
    }

    public final Date getReceiptDate() {
        return receiptDate;
    }

    public final void setReceiptDate(Date receiptDate) {
        if (receiptDate == null) {
            throw new IllegalArgumentException("receiptDate cannot be empty");
        }
        this.receiptDate = receiptDate;
    }

    public final ReceiptOutputStrategy getOutput() {
        return output;
    }

    public final void setOutput(ReceiptOutputStrategy output) {
        if (output == null) {
            throw new IllegalArgumentException("output cannot be empty");
        }
        this.output = output;
    }

    public final String getDateFormat() {
        return dateFormat;
    }

    public final void setDateFormat(String dateFormat) {
        if (dateFormat == null) {
            throw new IllegalArgumentException("dateFormat cannot be empty");
        }
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

    private final String getReceiptDateFormatted() {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(receiptDate);
    }

    public final String outputReceipt() {
        StringBuilder builder = new StringBuilder();

        double subtotal = 0;
        double discountTotal = 0;

        final String companyName = "Thank you for shopping at Northwinds";
        final String CRLF = "\n";
        final String CRLF2 = "\n\n";

        builder.append(companyName).append(CRLF);
        builder.append(getReceiptDateFormatted()).append(CRLF);
        builder.append("Receipt No. ").append(getReceiptNumber()).append(CRLF2);
        builder.append("Sold to: ").append(getCustomer().getCustName()).append(CRLF2);

        String[] header = {"Prod Id", "Prod Name", "Unit Price", "Qty", "Subtotal", "Discount"};
        String[] headerUnderscores = {"-------", "---------", "----------", "---", "--------", "--------"};
        
        //create 2d array for line item info
        //also will convert, and format, numerics into strings
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
                        tempProdInfo = String.format("%.2f", lineItemArray[row].getProduct().getUnitCost());
                        break;
                    case 3:
                        tempProdInfo = Integer.toString(lineItemArray[row].getQty());
                        break;
                    case 4:
                        tempProdInfo = String.format("%.2f", lineItemArray[row].getLineItemSubTotal());
                        subtotal += lineItemArray[row].getLineItemSubTotal();
                        break;
                    case 5:
                        tempProdInfo = String.format("%.2f", lineItemArray[row].getProduct().calcDiscount(lineItemArray[row].getQty()));
                        discountTotal += lineItemArray[row].getProduct().calcDiscount(lineItemArray[row].getQty());
                        break;
                    default:
                        throw new IllegalArgumentException("an error has occured yo");
                }
                rowData[row][col] = tempProdInfo;
            }
        }

        //add header info to builder
        builder.append(String.format("%-9s%-30s%-12s%-5s%-10s%-10s", header));
        builder.append(CRLF);
        builder.append(String.format("%-9s%-30s%-12s%-5s%-10s%-10s", headerUnderscores));
        builder.append(CRLF);

        //adds each line item to the builder
        for (String item[] : rowData) {
            builder.append(String.format("%-9s%-30s%-12s%-5s%-10s%-10s", item));
            builder.append(CRLF);
        }

        builder.append(CRLF);
        builder.append(String.format("%66s", "Subtotal"));
        builder.append(String.format("%2s%.2f", "$", subtotal));
        builder.append(CRLF);
        builder.append(String.format("%66s", "Discounts"));
        builder.append(String.format("%2s%.2f", "$", discountTotal));
        builder.append(CRLF);

        double orderTotal = subtotal - discountTotal;

        builder.append(String.format("%66s", "Order Total"));
        builder.append(String.format("%2s%.2f", "$", orderTotal));
        builder.append(CRLF);
        builder.append("----------------------------------------------------------------------------");
        builder.append(CRLF);

        return builder.toString();
    }

}
