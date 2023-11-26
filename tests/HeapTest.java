import org.junit.jupiter.api.Test;
import heaps.HeapQueue;
import java.util.NoSuchElementException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

public class HeapTest {
    @Test
    void testAddAndRemove() {
        HeapQueue<Integer, String> heapQueue = new HeapQueue<>();

        heapQueue.add(1, "Element 1");
        heapQueue.add(3, "Element 3");
        heapQueue.add(2, "Element 2");

        assertEquals(3, heapQueue.size());
        assertEquals("Element 3", heapQueue.element());

        assertEquals("Element 3", heapQueue.remove());
        assertEquals(2, heapQueue.size());

        assertEquals("Element 2", heapQueue.remove());
        assertEquals(1, heapQueue.size());
    }

    @Test
    void testRemoveFromEmptyQueue() {
        HeapQueue<Integer, String> emptyHeapQueue = new HeapQueue<>();

        assertThrows(NoSuchElementException.class, emptyHeapQueue::remove);
    }

    @Test
    void testElementFromEmptyQueue() {
        HeapQueue<Integer, String> emptyHeapQueue = new HeapQueue<>();

        assertThrows(NoSuchElementException.class, emptyHeapQueue::element);
    }

    @Test
    void testSize() {
        HeapQueue<Integer, String> heapQueue = new HeapQueue<>();

        assertEquals(0, heapQueue.size());

        heapQueue.add(1, "Element 1");
        heapQueue.add(2, "Element 2");

        assertEquals(2, heapQueue.size());

        heapQueue.remove();

        assertEquals(1, heapQueue.size());
    }


}