import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.snoozeloo.R

@Composable
fun AlarmListScreen(
    modifier: Modifier = Modifier,
    navigateToAlarmCreate: () -> Unit = {}
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, end = 20.dp, top = 20.dp)
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
                NoAlarmsFound()
            }
        }
    }
}

@Composable
private fun Title() {
    Text(
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
        modifier = Modifier.fillMaxSize().padding(bottom = 80.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            modifier = Modifier.padding(30.dp).size(70.dp),
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