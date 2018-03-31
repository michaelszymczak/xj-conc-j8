package xj_conc.ch03_building_blocks.exercise_3_2;

import java.util.concurrent.*;

/**
 * This task should be comparable according to priority and insertion order.
 * Try and understand what
 */
class PrioritizedFutureTask<T> extends FutureTask<T> {
    public PrioritizedFutureTask(Runnable runnable, T result, Priority priority) {
        super(runnable, result);
    }

    public PrioritizedFutureTask(Callable<T> callable, Priority priority) {
        super(callable);
    }
}