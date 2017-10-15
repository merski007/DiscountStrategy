package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public class PosRegister {

    private ReceiptGenerator rg;

    public final void startNewSale(String custId, ReceiptDataAccessStrategy db) {
        rg = new ReceiptGenerator(custId, db);
    }

    public final void addItemToSale(String prodId, int qty) {
        rg.addLineItem(prodId, qty);
    }

    public final void endSale(ReceiptOutputStrategy view) {
        rg.outputReceipt(view);
    }

    public final void cancelSale() {

    }

}
