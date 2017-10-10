package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public class NoDiscount implements DiscountAbstraction {

    public NoDiscount() {
    }

    @Override
    public double calcDiscountAmt(int qty, double unitCost) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
