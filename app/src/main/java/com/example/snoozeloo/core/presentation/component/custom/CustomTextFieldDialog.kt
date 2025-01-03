package com.example.snoozeloo.core.presentation.component.custom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.example.snoozeloo.core.presentation.component.wrapper.RoundedCornerWrap

@Composable
fun CustomTextFieldDialog(
    dialogTitle: String,
    confirmButtonText: String,
    onDismissRequest: () -> Unit = {},
    value: String = "",
    onValueChange: (String) -> Unit = {}
) {
    Dialog (
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true
        )
    ) {
        RoundedCornerWrap(
            modifier = Modifier,
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                DialogContent(
                    dialogTitle = dialogTitle,
                    confirmButtonText = confirmButtonText,
                    onDismissRequest = onDismissRequest,
                    value = value,
                    onValueChange = onValueChange
                )
            }
        }
    }
}

@Composable
private fun DialogContent(
    dialogTitle: String,
    confirmButtonText: String,
    onDismissRequest: () -> Unit = {},
    value: String = "",
    onValueChange: (String) -> Unit = {}
) {
    Text(
        modifier = Modifier.padding(vertical = 10.dp),
        text = dialogTitle,
        fontSize = 18.sp,
        fontWeight = FontWeight.Bold,
    )
    CustomTextField(
        value = value,
        onValueChange = onValueChange
    )
    Spacer(Modifier.height(12.dp))
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.End,
    ) {
        CustomTextButton(
            modifier = Modifier.size(width = 80.dp, height = 32.dp),
            title = confirmButtonText,
            onClick = onDismissRequest
        )
    }
}