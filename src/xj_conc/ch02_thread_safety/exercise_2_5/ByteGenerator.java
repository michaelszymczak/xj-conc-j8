package xj_conc.ch02_thread_safety.exercise_2_5;

import java.util.concurrent.atomic.*;

/**
 * TODO: Figure out why we have a race condition and then fix it.
 * Code by Neeme Praks, used with permission.
 * http://stackoverflow.com/questions/7384875/what-is-wrong-with-this-thread-safe-byte-sequence-generator/7387782#7387782
 *
 * @author Neeme Praks
 */
public class ByteGenerator {

    private final AtomicInteger counter = new AtomicInteger(Byte.MIN_VALUE - 1);

    private final Object lock = new Object();

    public byte nextValue() {
        return (byte) counter.incrementAndGet();
    }
}
