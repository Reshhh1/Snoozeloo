package com.example.snoozeloo.core.presentation.component.custom

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun CustomNumberField(
    modifier: Modifier = Modifier,
    value: String,
    textStyle: TextStyle = TextStyle(),
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    onValueChange: (newValue: String) -> Unit = {}
) {
    BasicTextField(
        modifier = modifier
            .height(37.dp)
            .width(296.dp),
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        maxLines = 1,
        textStyle = textStyle.copy(
            textAlign = TextAlign.Center,
            fontSize = 50.sp,
            fontWeight = FontWeight.W600
        ),
        keyboardOptions = keyboardOptions,
        decorationBox = { innerTextField ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.LightGray, shape = RoundedCornerShape(5.dp)),
                contentAlignment = Alignment.Center
            ) {
                if (value.isEmpty()) {
                    Text(
                        text = "00",
                        color = Color.Gray.copy(alpha = 0.5f),
                        textAlign = TextAlign.Center,
                        fontSize = 50.sp,
                        fontWeight = FontWeight.Bold,
                    )
                }
                innerTextField()
            }
        }
    )
}