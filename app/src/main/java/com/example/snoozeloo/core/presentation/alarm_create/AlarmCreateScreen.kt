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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButtonColors
import androidx.compose.material3.Text
import androidx.compose.material3.TimeInput
import androidx.compose.material3.TimePickerDefaults
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.R
import com.example.snoozeloo.core.presentation.alarm_list.AlarmListViewModel
import com.example.snoozeloo.core.presentation.component.custom.CustomAlertDialog
import com.example.snoozeloo.core.presentation.component.custom.CustomTextButton
import com.example.snoozeloo.core.presentation.component.wrapper.RoundedCornerWrap
import org.koin.androidx.compose.koinViewModel

@Composable
fun AlarmCreateScreen(
    modifier: Modifier = Modifier,
    navigateToList: () -> Unit = {},
    viewmodel: AlarmListViewModel = koinViewModel()
) {
    var isOpen by remember { mutableStateOf(false)}
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp, top = 35.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        TopBarActions(navigateBack = navigateToList)
        AlarmDuration()
        AlarmName(onButtonClick = { isOpen = !isOpen })
        if (isOpen) {
            CustomAlertDialog(
                dialogTitle = "Alarm Name",
                confirmButtonText = "Save",
                onDismissRequest = { isOpen = !isOpen }
            )
        } else {
            Text("CLOSED")
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AlarmDuration(modifier: Modifier = Modifier) {
    val timeInputState = rememberTimePickerState(
        initialHour = 0,
        initialMinute = 0,
        is24Hour = true
    )
    RoundedCornerWrap(
        modifier = Modifier
            .padding(top = 30.dp)
            .size(328.dp, 176.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            TimeInput(
                modifier = modifier
                    .align(Alignment.CenterHorizontally),
                state = timeInputState,
                colors = TimePickerDefaults.colors(
                    timeSelectorSelectedContainerColor = colorResource(R.color.secondary_color),
                    timeSelectorUnselectedContainerColor = colorResource(R.color.secondary_color),
                    timeSelectorSelectedContentColor = colorResource(R.color.primary_color),
                    timeSelectorUnselectedContentColor = colorResource(R.color.placeholder_color),
                )
            )
        }
        Text(
            modifier = modifier,
            text = "Alarm in 7h 15min",
            color = colorResource(R.color.placeholder_color)
        )
    }
}

@Composable
private fun AlarmName(onButtonClick: () -> Unit = {}) {
    RoundedCornerWrap(
        modifier = Modifier
            .padding(top = 16.dp)
            .clip(shape = RoundedCornerShape(CornerSize(12.dp)))
            .clickable { onButtonClick() }
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
                text = "YOUR ALARM NAME",
                fontSize = 16.sp,
                color = colorResource(R.color.placeholder_color)
            )
        }
    }
}

@Composable
private fun TopBarActions(
    navigateBack: () -> Unit = {}
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        CloseButton(onClick = navigateBack)
        CustomTextButton(
            modifier = Modifier.size(80.dp, 40.dp),
            title = "Save",
            onClick = {}
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