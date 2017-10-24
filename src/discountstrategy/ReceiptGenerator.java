package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public class ReceiptGenerator {

    private Receipt receipt;

    public ReceiptGenerator(String custId, ReceiptDataAccessStrategy db) {
        try{
        receipt = new Receipt(custId, db);
        }
        catch(IllegalArgumentException iae){
            iae.getMessage();
        }
    }

    public final void addLineItem(String prodId, int qty) {
        receipt.addLineItem(prodId, qty);
    }

    public final void outputSalesReceipt(ReceiptOutputStrategy view) {
        view.receiptOutput(receipt.outputReceipt());
    }
    
    public final void outputCancelReceipt(ReceiptOutputStrategy view) {
        view.receiptOutput("Transaction has been cancelled.");
    }

}
