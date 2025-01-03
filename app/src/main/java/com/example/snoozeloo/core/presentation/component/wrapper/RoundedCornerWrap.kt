package com.example.snoozeloo.core.presentation.component.wrapper

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

/**
 * RoundedCornerWrap wraps the content
 * Note that: the cornerSize needs to be specified again if the composable should be clickable
 * (Otherwise the click color change will not take the cornerSize in account)
 */
@Composable
fun RoundedCornerWrap(
    modifier: Modifier = Modifier,
    color: Color = Color.White,
    cornerSize: CornerSize = CornerSize(12.dp),
    verticalArrangement: Arrangement.Vertical = Arrangement.Center,
    horizontalAlignment: Alignment.Horizontal = Alignment.CenterHorizontally,
    content: @Composable () -> Unit
) {
    Column(
        modifier = modifier
            .clip(shape = RoundedCornerShape(cornerSize))
            .background(color),
        verticalArrangement = verticalArrangement,
        horizontalAlignment = horizontalAlignment
    ) {
        content()
    }
}