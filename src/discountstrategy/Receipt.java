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
    
    private final String getLineItem(int index){
        return lineItemArray[index].toString();
    }

    public final String getReceiptDateFormatted() {
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(receiptDate);
    }

    public final String outputReceipt() {
        String receiptData = "Start Here";
        
        /*
        NumberFormat nf = NumberFormat.getCurrencyInstance();
        final String CRLF = "\n";
        final String CRLF2 = "\n\n";
        
        StringBuilder receiptData = new StringBuilder("Thank you for shopping at Northwinds.");
        String custName = customer == null ? "" : customer.getCustName();
        receiptData.append("Sold to: ").append(custName).append(CRLF);
        receiptData.append("Date of sale: ").append(getReceiptDateFormatted()).append(CRLF);
        receiptData.append("Receipt number: ").append(Receipt.receiptNumber).append(CRLF2);
        output.outputReceipt(receiptData.toString());
         */
        
        return receiptData;
    }

    public static void main(String[] args) {
        ReceiptDataAccessStrategy db = new InMemoryDataAccess();
        Receipt receipt = new Receipt("100", db);
        ReceiptOutputStrategy co = new ConsoleOutput();
        ReceiptOutputStrategy guio = new GUIOutput();

        System.out.println(receipt.getCustomer().getCustName());
        System.out.println(receipt.getReceiptDateFormatted());
        System.out.println(receipt.getReceiptNumber());
        
        receipt.addLineItem("A101", 2);
        receipt.addLineItem("B205", 1);
        
        System.out.println("");
        String lineItems = "";
        for(LineItem item : receipt.getLineItemArray()){
            //System.out.println(item.getProduct().getProdName());
            lineItems += item.getProduct().getProdName() + "\n";
        }
        
        System.out.println("");
        
        co.receiptOutput(lineItems);
        guio.receiptOutput(lineItems);
    }

}
