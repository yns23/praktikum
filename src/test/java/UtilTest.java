import de.hfu.Util;
import org.junit.Test;
import static org.junit.Assert.*;


public class UtilTest {


    @Test
    public void TestHalbjahr(){
        assertEquals("1 sollte im HJ",Util.istErstesHalbjahr(1),true);
        assertEquals("6 sollte im HJ",Util.istErstesHalbjahr(6),true);
        assertEquals("7 sollte nicht im HJ",Util.istErstesHalbjahr(7),false);

        try{
            Util.istErstesHalbjahr(13);
            Util.istErstesHalbjahr(0);
            fail("Erwartete Ausnahme wurde nicht geworfen");

        } catch(ArithmeticException e){
        }
    }
}