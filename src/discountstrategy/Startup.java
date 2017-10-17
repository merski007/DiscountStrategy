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
        //ReceiptOutputStrategy o = new GUIOutput();
        
        //Trans #1
        pos.startNewSale("100", db);
        pos.addItemToSale("A101", 2);
        pos.addItemToSale("A102", 5);
        pos.addItemToSale("A103", 5);
        pos.addItemToSale("A104", 2);
        pos.addItemToSale("B205", 2);
        pos.addItemToSale("C222", 1);
        pos.endSale(o);
        //pos.cancelSale(o);
        
        //Trans #2
        pos.startNewSale("100", db);
        pos.addItemToSale("A101", 2);
        pos.addItemToSale("B205", 2);
        pos.addItemToSale("C222", 1);
        pos.endSale(o);
        

        //test case 1 - turn on test case 1 product array in the InMemoryDataAccess class
        //QtyPercentOffDiscount
//        pos.startNewSale("100", db);
//        pos.addItemToSale("A101", 1);   //this should return 0 for discount, minium not met
//        pos.addItemToSale("B205", 5);   //this should return 10% for discount
//        pos.addItemToSale("C222", 2);   //this has noDiscount
//        pos.endSale(o);
        // TEST = SUCCESS!
        
        //test case 2 - turn on test case 2 product array in the InMemoryDataAccess class
        //QtyFlatAmtOffDiscount
//        pos.startNewSale("100", db);
//        pos.addItemToSale("A101", 1);   //this should return 0 for discount, minium not met
//        pos.addItemToSale("B205", 5);   //this should return $10 for discount
//        pos.addItemToSale("C222", 2);   //this has noDiscount
//        pos.endSale(o);
        // TEST = SUCCESS!
        
        //test case 3 - turn on test case 3 product array in the InMemoryDataAccess class
        //PercentOffDiscount
//        pos.startNewSale("100", db);
//        pos.addItemToSale("A101", 1);   //this should return 10% for discount, minium not met
//        pos.addItemToSale("B205", 5);   //this should return 20% for discount
//        pos.addItemToSale("C222", 2);   //this has noDiscount
//        pos.endSale(o);
        // TEST = SUCCESS!
        
        //test case 4 - turn on test case 4 product array in the InMemoryDataAccess class
        //FlatAmtOffDiscount
//        pos.startNewSale("100", db);
//        pos.addItemToSale("A101", 1);   //this should return $10 for discount, minium not met
//        pos.addItemToSale("B205", 5);   //this should return $20 for discount
//        pos.addItemToSale("C222", 2);   //this has noDiscount
//        pos.endSale(o);
        // TEST = SUCCESS!
    }
}
