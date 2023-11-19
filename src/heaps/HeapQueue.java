package heaps;

import java.util.ArrayList;
import java.util.NoSuchElementException;
public class HeapQueue<P extends Comparable<? super P>, V>
        implements PriorityQueue<P, V> {
    private final ArrayList<Triplet<P, V>> triplets;
    private long nextTimeStamp = 0L;
    static class Triplet<P extends Comparable<? super P>, V>
            implements Comparable<Triplet<P, V>> {
        private final P priority;
        private final long timeStamp;
        private final V value;
        Triplet(P priority, long timeStamp, V value) {
            this.value = value;
            this.priority = priority;
            this.timeStamp = timeStamp;
        }
        @Override
        public int compareTo(Triplet<P, V> other) { ¿? }
    }
    public HeapQueue() { ¿? }
    @Override public void add(P priority, V value,) { ¿? }
    @Override public V remove() { ¿? }
    @Override public V element() { ¿? }
    @Override public int size() { ¿? }
    ¿?
}
