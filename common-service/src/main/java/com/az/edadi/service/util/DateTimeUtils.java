package com.az.edadi.service.util;

import com.az.edadi.common.TimeZoneFilter;

import java.time.*;

public class DateTimeUtils {

    public static LocalDateTime toUserLocalDateTime(Instant instant) {
        ZoneId zone = TimeZoneFilter.USER_ZONE.get();
        return LocalDateTime.ofInstant(instant, zone);
    }
    public static LocalDate toUserLocalDate(Instant instant) {
        ZoneId zone = TimeZoneFilter.USER_ZONE.get();
        return instant.atZone(zone).toLocalDate();
    }
}
