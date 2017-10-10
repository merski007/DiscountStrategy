package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public interface ReceiptDataAccessStrategy {
    public abstract Customer findCustomer(final String custId);
    
    public abstract Product findProduct(final String prodId);
    
}
