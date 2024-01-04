import androidx.compose.material.MaterialTheme
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import ui.MainScreen


fun main() = application {
    Window(onCloseRequest = ::exitApplication, title = "News App") {
        MaterialTheme {
            MainScreen()
        }
    }
}
