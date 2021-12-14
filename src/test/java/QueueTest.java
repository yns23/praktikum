import de.hfu.Queue;
import de.hfu.Util;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;


public class QueueTest {

    private Queue test;
    private Queue empty;


    @Before
    public void createQueue(){
        test = new Queue(3);
    }

    @Test
    public void TestQ(){

        test.enqueue(1);
        test.enqueue(2);
        test.enqueue(3);
        test.enqueue(4);
        test.enqueue(5);
        test.enqueue(6);
        test.enqueue(7);
        assert(test.dequeue()==1);
        assert(test.dequeue()==2);
        assert(test.dequeue()==7);
        test.enqueue(23);
        assert(test.dequeue()==23);


    }




}