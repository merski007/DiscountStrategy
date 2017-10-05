package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public class PercentOffDiscount implements DiscountAbstraction {

    private double percentOff;

    public PercentOffDiscount(double percentOff) {
        setPercentOff(percentOff);
    }

    @Override
    public final double calcDiscountAmt(int qty, double unitCost) {
        return qty * unitCost * percentOff;
    }

    public double getPercentOff() {
        return percentOff;
    }

    public void setPercentOff(double percentOff) {
        if (percentOff <= 0 || percentOff > 100) {
            throw new IllegalArgumentException("value must be greater than zero and no bigger then 100");
        }

        this.percentOff = percentOff;
    }

}
