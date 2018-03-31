package xj_conc.ch02_thread_safety.exercise_2_1;

import net.jcip.annotations.*;

import java.text.*;
import java.util.*;

/**
 * Solve thread safety by using thread confinement.
 */
@NotThreadSafe
public class DateFormatter {
    private final DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public String format(Date date) {
        return df.format(date);
    }

    public Date parse(String date) throws ParseException {
        return df.parse(date);
    }
}