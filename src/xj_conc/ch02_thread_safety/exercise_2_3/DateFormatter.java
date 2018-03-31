package xj_conc.ch02_thread_safety.exercise_2_3;

import net.jcip.annotations.*;

import java.text.*;
import java.util.*;

/**
 * Solve thread safety by using object confinement.
 */
@NotThreadSafe
public class DateFormatter {
    public DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    public String format(Date date) {
        return df.format(date);
    }

    public Date parse(String date) throws ParseException {
        return df.parse(date);
    }
}