package xj_conc.ch02_thread_safety.exercise_2_4;

import net.jcip.annotations.*;

import java.text.*;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * Solve thread safety by using the immutable parser object
 * DateTimeFormatter.ISO_LOCAL_DATE.  Your methods would now use LocalDate.
 */
@ThreadSafe
public class DateFormatter {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE;

    public String format(LocalDate date) {
        return dtf.format(date);
    }

    public LocalDate parse(String date) {
        return LocalDate.parse(date, dtf);
    }
}
