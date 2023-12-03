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

    @Test
    void testGeneral() {
        HeapQueue<Integer, String> heap = new HeapQueue<>();

        // Agregar elementos
        heap.add(5, "Five");
        heap.add(2, "Two");
        heap.add(8, "Eight");
        heap.add(1, "One");

        // Verificar tamaño y acceso al elemento de mayor prioridad
        assertEquals(4, heap.size());
        assertEquals("Eight", heap.element());

        // Eliminar el elemento de mayor prioridad
        String removed = heap.remove();
        assertEquals("Eight", removed);
        assertEquals(3, heap.size());
        assertEquals("Five", heap.element());

        // Agregar más elementos
        heap.add(10, "Ten");
        heap.add(3, "Three");

        // Verificar el nuevo elemento de mayor prioridad después de agregar más elementos
        assertEquals(5, heap.size());
        assertEquals("Ten", heap.element());

        // Eliminar todos los elementos restantes
        heap.remove();
        heap.remove();
        heap.remove();
        heap.remove();
        heap.remove();

        // Verificar que la estructura esté vacía después de eliminar todos los elementos
        assertEquals(0, heap.size());
        assertThrows(NoSuchElementException.class, heap::element);
        }

    @Test
    void testRemoveOnEmptyHeapShouldThrowException() {
        HeapQueue<Integer, String> heap = new HeapQueue<>();

        // Verificar que lanzar NoSuchElementException al intentar eliminar de un heap vacío
        assertThrows(NoSuchElementException.class, heap::remove);
    }

}