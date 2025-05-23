import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.MainScreen

@Composable
fun App() {
    MaterialTheme {
        MainScreen()
    }
}

fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "News App") {
        App()
    }
}
