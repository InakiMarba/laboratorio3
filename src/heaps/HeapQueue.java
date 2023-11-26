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
            int comparacionPrioridad=this.priority.compareTo(other.priority);
            if(comparacionPrioridad!=0){
                return comparacionPrioridad;
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
        Triplet nuevoAdd=new Triplet(priority, this.nextTimeStamp,value);
        this.nextTimeStamp++;
        triplets.add(nuevoAdd);
        ordenacionHeapHijos();
    }

    private void ordenacionHeapHijos() {
        int indexActual=triplets.size();
        ordenacionHeapRecHijos(indexActual);
    }

    private void ordenacionHeapRecHijos(int indexActual) {
        if(indexActual>1){
            int indicePadre=parentIndex(indexActual);
            if(triplets.get(indicePadre).compareTo(triplets.get(indexActual))<0){
                swap(indexActual, indicePadre);
                ordenacionHeapRecHijos(indicePadre);
            }
        }
    }

    @Override public V remove() {
        if (size()==0){
            throw new NoSuchElementException();
        }
        V elemtoEliminado=triplets.get(1).value;
        triplets.remove(1);
        triplets.set(1,triplets.get(size()));
        ordenacionHeapPadre(1);
        return elemtoEliminado;
    }

    private void ordenacionHeapPadre(int indiceActual) {
        int indexIzquierda=leftIndex(indiceActual);
        int indexDerecha=rightIndex(indiceActual);
        int mayorPrioridad=indiceActual;

        if(exists(indexIzquierda) && triplets.get(indexIzquierda).compareTo(triplets.get(mayorPrioridad))>0){
            mayorPrioridad=indexIzquierda;
        }

        if(exists(indexDerecha) && triplets.get(indexDerecha).compareTo(triplets.get(mayorPrioridad))>0){
            mayorPrioridad=indexDerecha;
        }

        if(mayorPrioridad!=indiceActual){
            swap(indiceActual,mayorPrioridad);
            ordenacionHeapPadre(mayorPrioridad);
        }
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

    private void swap(int indexActual, int indiceIntercamiable) {
        Triplet temp=triplets.get(indexActual);
        triplets.set(indexActual, triplets.get(indiceIntercamiable));
        triplets.set(indiceIntercamiable,temp);
    }
}
