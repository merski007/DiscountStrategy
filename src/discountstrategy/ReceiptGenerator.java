package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public class ReceiptGenerator {

    private Receipt receipt;

    public ReceiptGenerator(String custId, ReceiptDataAccessStrategy db) {
        receipt = new Receipt(custId, db);
    }

    public void addLineItem(String prodId, int qty) {
        receipt.addLineItem(prodId, qty);
    }

    public void outputReceipt(ReceiptOutputStrategy view) {
        view.receiptOutput(receipt.outputReceipt());
    }

}
