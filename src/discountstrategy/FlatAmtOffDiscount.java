package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public class FlatAmtOffDiscount implements DiscountAbstraction {

    private double flatAmtOff;

    public FlatAmtOffDiscount(double flatAmtOff) {
        setFlatAmtOff(flatAmtOff);
    }

    @Override
    public final double calcDiscountAmt(int qty, double unitCost) {
        return flatAmtOff;
    }

    public final void setFlatAmtOff(double flatAmtOff) {
        if (flatAmtOff <= 0 || flatAmtOff > 100) {
            throw new IllegalArgumentException("value cannot be zero");
        }

        this.flatAmtOff = flatAmtOff;
    }
}
