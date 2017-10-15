package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public class Startup {
    public static void main(String[] args) {
        /**
         * Configuration info:
         * 
         */
        PosRegister pos = new PosRegister();
        ReceiptDataAccessStrategy db = new InMemoryDataAccess();
        ReceiptOutputStrategy o = new ConsoleOutput();
        //ReceiptOutputStrategy guio = new GUIOutput();
        
        //Trans #1
        pos.startNewSale("100", db);
        pos.addItemToSale("A101", 2);
        pos.addItemToSale("B205", 2);
        pos.endSale(o);
        //pos.cancelSale(o);
        
        //Trans #2
        pos.startNewSale("100", db);
        pos.addItemToSale("A101", 2);
        pos.addItemToSale("B205", 2);
        pos.endSale(o);
        
    }
}
