import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.R
import com.example.snoozeloo.core.data.AlarmEntity
import com.example.snoozeloo.core.presentation.alarm_list.AlarmListState
import com.example.snoozeloo.core.presentation.component.wrapper.RoundedCornerWrap

@Composable
fun AlarmListScreen(
    modifier: Modifier = Modifier,
    state: AlarmListState,
    navigateToAlarmCreate: () -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(horizontal = 20.dp)
    ){
        Scaffold (
            topBar = {
                Title()
            },
            floatingActionButton = {
                CreateAlarmButton(
                    onButtonClick = navigateToAlarmCreate
                )
            },
            floatingActionButtonPosition = FabPosition.Center
        ) { paddingValues ->
            Column (
                modifier = modifier
                    .padding(paddingValues)
            ) {
                if (state is AlarmListState.Success) {
                    LazyColumn(
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(state.alarms) { alarm ->
                            AlarmItem(alarm)
                        }
                    }
                    if (state.alarms.isEmpty()) {
                        NoAlarmsFound()
                    }
                }
            }
        }
    }
}

@Composable
private fun AlarmItem(alarm: AlarmEntity) {
    RoundedCornerWrap(
        modifier = Modifier
            .fillMaxWidth()
            .height(125.dp)
            .clip(shape = RoundedCornerShape(CornerSize(12.dp)))
            .clickable { },
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.Top
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center
        ) {
            if (alarm.title.isNotEmpty()) {
                Text(
                    text = alarm.title,
                    style = TextStyle(
                        fontSize = 18.sp,
                        fontWeight = FontWeight.W600
                    )
                )
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                Text(
                    text = "${alarm.initialHour}:${alarm.initialMinute}",
                    style = TextStyle(
                        fontSize = 42.sp,
                        fontWeight = FontWeight.W500
                    )
                )
                Switch(
                    modifier = Modifier.size(40.dp),
                    checked = true,
                    onCheckedChange = {}
                )
            }
        }
    }
}
@Composable
private fun Title() {
    Text(
        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp),
        text = stringResource(R.string.alarms_list_title),
        style = TextStyle(fontSize = 28.sp)
    )
}

@Composable
private fun CreateAlarmButton(
    onButtonClick: () -> Unit = {}
) {
    FloatingActionButton(
        modifier = Modifier.size(65.dp),
        onClick = onButtonClick,
        shape = RoundedCornerShape((50.dp)),
        containerColor = colorResource(R.color.primary_color)
    ) {
        Icon(
            modifier = Modifier.size(40.dp),
            imageVector = Icons.Filled.Add,
            contentDescription = "Add",
            tint = Color.White
        )
    }
}

@Composable
private fun NoAlarmsFound() {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier
                .padding(30.dp)
                .size(70.dp),
            painter = painterResource(id = R.drawable.alarm),
            contentDescription = "",
            tint = colorResource(R.color.primary_color)
        )
        Text(
            text = stringResource(R.string.no_alarms),
            textAlign = TextAlign.Center
        )
    }
}