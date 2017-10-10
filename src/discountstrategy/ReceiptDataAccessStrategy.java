package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public interface ReceiptDataAccessStrategy {
    public Customer findCustomer(final String custId);
    
    public Product findProduct(final String prodId);
    
}
