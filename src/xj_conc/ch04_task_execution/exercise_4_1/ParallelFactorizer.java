package xj_conc.ch04_task_execution.exercise_4_1;

import xj_conc.math.*;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static java.util.concurrent.Executors.newFixedThreadPool;
import static java.util.stream.LongStream.range;

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
        return (int) range(start, start + numbersToCheck)
            .parallel()
            .mapToInt(numberToCheck -> Factorizer.factor(numberToCheck).length)
            .filter(found -> found == 1)
            .count();
    }
}
