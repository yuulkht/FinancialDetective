package ru.hse.financialdetective.ui.uimodel.mapper

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun convertInstantToDateWithTime(instant: Instant): String {
    val zonedDateTime = instant.atZone(ZoneId.systemDefault())
    val formatter = DateTimeFormatter.ofPattern("HH:mm, dd.MM.yyyy")
    return zonedDateTime.format(formatter)
}