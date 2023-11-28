package heaps;

import java.util.ArrayList;
import java.util.NoSuchElementException;

public class HeapQueue<P extends Comparable<? super P>, V> implements PriorityQueue<P, V> {
    public static class Triplet<P extends Comparable<? super P>, V> implements Comparable<Triplet<P, V>> {
        private final P priority;
        private final long timeStamp;
        private final V value;
        public Triplet(P priority, long timeStamp, V value) {
            this.value = value;
            this.priority = priority;
            this.timeStamp = timeStamp;
        }
        @Override
        public int compareTo(Triplet<P, V> other) {
            int comparePriority =this.priority.compareTo(other.priority);
            if(comparePriority !=0){
                return comparePriority ;
            }else {
                if (this.timeStamp<other.timeStamp){
                    return -1;
                }else if (this.timeStamp>other.timeStamp){
                    return 1;
                }else {
                    return 0;
                }
            }
        }
    }

    private final ArrayList<Triplet<P, V>> triplets;
    private long nextTimeStamp = 0L;

    static int parentIndex(int i){
        return i/2;
    }
    static int leftIndex(int i){
        return 2 * i;
    }
    static int rightIndex(int i){
        return 2 * i + 1;
    }
    boolean exists(int index){
        return 1<=index && index<=size();
    }
    public HeapQueue() {
        this.triplets = new ArrayList<>();
        this.triplets.add(0,null);
    }
    @Override public void add(P priority, V value) {
        Triplet newChild =new Triplet(priority, this.nextTimeStamp,value);
        triplets.add(newChild);
        orderHeapChilds();
        this.nextTimeStamp++;
    }

    @Override public V remove() {
        if (size()==0){
            throw new NoSuchElementException();
        }
        V deletedElem=triplets.get(1).value;
        triplets.set(1,triplets.get(size()));
        triplets.remove(size());
        orderHeapParent(1);
        return deletedElem;
    }
    @Override public V element() {
        if (size()==0){
            throw new NoSuchElementException();
        }
        return triplets.get(1).value;
    }
    @Override public int size() {
        return triplets.size()-1;
    }

    private void orderHeapChilds() {
        int indexActually=this.size();
        orderHeapChildsRec(indexActually);
    }

    private void orderHeapChildsRec(int indexActually) {
        if(indexActually>1){
            int parentIndex=parentIndex(indexActually);
            if(triplets.get(parentIndex).compareTo(triplets.get(indexActually))<0){
                swap(indexActually, parentIndex);
                orderHeapChildsRec(parentIndex);
            }
        }
    }
    private void orderHeapParent(int indexActually) {
        int leftIndex=leftIndex(indexActually);
        int rightIndex=rightIndex(indexActually);
        int mostPriority=indexActually;

        if(exists(leftIndex) && triplets.get(leftIndex).compareTo(triplets.get(mostPriority))>0){
            mostPriority=leftIndex;
        }

        if(exists(rightIndex) && triplets.get(rightIndex).compareTo(triplets.get(mostPriority))>0){
            mostPriority=rightIndex;
        }

        if(mostPriority!=indexActually){
            swap(indexActually,mostPriority);
            orderHeapParent(mostPriority);
        }
    }

    private void swap(int indexActually, int changedIndex) {
        Triplet temp=triplets.get(indexActually);
        triplets.set(indexActually, triplets.get(changedIndex));
        triplets.set(changedIndex,temp);
    }
}
