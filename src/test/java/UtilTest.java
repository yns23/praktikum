import de.hfu.Util;
import org.junit.Test;
import static org.junit.Assert.*;

public class UtilTest {

    @Test
    public void testistErsteHalbjahr() {

        boolean test1 = Util.istErstesHalbjahr(1);
        boolean test2 = Util.istErstesHalbjahr(6);

        boolean test3 = Util.istErstesHalbjahr(7);
        boolean test4 = Util.istErstesHalbjahr(12);


        assertTrue(test1);
        assertTrue(test2);

        assertFalse(test3);
        assertFalse(test4);


        try {
            boolean test = Util.istErstesHalbjahr(0);
            assertTrue(false);
        }
        catch(IllegalArgumentException e){
        }

        try{
            boolean test = Util.istErstesHalbjahr(13);

        }
        catch(IllegalArgumentException e){
        }

    }
}