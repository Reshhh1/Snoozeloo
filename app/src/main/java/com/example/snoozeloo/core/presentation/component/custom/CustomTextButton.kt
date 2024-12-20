package com.example.snoozeloo.core.presentation.component.custom

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.R

@Composable
fun CustomTextButton(
    modifier: Modifier = Modifier,
    title: String,
    onClick: () -> Unit = {}
) {
    TextButton(
        modifier = modifier,
        contentPadding = PaddingValues(0.dp),
        onClick = onClick,
        colors = ButtonColors(
            containerColor = colorResource(R.color.primary_color),
            disabledContainerColor = Color.Red,
            contentColor = Color.White,
            disabledContentColor = Color.Cyan
        )
    ) {
        Text(
            text = title,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}