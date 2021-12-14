import org.junit.Test;
import static org.junit.Assert.*;
import de.hfu.Queue;

public class QueueTest {
    @Test
    public void checkQueue(){
        Queue test = new Queue(3);

        test.enqueue(10);
        assertEquals(10, test.dequeue());

        test.enqueue(11);
        test.enqueue(12);
        test.enqueue(13);
        test.enqueue(14);
        assertEquals(11, test.dequeue());
        assertEquals(12, test.dequeue());
        assertEquals(14, test.dequeue());

        try{
            test.dequeue();
            assertTrue(false);
        }
        catch (IllegalStateException e){}
    }
}