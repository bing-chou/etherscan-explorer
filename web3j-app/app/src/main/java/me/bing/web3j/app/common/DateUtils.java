package me.bing.web3j.app.common;

import java.util.Date;
import org.joda.time.LocalDate;

/**
 * @author bing on 2018/7/18
 */
public class DateUtils {
    private DateUtils() {}

    public static Date now() {
        return LocalDate.now().toDate();
    }

    public static String toDay(Date date) {
        return LocalDate.fromDateFields(date).toString("yyyy-MM-dd");
    }
}
