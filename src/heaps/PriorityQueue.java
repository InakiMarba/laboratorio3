package heaps;

import java.util.NoSuchElementException;

public interface PriorityQueue<P extends Comparable<? super P>, V> {
    void add(P priority, V value);
    V remove() throws NoSuchElementException;
    V element() throws NoSuchElementException;
    int size();
}
