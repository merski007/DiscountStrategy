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
        return 0;
    }

}
