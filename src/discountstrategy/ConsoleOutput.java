package discountstrategy;

/**
 *
 * @author mjmersenski
 */
public class ConsoleOutput implements ReceiptOutputStrategy {

    @Override
    public final void receiptOutput(String output) {
         System.out.println(output);
    }
    
}
