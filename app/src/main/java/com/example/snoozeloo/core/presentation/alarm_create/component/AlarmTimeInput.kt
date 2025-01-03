package com.example.snoozeloo.core.presentation.alarm_create.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import com.example.snoozeloo.R
import com.example.snoozeloo.core.presentation.component.custom.CustomNumberField

@Composable
fun AlarmTimeInput(
    modifier: Modifier = Modifier,
    number: String,
    keyboardOptions: KeyboardOptions,
    onValueChange: (String) -> Unit
) {
    CustomNumberField(
        modifier = modifier
            .clip(RoundedCornerShape(5.dp))
            .background(colorResource(R.color.secondary_color))
            .size(100.dp, 120.dp),
        value = number,
        keyboardOptions = keyboardOptions,
        onValueChange = {
            onValueChange(it)
        },
        textStyle = TextStyle(
            color = colorResource(R.color.primary_color),
        )
    )
}