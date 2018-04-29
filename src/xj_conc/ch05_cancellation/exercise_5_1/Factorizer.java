package xj_conc.ch05_cancellation.exercise_5_1;

import java.util.*;

/**
 * TODO: Change factor() to allow another thread to cancel it using interrupt().
 */
public class Factorizer {

    public long[] factor(final long number) throws InterruptedException {
        return new LongCollection(new Factors(number)).asArray();
    }

    private interface InterruptibleSupplier<T> {
        T get() throws InterruptedException;
    }

    private static class Factors implements InterruptibleSupplier<Collection<Long>> {
        private final long originalNumber;

        public Factors(long number) {
            this.originalNumber = number;
        }

        @Override
        public Collection<Long> get() throws InterruptedException {
            long number = originalNumber;
            Collection<Long> factors = new ArrayList<>();
            for (long factor = 2; factor <= number; factor++) {
                interruptIfNecessary();
                while (number % factor == 0) {
                    factors.add(factor);
                    number /= factor;
                }
            }

            return factors;
        }

        private void interruptIfNecessary() throws InterruptedException {
            if (Thread.interrupted()) {
                throw new InterruptedException();
            }
        }

    }

    private static class LongCollection {

        private final InterruptibleSupplier<Collection<Long>> collectionSupplier;

        private LongCollection(InterruptibleSupplier<Collection<Long>> collectionSupplier) {
            this.collectionSupplier = collectionSupplier;
        }

        public long[] asArray() throws InterruptedException {
            return arrayOf(collectionSupplier.get());
        }

        private long[] arrayOf(Collection<Long> collection) {
            long[] result = new long[collection.size()];
            int pos = 0;
            for (Long factor : collection) {
                result[pos++] = factor;
            }
            return result;
        }
    }
}
