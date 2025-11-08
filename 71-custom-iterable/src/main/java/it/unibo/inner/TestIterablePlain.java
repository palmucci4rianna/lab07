package it.unibo.inner;

import it.unibo.inner.api.IterableWithPolicy;
import it.unibo.inner.api.impl.IterableWithPolicyImpl;

import java.util.List;

import static it.unibo.inner.test.Assertions.assertContentEqualsInOrder;

public class TestIterablePlain {

    private TestIterablePlain() {}

    private static <T> IterableWithPolicy<T> getIterableWithPolicy(T[] elements) {
        //return null; 
        // TODO: return the implementation of IterableWithPolicy
        return new IterableWithPolicyImpl<>(elements);
    }

    public static void main(final String[] args) {
        final String[] test1 = { "pippo", "pluto", "paperino" };
        final IterableWithPolicy<String> evenIterable = getIterableWithPolicy(test1);
        assertContentEqualsInOrder(List.of("pippo", "pluto", "paperino"), evenIterable);
        final String[] test2 = {};
        final IterableWithPolicy<String> emptyIterable = getIterableWithPolicy(test2);
        assertContentEqualsInOrder(List.of(), emptyIterable);
        String[] test3 = { "foo" };
        final IterableWithPolicy<String> oneIterable = getIterableWithPolicy(test3);
        assertContentEqualsInOrder(List.of("foo"), oneIterable);
    }
}

/*
 * output:
- As expected: [pippo, pluto, paperino] expected, and [pippo, pluto, paperino] received.
- As expected: [] expected, and [] received.
- As expected: [foo] expected, and [foo] received.
 */

/*
 * to compile and run:
 * javac -d bin \
    src/main/java/it/unibo/inner/api/Predicate.java \
    src/main/java/it/unibo/inner/api/IterableWithPolicy.java \
    src/main/java/it/unibo/inner/api/impl/IterableWithPolicyImpl.java \
    src/test/java/it/unibo/inner/test/TestIterablePlain.java
 */