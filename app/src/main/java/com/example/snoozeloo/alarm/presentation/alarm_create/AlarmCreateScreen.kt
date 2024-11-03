package com.example.snoozeloo.alarm.presentation.alarm_create

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Scaffold
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
fun AlarmCreateScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 80.dp),
    ){
        Scaffold (
            containerColor = Color.Transparent,
            topBar = {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    CloseButton()
                    SaveButton()
                }
            },
            floatingActionButton = {
                //CreateAlarmButton()
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { paddingValues ->
            Column (
                modifier = modifier
                    .padding(paddingValues)
            ) {
               // NoAlarmsFound()
            }
        }
    }
}

@Composable
private fun CloseButton() {
    FilledIconButton (
        modifier = Modifier.size(40.dp),
        onClick = { },
        colors = IconButtonColors(
            containerColor = colorResource(R.color.disabled_button),
            contentColor = Color.White,
            disabledContainerColor = colorResource(R.color.disabled_button),
            disabledContentColor = Color.White
        ),
        enabled = true,
        shape = RoundedCornerShape(25)
    ) {
        Icon(
            imageVector = Icons.Filled.Close,
            contentDescription = "",
            tint = Color.White
        )
    }
}

@Composable
private fun SaveButton() {
    TextButton (
        modifier = Modifier.size(80.dp, 40.dp),
        onClick = {  },
        enabled = false,
        colors = ButtonColors(
            containerColor = colorResource(R.color.primary_color),
            contentColor = Color.White,
            disabledContainerColor = colorResource(R.color.disabled_button),
            disabledContentColor = Color.White
        )
    ) {
        Text(
            text = "Save",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}