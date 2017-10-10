package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public class QtyFlatAmtOffDiscount implements DiscountAbstraction {

    private double flatAmtOff;
    private int minQty;

    public QtyFlatAmtOffDiscount(double flatAmtOff, int minQty) {
        setFlatAmtOff(flatAmtOff);
        setMinQty(minQty);
    }

    @Override
    public final double calcDiscountAmt(int qty, double unitCost) {
        double discountAmt = 0;
        if (qty < minQty) {
            discountAmt = 0;
        } else {
            discountAmt = (qty * unitCost) - flatAmtOff;
        }
        return discountAmt;
    }

    public final double getFlatAmtOff() {
        return flatAmtOff;
    }

    public final void setFlatAmtOff(double flatAmtOff) {
        if (flatAmtOff <= 0 || flatAmtOff > 100) {
            throw new IllegalArgumentException("value cannot be zero");
        }

        this.flatAmtOff = flatAmtOff;
    }

    public final double getMinQty() {
        return minQty;
    }

    public final void setMinQty(int minQty) {
        if (minQty < 0) {
            throw new IllegalArgumentException("value cannot be less then zero");
        }
        this.minQty = minQty;
    }

}
