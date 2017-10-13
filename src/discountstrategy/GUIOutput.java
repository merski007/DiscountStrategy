package discountstrategy;

import javax.swing.JOptionPane;

/**
 *
 * @author mjmersenski
 */
public class GUIOutput implements ReceiptOutputStrategy {

    @Override
    public final void receiptOutput(String output) {
        JOptionPane.showMessageDialog(null, output);
    }
    
}
