package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public interface DiscountAbstraction {

    public abstract double calcDiscountAmt(int qty, double unitCost);

}
