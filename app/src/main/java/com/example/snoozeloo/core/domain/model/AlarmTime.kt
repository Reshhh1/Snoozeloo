package com.example.snoozeloo.core.domain.model

import androidx.core.text.isDigitsOnly
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class AlarmTime(
    var initialHour: String,
    var initialMinute: String
) {

    fun validateTimeHours(): Boolean {
        return validateTimeDigit(initialHour)
    }

    fun validateTimeMinutes(): Boolean {
        return validateTimeDigit(initialMinute)
    }

    /**
     * Normalize time to double digit
     * @param timeToNormalize time to normalize
     * @param maxTimeValue max time value
     */
    fun normalizeTime(timeToNormalize: String, maxTimeValue: Int): String {
        val time = timeToNormalize.toIntOrNull() ?: return ""
        val maxDoubleDigit = maxTimeValue.toString().first().digitToInt()
        return when {
            timeToNormalize.length == 2 && time > maxTimeValue -> "00"
            timeToNormalize.length == 1 && time > maxDoubleDigit -> "0$timeToNormalize"
            else -> timeToNormalize
        }
    }

    /**
     * Normalize final time to double digit to ensure correct time format
     */
    fun normalizeFinalTime(): AlarmTime {
        return this.copy(
            initialHour = if (initialHour.length == 1) "0$initialHour" else initialHour,
            initialMinute = if (initialMinute.length == 1) "0$initialMinute" else initialMinute
        )
    }

    fun convertTimeToMilliseconds(): LocalDateTime? {
        val timeString = "${initialHour}:${initialMinute}"
        val formatter = DateTimeFormatter.ofPattern("HH:mm")
        return LocalDateTime.parse(timeString, formatter)
    }

    private fun validateTimeDigit(toBeValidated: String): Boolean {
        return toBeValidated.length in 0..2 && toBeValidated.isDigitsOnly()
    }
}
