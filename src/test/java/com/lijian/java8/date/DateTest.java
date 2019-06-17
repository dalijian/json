package com.lijian.java8.date;

import org.junit.Test;

import java.time.*;
import java.time.temporal.ChronoUnit;

public class DateTest {
    @Test
    public void testLocalDate() {
        LocalDate today = LocalDate.now();
        System.out.println("today=" + today);
        int year = today.getYear();
        int month = today.getMonthValue();
        int day = today.getDayOfMonth();
        System.out.println(" year=" + year + " month=" + month + " day=" + day);
        LocalDate dateOfBirth = LocalDate.of(2018, 8, 8);
        System.out.println("date of birth=" + dateOfBirth);

        System.out.println(dateOfBirth.equals(LocalDate.now()));

        MonthDay birthday = MonthDay.of(dateOfBirth.getMonth(), dateOfBirth.getDayOfMonth());
        MonthDay currentMonthDay = MonthDay.from(LocalDate.now());
        if (currentMonthDay.equals(birthday)) {
            System.out.println(" this is birthday");

        }
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime);

        LocalTime two = localTime.plusHours(2);
        System.out.println("2小时后的时间是");

        LocalDate oneToday = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("一周后的日期是" + oneToday);


        LocalDate previousYear = today.minus(1, ChronoUnit.YEARS);

        System.out.println("一年前的日期是" + previousYear);

        LocalDate nextYear = today.plus(1, ChronoUnit.YEARS);

        System.out.println("一年前的日期是" + nextYear);

        Clock clock = Clock.systemUTC();

        System.out.println("clock" + clock);

        Clock.systemDefaultZone();
        System.out.println(" clock" + clock);


        System.out.println("日期" + nextYear + " 是否在日期" + today + "之后" + nextYear.isAfter(today));
        System.out.println("日期" + nextYear + " 是否在日期" + today + "之后" + nextYear.isBefore(today));

        LocalDateTime localDateTime = LocalDateTime.now();

        ZoneId zone = ZoneId.of(ZoneId.SHORT_IDS.get("ACT"));

        ZonedDateTime zonedDateTime = ZonedDateTime.of(localDateTime, zone);


    }
}
