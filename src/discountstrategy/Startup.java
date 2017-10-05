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
        
        //Trans #1
        pos.startNewSale("C101");
        pos.addItemToSale("A101", 2);
        pos.addItemToSale("B101", 2);
        pos.endSale();
    }
}
