package ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerHoverIcon
import androidx.compose.ui.res.loadImageBitmap
import androidx.compose.ui.res.useResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import utils.handCursor

@Composable
fun SidePanel(
    onMenuSelected: (header: String) -> Unit, onNewsSearched: (searchedText: String, header: String) -> Unit
) {
    var searchedText by remember { mutableStateOf("") }
    var expandedSection by remember { mutableStateOf("") }
    
    val categories = listOf("business", "entertainment", "general", "health", "science", "sports", "technology")
    val sources = listOf("BBC News", "CNN", "The Guardian", "Reuters", "Associated Press")

    Column(
        modifier = Modifier
            .fillMaxWidth(0.25f)
            .fillMaxHeight()
            .padding(12.dp)
            .background(Color(0xFFF5F5F5)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val bitmap = useResource("logo_image.png") {
            loadImageBitmap(it)
        }
        Spacer(modifier = Modifier.height(18.dp))
        Image(bitmap, "Logo", modifier = Modifier.width(120.dp))
        Spacer(modifier = Modifier.height(18.dp))

        // Search field
        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().height(50.dp),
            singleLine = true,
            placeholder = {
                Text("Search")
            },
            value = searchedText,
            onValueChange = {
                searchedText = it
            },
            trailingIcon = {
                IconButton(
                    onClick = {
                        if (searchedText.isNotEmpty()) {
                            onNewsSearched(searchedText, "Results for '$searchedText'")
                        }
                    },
                    modifier = Modifier.size(40.dp).pointerHoverIcon(handCursor())
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search button",
                        tint = Color.Black
                    )
                }
            }
        )
        
        Spacer(modifier = Modifier.height(24.dp))
        
        // Headlines section
        SectionItem(
            icon = Icons.Default.Search,
            title = "Headlines",
            onClick = {
                searchedText = ""
                onMenuSelected("Headlines")
            }
        )
        
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        
        // Categories section
        SectionHeader(
            icon = Icons.Default.Search,
            title = "Categories",
            isExpanded = expandedSection == "categories",
            onClick = {
                expandedSection = if (expandedSection == "categories") "" else "categories"
            }
        )
        
        if (expandedSection == "categories") {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().height(200.dp)
            ) {
                items(categories) { category ->
                    SubItem(
                        title = category.capitalize(),
                        onClick = {
                            searchedText = ""
                            onMenuSelected("Category: ${category.capitalize()}")
                        }
                    )
                }
            }
        }
        
        Divider(modifier = Modifier.padding(vertical = 8.dp))
        
        // Sources section
        SectionHeader(
            icon = Icons.Default.Person,
            title = "Sources",
            isExpanded = expandedSection == "sources",
            onClick = {
                expandedSection = if (expandedSection == "sources") "" else "sources"
            }
        )
        
        if (expandedSection == "sources") {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().height(200.dp)
            ) {
                items(sources) { source ->
                    SubItem(
                        title = source,
                        onClick = {
                            searchedText = ""
                            onMenuSelected("Source: $source")
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SectionItem(icon: ImageVector, title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .pointerHoverIcon(handCursor())
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 16.sp
        )
    }
}

@Composable
fun SectionHeader(icon: ImageVector, title: String, isExpanded: Boolean, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 4.dp)
            .pointerHoverIcon(handCursor())
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = Color.Black,
            modifier = Modifier.size(24.dp)
        )
        Spacer(modifier = Modifier.width(12.dp))
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            fontSize = 16.sp
        )
        Spacer(modifier = Modifier.weight(1f))
        Text(
            text = if (isExpanded) "▼" else "▶",
            color = Color.Black,
            fontSize = 14.sp
        )
    }
}

@Composable
fun SubItem(title: String, onClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 6.dp, horizontal = 16.dp)
            .pointerHoverIcon(handCursor())
            .clickable(onClick = onClick),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            color = Color.DarkGray,
            fontSize = 14.sp
        )
    }
}

private fun String.capitalize(): String {
    return this.replaceFirstChar { if (it.isLowerCase()) it.titlecase() else it.toString() }
}