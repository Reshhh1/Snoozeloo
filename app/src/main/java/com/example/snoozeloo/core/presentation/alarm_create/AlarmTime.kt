package com.example.snoozeloo.core.presentation.alarm_create

import androidx.core.text.isDigitsOnly

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
    fun timeNormalizer(timeToNormalize: String, maxTimeValue: Int): String {
        val time = timeToNormalize.toIntOrNull() ?: return ""
        val maxDoubleDigit = maxTimeValue.toString().first().digitToInt()
        return when {
            timeToNormalize.length == 2 && time > maxTimeValue -> "00"
            timeToNormalize.length == 1 && time > maxDoubleDigit -> "0$timeToNormalize"
            else -> timeToNormalize
        }
    }


    private fun validateTimeDigit(toBeValidated: String): Boolean {
        return toBeValidated.length in 0..2 && toBeValidated.isDigitsOnly()
    }
}
