package pl.bydgoszcz.technikum.apka1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pl.bydgoszcz.technikum.apka1.ui.theme.Apka1Theme
import kotlin.random.Random
import androidx.compose.material3.Text
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.layout.ContentScale
// import pl.bydgoszcz.technikum.apka1.R


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Apka1Theme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SwitchesPrank(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }

    @Composable
    fun SwitchesPrank(modifier: Modifier = Modifier) {
        var switchesState by remember { mutableStateOf(List(10) { false }) }
        var switchCount by remember { mutableIntStateOf(0) }
        var totalPresses by remember { mutableIntStateOf(0) }

        val message = when (switchCount) {
            in 0..9 -> "turn all on"
            in 10..19 -> "go go go..."
            in 20..29 -> "you have to focus more"
            in 30..39 -> "use the skibidi power, aaaaaaaaaa"
            else -> "ARE YOU PLAYING DURING A PROGRAMMING CLASS?????????????????"
        }

        Column(modifier = modifier.padding(16.dp)) {
            Text(text = message, modifier = Modifier.padding(bottom = 16.dp))
            Row {
                Column(modifier = Modifier.weight(1f)) {
                    switchesState.subList(0, 5).forEachIndexed { index, isChecked ->
                        Switch(
                            checked = isChecked,
                            onCheckedChange = { checked ->
                                val newState = switchesState.toMutableList()
                                newState[index] = checked
                                if (checked) {
                                    switchCount++
                                    totalPresses++
                                    if (newState.all { it }) {
                                        val randomIndex = Random.nextInt(10)
                                        newState[randomIndex] = false
                                        switchCount--
                                    }
                                } else {
                                    switchCount--
                                }
                                switchesState = newState
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
                Column(modifier = Modifier.weight(1f)) {
                    switchesState.subList(5, 10).forEachIndexed { index, isChecked ->
                        Switch(
                            checked = isChecked,
                            onCheckedChange = { checked ->
                                val newState = switchesState.toMutableList()
                                newState[index + 5] = checked
                                if (checked) {
                                    switchCount++
                                    totalPresses++
                                    if (newState.all { it }) {
                                        val randomIndex = Random.nextInt(10)
                                        newState[randomIndex] = false
                                        switchCount--
                                    }
                                } else {
                                    switchCount--
                                }
                                switchesState = newState
                            },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = MaterialTheme.colorScheme.primary
                            ),
                            modifier = Modifier.padding(vertical = 8.dp)
                        )
                    }
                }
            }
            Text(text = "Number of presses: $switchCount", modifier = Modifier.padding(top = 16.dp))
            Text(text = "Total presses: $totalPresses", modifier = Modifier.padding(top = 8.dp))
            if (switchCount >= 40) {
                Image(
                    painter = painterResource(id = R.drawable.crazy),
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp)
                )
            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun SwitchesPrankPreview() {
        Apka1Theme {
            SwitchesPrank()
        }
    }
}