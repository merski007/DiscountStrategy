package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public class Product {

    private String prodId;
    private String prodName;
    private double unitCost;
    private DiscountAbstraction discount;

    public Product(String prodId, String prodName, double unitCost, DiscountAbstraction discount) {
        setProdId(prodId);
        setProdName(prodName);
        setUnitCost(unitCost);
        setDiscount(discount);
    }
    
    public final double calcDiscount(int qty){
        return discount.calcDiscountAmt(qty, unitCost);
    }

    public final String getProdId() {
        return prodId;
    }

    public final void setProdId(String prodId) {
        if (prodId == null || prodId.length() == 0) {
            throw new IllegalArgumentException("prodId cannot be blank");
        }
        this.prodId = prodId;
    }

    public final String getProdName() {
        return prodName;
    }

    public final void setProdName(String prodName) {
        if (prodName == null || prodName.length() == 0) {
            throw new IllegalArgumentException("prodName cannot be blank");
        }
        this.prodName = prodName;
    }

    public final double getUnitCost() {
        return unitCost;
    }

    public final void setUnitCost(double unitCost) {
        if (unitCost <= 0) {
            throw new IllegalArgumentException("unitCost cannot be less than, or equal to zero");
        }
        this.unitCost = unitCost;
    }

    public final DiscountAbstraction getDiscount() {
        return discount;
    }

    public final void setDiscount(DiscountAbstraction discount) {
        if (discount == null) {
            throw new IllegalArgumentException("discount cannot be blank");
        }
        this.discount = discount;
    }

}
