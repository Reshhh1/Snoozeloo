import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun AlarmListScreen(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.padding(start = 20.dp, top = 80.dp)
    ){
        Scaffold (
            topBar = {
                Title()
            }
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
        text = "Your Alarms",
        style = TextStyle(fontSize = 28.sp)
    )
}

@Composable
private fun NoAlarmsFound() {
    Text(
        text = "It's empty! Add the first alarm so you don't miss an important moment"
    )
}