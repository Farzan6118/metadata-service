package com.nesstiff.reference.metadataservice.util;


import com.nesstiff.reference.metadataservice.util.dto.DateDto;

import java.time.LocalDate;

public class GregorianToJalali {
    public static DateDto toJalaliDate(LocalDate gregorian) {
        int gy = gregorian.getYear();
        int gm = gregorian.getMonthValue();
        int gd = gregorian.getDayOfMonth();

        int[] g_d_m = {0, 31, 59, 90, 120, 151, 181, 212, 243, 273, 304, 334};
        int jy, jm, jd;
        int gy2 = (gm > 2) ? (gy + 1) : gy;
        int days = 355666 + (365 * gy) + ((gy2 + 3) / 4) - ((gy2 + 99) / 100) + ((gy2 + 399) / 400) + gd + g_d_m[gm - 1];

        jy = -1595 + (33 * (days / 12053));
        days %= 12053;

        jy += 4 * (days / 1461);
        days %= 1461;

        if (days > 365) {
            jy += (days - 1) / 365;
            days = (days - 1) % 365;
        }

        int[] j_days_in_month = {31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};
        for (jm = 0; jm < 12 && days >= j_days_in_month[jm]; jm++) {
            days -= j_days_in_month[jm];
        }
        jd = days + 1;
        jm += 1;

        return DateDto.builder()
                .y(jy)
                .m(jm)
                .d(jd)
                .build();

    }

    public static LocalDate toGregorianDate(DateDto jalali) {
        int jy = jalali.getY();
        int jm = jalali.getM();
        int jd = jalali.getD();

        int gy;
        int[] g_days_in_month = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int[] j_days_in_month = {31, 31, 31, 31, 31, 31, 30, 30, 30, 30, 30, 29};

        jy += 1595;
        int days = -355668 + (365 * jy) + ((jy / 33) * 8) + (((jy % 33) + 3) / 4);

        for (int i = 0; i < jm - 1; ++i) {
            days += j_days_in_month[i];
        }

        days += jd;

        gy = 400 * (days / 146097);
        days %= 146097;

        if (days > 36524) {
            gy += 100 * (--days / 36524);
            days %= 36524;
            if (days >= 365) days++;
        }

        gy += 4 * (days / 1461);
        days %= 1461;

        if (days > 365) {
            gy += (days - 1) / 365;
            days = (days - 1) % 365;
        }

        int gm;
        int gd;
        boolean leap = (gy % 4 == 0 && gy % 100 != 0) || (gy % 400 == 0);
        if (leap) g_days_in_month[1] = 29;

        for (gm = 0; gm < 12 && days >= g_days_in_month[gm]; gm++) {
            days -= g_days_in_month[gm];
        }

        gd = days + 1;

        return LocalDate.of(gy, gm + 1, gd);
    }

    public static LocalDate toGregorian(String jalaliDateStr) {
        String[] parts = jalaliDateStr.split("/");
        if (parts.length != 3) {
            throw new IllegalArgumentException("Invalid Jalali date format. Expected yyyy/MM/dd");
        }

        int jy = Integer.parseInt(parts[0]);
        int jm = Integer.parseInt(parts[1]);
        int jd = Integer.parseInt(parts[2]);

        DateDto dateDto = DateDto.builder()
                .y(jy)
                .m(jm)
                .d(jd)
                .build();

        return toGregorianDate(dateDto);
    }

    public static String toJalali(LocalDate gregorian) {
        DateDto dateDto = toJalaliDate(gregorian);

        return String.format("%04d/%02d/%02d", dateDto.getY(), dateDto.getM(), dateDto.getD());
    }

    public static Integer toJalaliInteger(LocalDate gregorian) {
        DateDto dateDto = toJalaliDate(gregorian);
        return Integer.valueOf(String.format("%04d%02d%02d", dateDto.getY(), dateDto.getM(), dateDto.getD()));
    }

}
