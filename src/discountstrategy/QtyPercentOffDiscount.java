package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public class QtyPercentOffDiscount implements DiscountAbstraction {

    private double percentOff;
    private int minQty;

    public QtyPercentOffDiscount(double percentOff, int minQty) {
        setPercentOff(percentOff);
        setMinQty(minQty);
    }

    @Override
    public final double calcDiscountAmt(int qty, double unitCost) {
        double discountAmt = 0;
        if (qty < minQty) {
            discountAmt = 0;
        } else {
            discountAmt = qty * unitCost * percentOff;
        }
        return discountAmt;
    }

    public final double getPercentOff() {
        return percentOff;
    }

    public final void setPercentOff(double percentOff) {
        if (percentOff <= 0 || percentOff > 100) {
            throw new IllegalArgumentException("value must be greater than zero and no bigger then 100");
        }

        this.percentOff = percentOff;
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
