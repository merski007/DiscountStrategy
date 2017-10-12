package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public class LineItem {

    private ReceiptDataAccessStrategy db;
    private Product product;
    private int qty;

    public LineItem(ReceiptDataAccessStrategy db, String prodId, int qty) {
        this.db = db;
        this.product = findProduct(prodId);
        this.qty = qty;
    }

    private final Product findProduct(String prodId) {
        return db.findProduct(prodId);
    }
    
    public final double getExtendedCost() {
        return qty * product.getUnitCost();
    }

    public final ReceiptDataAccessStrategy getDb() {
        return db;
    }

    public final void setDb(ReceiptDataAccessStrategy db) {
        if(db == null){
            throw new IllegalArgumentException("db cannot be blank");
        }
        this.db = db;
    }

    public Product getProduct() {
        return product;
    }

    public final void setProduct(Product product) {
        if(product == null){
            throw new IllegalArgumentException("product cannot be blank");
        }
        this.product = product;
    }

    public final int getQty() {
        return qty;
    }

    public final void setQty(int qty) {
        if(qty <= 0){
            throw new IllegalArgumentException("qty must be greater than zero.");
        }
        this.qty = qty;
    }

    
    /**
     * This main was created for testing purposes only
     * delete before submitting
     * @param args 
     */
    public static void main(String[] args) {
        ReceiptDataAccessStrategy db = new InMemoryDataAccess();
        LineItem test = new LineItem(db, "B206", 2);
        
        String name = test.getProduct().getProdName();
        double cost = test.getExtendedCost();
        double discount = test.getProduct().getDiscount().calcDiscountAmt(5, cost);
        System.out.println(name);
        System.out.println(cost);
        System.out.println(discount);
        System.out.println("total: "+(cost-discount));
    }
}
