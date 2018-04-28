package xj_conc.ch04_task_execution.exercise_4_1;

import xj_conc.math.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.*;

import static java.util.concurrent.Executors.newFixedThreadPool;

/**
 * Instead of constructing a separate thread for each number, we want to rather
 * use a thread pool.  This should hopefully reduce the latency for factorizing
 * one prime, as we do not need to include the start-up time of a thread.
 */
public class ParallelFactorizer {
    public int factorizeInParallel(long start, int numbersToCheck)
        throws InterruptedException {
        LongAdder primes = new LongAdder();

        // Change this block of code only:
        AtomicLong next = new AtomicLong(start);
        final ExecutorService executorService = newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        for (int i = 0; i < numbersToCheck; i++) {
            executorService.submit(new PrimesDetector(primes, next));
        }
        awaitCompletion(executorService);

        return primes.intValue();
    }

    private void awaitCompletion(ExecutorService executorService) throws InterruptedException {
        executorService.shutdown();
        while (!executorService.isTerminated()) {
            Thread.sleep(100);
        }
    }

    static class PrimesDetector implements Runnable {

        private final LongAdder sharedPrimesCount;
        private final AtomicLong sharedNumbersToCheck;

        public PrimesDetector(LongAdder sharedPrimesCount, AtomicLong sharedNumbersToCheck) {
            this.sharedPrimesCount = sharedPrimesCount;
            this.sharedNumbersToCheck = sharedNumbersToCheck;
        }

        @Override
        public void run() {
            long number = sharedNumbersToCheck.getAndIncrement();
            long[] factors = Factorizer.factor(number);
            if (factors.length == 1) {
                sharedPrimesCount.increment();
            }
        }
    }
}
