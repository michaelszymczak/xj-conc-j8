package xj_conc.ch03_building_blocks.exercise_3_1;

import net.jcip.annotations.NotThreadSafe;

/**
 * Created 02/04/18.
 */
@NotThreadSafe
public class Every {

    private final String prefix;
    private final int howMany;
    private long called;

    public Every(String prefix, int howMany) {
        this.prefix = prefix;
        this.howMany = howMany;
    }

    void execute() {
        if (++called % howMany == 0) {
            System.out.println(prefix + ": " + called);
        }
    }
}
