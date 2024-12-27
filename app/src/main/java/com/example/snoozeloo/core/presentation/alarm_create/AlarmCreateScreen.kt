package com.example.snoozeloo.core.presentation.alarm_create

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.R
import com.example.snoozeloo.core.presentation.component.custom.CustomNumberField
import com.example.snoozeloo.core.presentation.component.custom.CustomTextButton
import com.example.snoozeloo.core.presentation.component.custom.CustomTextFieldDialog
import com.example.snoozeloo.core.presentation.component.wrapper.RoundedCornerWrap

@Composable
fun AlarmCreateScreen(
    modifier: Modifier = Modifier,
    navigateToList: () -> Unit = {},
    state: AlarmCreateState,
    onEvent: (AlarmEvent) -> Unit
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 20.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        TopBarActions(
            navigateBack = navigateToList,
            onSubmit = onEvent
        )
        AlarmDuration(
            onHourChange = { onEvent(AlarmEvent.SetAlarmHours(it)) },
            onMinuteChange = { onEvent(AlarmEvent.SetAlarmMinutes(it)) },
            hours = state.time.initialHour,
            minutes = state.time.initialMinute,
        )
        AlarmName(
            name = state.name,
            onButtonClick = { onEvent(AlarmEvent.IsTypingName(true)) }
        )
    }
    if(state.isTypingName) {
        CustomTextFieldDialog(
            dialogTitle = "Alarm Name",
            confirmButtonText = "Save",
            onDismissRequest = { onEvent(AlarmEvent.IsTypingName(false)) },
            value = state.name,
            onValueChange = { onEvent(AlarmEvent.SetAlarmName(it)) }
        )
    }
}

@Composable
private fun AlarmDuration(
    onHourChange: (newHours: String) -> Unit,
    onMinuteChange: (newMinutes: String) -> Unit,
    hours: String,
    minutes: String
) {
    RoundedCornerWrap(
        modifier = Modifier
            .padding(top = 30.dp)
            .size(328.dp, 176.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .padding(horizontal = 35.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                AlarmTimeInput(
                    number = hours,
                    onValueChange = onHourChange
                )
                Text(
                    text = ":",
                    style = TextStyle(
                        fontSize = 50.sp,
                        fontWeight = FontWeight.W600,
                    )
                )
                AlarmTimeInput(
                    number = minutes,
                    onValueChange = onMinuteChange
                )
            }
        }
    }
}

@Composable
private fun AlarmTimeInput(
    number: String,
    onValueChange: (String) -> Unit
) {
    CustomNumberField(
        modifier = Modifier
            .clip(RoundedCornerShape(5.dp))
            .background(colorResource(R.color.secondary_color))
            .size(100.dp, 120.dp),
        value = number,
        onValueChange = {
            Log.d("AlarmTimeInput", "Value: ${it.length}")
//            if (it.length >= 3) return@CustomNumberField
            onValueChange(it)
        },
        textStyle = TextStyle(
            color = colorResource(R.color.primary_color),
        )
    )
}

@Composable
private fun AlarmName(
    onButtonClick: () -> Unit,
    name: String,
) {
    RoundedCornerWrap(
        modifier = Modifier
            .padding(top = 16.dp)
            .clip(shape = RoundedCornerShape(CornerSize(12.dp)))
            .clickable {
                onButtonClick()
            }
            .size(328.dp, 52.dp),
        cornerSize = CornerSize(12.dp)
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            horizontalArrangement = Arrangement.Absolute.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Alarm Name",
                fontSize = 16.sp,
                fontWeight = FontWeight.W600
            )
            Text(
                text = name,
                fontSize = 16.sp,
                color = colorResource(R.color.placeholder_color)
            )
        }
    }
}

@Composable
private fun TopBarActions(
    navigateBack: () -> Unit = {},
    onSubmit: (event: AlarmEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CloseButton(onClick = navigateBack)
        CustomTextButton(
            modifier = Modifier.size(80.dp, 40.dp),
            title = "Save",
            onClick = { onSubmit(AlarmEvent.SaveAlarm) }
        )
    }
}

@Composable
private fun CloseButton(
    onClick: () -> Unit = {}
) {
    FilledIconButton (
        modifier = Modifier.size(40.dp),
        onClick = onClick,
        colors = IconButtonColors(
            containerColor = colorResource(R.color.secondary_color),
            contentColor = Color.White,
            disabledContainerColor = colorResource(R.color.secondary_color),
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