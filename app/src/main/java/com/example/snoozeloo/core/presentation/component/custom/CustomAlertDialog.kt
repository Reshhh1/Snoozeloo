package com.example.snoozeloo.core.presentation.component.custom

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.core.presentation.component.wrapper.RoundedCornerWrap

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomAlertDialog(
    dialogTitle: String,
    confirmButtonText: String,
    onDismissRequest: () -> Unit = {},
) {
    BasicAlertDialog(
        modifier = Modifier
            .fillMaxWidth(),
        onDismissRequest = onDismissRequest,
    ) {
        var input by remember { mutableStateOf("") }
        RoundedCornerWrap (
            modifier = Modifier,
            horizontalAlignment = Alignment.Start
        ) {
            Column(
                modifier = Modifier.padding(10.dp)
            ) {
                Text(
                    modifier = Modifier.padding(vertical = 10.dp),
                    text = dialogTitle,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                )
                CustomTextField(
                    value = input,
                    onValueChange = { input = it }
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
        }
    }
}