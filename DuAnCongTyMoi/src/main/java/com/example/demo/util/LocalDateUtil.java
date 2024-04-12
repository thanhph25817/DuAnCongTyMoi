package com.example.demo.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 
 * 31/03/2022 - LinhLH: Create new
 *
 * @author LinhLH
 */
public class LocalDateUtil extends DateUtil {
    public static LocalDate getFirstDayOfMonth() {
        LocalDate now = LocalDate.now();

        return now.withDayOfMonth(1);
    }

    /**
     * 
     * @return
     */
    public static LocalDate getLastDayOfMonth() {
        LocalDate now = LocalDate.now();

        return now.withDayOfMonth(now.lengthOfMonth());
    }

    /**
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isBefore(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        return date1.isBefore(date2);
    }

    /**
     * 
     * @param date1
     * @param date2
     * @return
     */
    public static boolean isAfter(LocalDate date1, LocalDate date2) {
        if (date1 == null || date2 == null) {
            return false;
        }

        return date1.isAfter(date2);
    }

    /**
     * 
     * @param localDate
     * @param pattern
     * @return
     */
    public static String getLocalDate(LocalDate localDate, String pattern) {
        if (localDate == null) {
            return null;
        }

        DateTimeFormatter formatters = DateTimeFormatter.ofPattern(pattern);

        return localDate.format(formatters);
    }
    
    /**
     * 
     * @param localDate
     * @return
     */
    public static String formatStringShortLocalDate(LocalDate localDate) {
        return getLocalDate(localDate, SHORT_DATE_PATTERN);
    }
}
