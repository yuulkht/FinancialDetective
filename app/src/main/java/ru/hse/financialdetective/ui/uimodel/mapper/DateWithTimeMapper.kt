package ru.hse.financialdetective.ui.uimodel.mapper

import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun convertInstantToDateWithTime(instant: Instant): String {
    val zonedDateTime = instant.atZone(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("HH:mm, dd.MM.yyyy")
    return zonedDateTime.format(formatter)
}

fun getDateFromInstant(instant: Instant): String {
    val zonedDateTime = instant.atZone(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return zonedDateTime.format(formatter)
}

fun getTimeFromInstant(instant: Instant): String {
    val zonedDateTime = instant.atZone(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return zonedDateTime.format(formatter)
}

fun formatLocalDate(date: LocalDate): String {
    return date.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))
}

fun formatLocalTime(time: LocalTime): String {
    return time.format(DateTimeFormatter.ofPattern("HH:mm"))
}

fun parseLocalDate(dateStr: String): LocalDate {
    val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
    return LocalDate.parse(dateStr, formatter)
}

fun parseLocalTime(timeStr: String): LocalTime {
    val formatter = DateTimeFormatter.ofPattern("HH:mm")
    return LocalTime.parse(timeStr, formatter)
}

fun toInstant(
    localDate: LocalDate,
    localTime: LocalTime,
    zoneId: ZoneId = ZoneId.systemDefault()
): Instant {
    return LocalDateTime.of(localDate, localTime)
        .atZone(zoneId)
        .toInstant()
}

fun stringsToInstant(
    dateStr: String,
    timeStr: String,
    zoneId: ZoneId = ZoneId.systemDefault()
): Instant {
    return toInstant(
        localDate = parseLocalDate(dateStr),
        localTime = parseLocalTime(timeStr),
        zoneId = zoneId
    )
}