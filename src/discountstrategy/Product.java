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
        this.prodId = prodId;
        this.prodName = prodName;
        this.unitCost = unitCost;
        this.discount = discount;
    }
    
    public double calcDiscount(int qty){
        return discount.calcDiscountAmt(qty, unitCost);
    }

    public String getProdId() {
        return prodId;
    }

    public void setProdId(String prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public double getUnitCost() {
        return unitCost;
    }

    public void setUnitCost(double unitCost) {
        this.unitCost = unitCost;
    }

    public DiscountAbstraction getDiscount() {
        return discount;
    }

    public void setDiscount(DiscountAbstraction discount) {
        this.discount = discount;
    }

}
