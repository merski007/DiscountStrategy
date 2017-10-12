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
        this.customer = findCustomer(custId);
        setDb(db);
    }
    
    private final Customer findCustomer(String custId) {
        return db.findCustomer(custId);
    }

    public final ReceiptDataAccessStrategy getDb() {
        return db;
    }

    public final void setDb(ReceiptDataAccessStrategy db) {
        if(db == null){
            throw new IllegalArgumentException("db cannot be blank");
        }
        this.db = db;
    }
    
    public final void addLineItem(String prodId, int qty){
        LineItem item = new LineItem(db,prodId,qty);
        addToLineItemArray(item);
    }
    
    private final void addToLineItemArray(LineItem item){
        LineItem[] tempItems = new LineItem[lineItemArray.length + 1];
        System.arraycopy(lineItemArray, 0, lineItemArray, 0, lineItemArray.length);
        tempItems[lineItemArray.length] = item;
        lineItemArray = tempItems;
    }
    
    public final String getReceiptDateFormatted(){
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(receiptDate);
    }
    
    public final void outputReceipt(){
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
    }
    
}
