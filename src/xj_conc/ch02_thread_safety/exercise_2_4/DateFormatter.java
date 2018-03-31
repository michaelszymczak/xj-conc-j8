package xj_conc.ch02_thread_safety.exercise_2_4;

import net.jcip.annotations.*;

import java.text.*;
import java.util.*;

/**
 * Solve thread safety by using the immutable parser object
 * DateTimeFormatter.ISO_LOCAL_DATE.  Your methods would now use LocalDate.
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