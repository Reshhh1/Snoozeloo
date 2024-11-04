package com.example.snoozeloo.alarm.presentation.component.wrapper

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun RoundedCornerWrap(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    cornerSize: CornerSize = CornerSize(12.dp),
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(cornerSize))
            .background(color),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        content()
    }
}