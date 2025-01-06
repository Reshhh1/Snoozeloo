package com.example.snoozeloo.core.presentation.alarm_create

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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.R
import com.example.snoozeloo.core.presentation.alarm_create.component.AlarmTimeInput
import com.example.snoozeloo.core.presentation.component.custom.CustomTextButton
import com.example.snoozeloo.core.presentation.component.custom.CustomTextFieldDialog
import com.example.snoozeloo.core.presentation.component.wrapper.RoundedCornerWrap

@Composable
fun AlarmCreateScreen(
    modifier: Modifier = Modifier,
    state: AlarmCreateState,
    onEvent: (AlarmEvent) -> Unit,
    navigateToList: () -> Unit = {},
) {

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 15.dp, end = 20.dp, top = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        TopBarActions(
            navigateToAlarmList = navigateToList,
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
        Row(
            modifier = Modifier
                .padding(horizontal = 35.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            val focusRequester by remember { mutableStateOf(FocusRequester()) }
            TimeHourInput(
                onHourChange = onHourChange,
                hours = hours,
                focusRequester = focusRequester
            )
            Text(
                text = ":",
                style = TextStyle(
                    fontSize = 50.sp,
                    fontWeight = FontWeight.W600,
                )
            )
            TimeMinuteInput(
                onMinuteChange = onMinuteChange,
                minutes = minutes,
                focusRequester = focusRequester
            )
        }
    }
}

@Composable
private fun TimeHourInput(
    onHourChange: (newHours: String) -> Unit,
    hours: String,
    focusRequester: FocusRequester
) {
    if(hours.length == 2) {
        focusRequester.requestFocus()
    }
    AlarmTimeInput(
        number = hours,
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Next
        ),
        onValueChange = {
            onHourChange(it)
            if(it.length >= 2) {
                focusRequester.requestFocus()
            }
        }
    )
}

@Composable
private fun TimeMinuteInput(
    onMinuteChange: (newMinutes: String) -> Unit,
    minutes: String,
    focusRequester: FocusRequester
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val localFocusManager = LocalFocusManager.current

    fun hideKeyboardAndClearFocus() {
        keyboardController?.hide()
        localFocusManager.clearFocus()
    }

    if(minutes.length >= 2) { hideKeyboardAndClearFocus() }

    AlarmTimeInput(
        modifier = Modifier.focusRequester(focusRequester),
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.Number,
            imeAction = ImeAction.Done
        ),
        number = minutes,
        onValueChange = {
            onMinuteChange(it)
            if(it.length >= 2) { hideKeyboardAndClearFocus() }
        }
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
    navigateToAlarmList: () -> Unit = {},
    onSubmit: (event: AlarmEvent) -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CloseButton(onClick = navigateToAlarmList)
        CustomTextButton(
            modifier = Modifier.size(80.dp, 40.dp),
            title = "Save",
            onClick = {
                onSubmit(AlarmEvent.SaveAlarm)
                navigateToAlarmList()
            }
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