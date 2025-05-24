import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.WindowState
import androidx.compose.ui.window.application
import ui.MainScreen

@Composable
fun App() {
    MaterialTheme {
        MainScreen()
    }
}

fun main() = application {
    Window(
        onCloseRequest = ::exitApplication,
        title = "News Desktop App",
        state = WindowState(size = DpSize(1280.dp, 800.dp))
    ) {
        App()
    }
}
