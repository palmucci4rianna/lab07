package it.unibo.inner.api.impl;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.Predicate;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.List;

public class IterableWithPolicyImpl<T> implements IterableWithPolicy<T> {
    private final List<T> elements;
    private Predicate<T> filter;
    
    /**
     * Iterates over the elements.
     * @param elements the elements to iterate over.
     */
    public IterableWithPolicyImpl(final T[] elements) {
       this(elements,
            new Predicate<T>() {
                @Override
                public boolean test(T e) {
                    return true;
                }
            }
        );
    }

    /**
     * Iterates over the elements.
     * @param elements the elements to iterate over.
     * @param filter the filter to apply.
     */
    public IterableWithPolicyImpl(final T[] elements, final Predicate<T> filter) {
        this.elements = List.of(elements);
        this.filter = filter;
    }

    @Override
    public void setIterationPolicy(Predicate<T> filter){
        this.filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new FilterIterator();
    }

    @Override
    public String toString() {
        return elements.toString();
    }

    /**
     * An iterator over the elements of the enclosing collection that applies
     * a filter to determine which elements should be returned during iteration.
     * This iterator skips all elements that do not satisfy the filter.
     * {@link #next()} method returns the next element accepted by the filter,
     * {@link #hasNext()} checks if such an element exists,
     * {@link #remove()} is not supported. 
     * @param <T> the type of elements returned by this iterator.
     */
    private class FilterIterator implements Iterator<T>{
        private int currentIndex = 0;

        @Override
        public boolean hasNext(){
            while (currentIndex < elements.size()){
                final T elem = elements.get(currentIndex);
                if (filter.test(elem)) {
                    return true;
                }
                currentIndex++;
            }
            return false;
        }

        @Override
        public T next(){
            if(hasNext()){
                return elements.get(currentIndex++);
            }
            throw new NoSuchElementException();
        }

        @Override
        public void remove(){
            throw new UnsupportedOperationException();
        }
    }
}
