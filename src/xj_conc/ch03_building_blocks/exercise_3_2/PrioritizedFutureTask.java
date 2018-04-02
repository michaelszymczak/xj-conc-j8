package xj_conc.ch03_building_blocks.exercise_3_2;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This task should be comparable according to priority and insertion order.
 * Try and understand what
 */
class PrioritizedFutureTask<T> extends FutureTask<T> implements Comparable<T> {

    private static final AtomicLong sequenceGenerator = new AtomicLong(0);

    private final Priority priority;
    private final long sequence;

    public PrioritizedFutureTask(Runnable runnable, T result, Priority priority) {
        super(runnable, result);
        this.priority = priority;
        this.sequence = sequenceGenerator.getAndIncrement();
    }

    public PrioritizedFutureTask(Callable<T> callable, Priority priority) {
        super(callable);
        this.priority = priority;
        this.sequence = sequenceGenerator.getAndIncrement();
    }

    @Override
    public int compareTo(T o) {
        if (! (o instanceof PrioritizedFutureTask)) {
            return -1;
        }

        PrioritizedFutureTask<T> other = (PrioritizedFutureTask<T>) o;

        return (priority.compareTo(other.priority) != 0) ?
            priority.compareTo(other.priority) : Long.compare(sequence, other.sequence);
    }
}
