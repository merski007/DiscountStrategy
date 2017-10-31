package discountstrategy;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mjmersenski
 */
public class QtyPercentOffDiscountTest {
    
    public QtyPercentOffDiscountTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

 
    @Test
    public void calcDiscountAmtReturnZero(){
        QtyPercentOffDiscount qpod = new QtyPercentOffDiscount(.5, 5);

        int[] qty = {1,2,3,4};
        for(int i = 0; i < qty.length; i++){
            assertEquals(0,qpod.calcDiscountAmt(qty[i], 5.0),0);
        }
        
    }
    
    @Test
    public void calcDiscountAmtReturnGreaterThanZero(){
        QtyPercentOffDiscount qpod = new QtyPercentOffDiscount(.5, 5);
        assertEquals(2.5,qpod.calcDiscountAmt(5, 1.0),0);
        assertEquals(3,qpod.calcDiscountAmt(6, 1.0),0);
        assertEquals(3.5,qpod.calcDiscountAmt(7, 1.0),0);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void percentOffSetToZero(){
        QtyPercentOffDiscount qpod = new QtyPercentOffDiscount(0, 5);
    }
    
    @Test
    public void percentOffSetGreaterThanZero(){
        QtyPercentOffDiscount qpod = new QtyPercentOffDiscount(1, 5);
    }
     
    @Test(expected = IllegalArgumentException.class)
    public void minQtySetToNegativeNum(){
        QtyPercentOffDiscount qpod = new QtyPercentOffDiscount(1, -1);
    }
    
    @Test
    public void minQtySetToZeroAndGreater(){
        int[] minQty = {0,1,2,3,4,5};
        for(int i = 0; i < minQty.length; i++){
            QtyPercentOffDiscount qpod = new QtyPercentOffDiscount(1, minQty[i]);   
        }
    }
}
