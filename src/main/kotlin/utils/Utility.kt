package utils

import androidx.compose.ui.input.pointer.PointerIcon
import java.awt.Cursor
import java.awt.Desktop
import java.net.URI

fun handCursor() = PointerIcon(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR))

fun openURL(uri: URI) {
    val desktop = Desktop.getDesktop()
    desktop.browse(uri)
}
