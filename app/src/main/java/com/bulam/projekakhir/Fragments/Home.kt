// HomeActivity.kt
package com.bulam.projekakhir.Fragments

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.bulam.projekakhir.Model.Blog

class Home : ComponentActivity() {
    private val viewModel by viewModels<HomeViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HomeContent()
        }
    }

    @Composable
    fun HomeContent() {
        var searchText by remember { mutableStateOf(TextFieldValue("")) }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
        ) {
            Text(
                text = "Beranda",
                fontFamily = MaterialTheme.typography.headlineLarge.fontFamily,  // Correct reference
                fontSize = 27.sp,
                color = Color.Green,
                modifier = Modifier.paddingFromBaseline(top = 16.dp)
            )

            Box(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.primaryContainer)  // Correct reference
                    .height(5.dp)
                    .width(40.dp)
                    .paddingFromBaseline(top = 8.dp)
            )
            BasicTextField(
                value = searchText,
                onValueChange = { newTextFieldValue: TextFieldValue ->
                    searchText = newTextFieldValue
                    viewModel.filterBlogs(newTextFieldValue.text)
                },
                textStyle = LocalTextStyle.current.copy(color = Color.Black),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                singleLine = true,
                leadingIcon = {
                    // Use the Icons.Default.Search directly
                    Icons.Default.Search
                }
            )



            Spacer(modifier = Modifier.height(16.dp))
            // Observe the state flow using collectAsState
            BlogList(viewModel.filteredBlogs.collectAsState().value)
        }
    }

    private fun BasicTextField(value: TextFieldValue, onValueChange: (TextFieldValue) -> Unit, textStyle: TextStyle, modifier: Modifier, singleLine: Boolean, leadingIcon: () -> ImageVector) {

    }

    @Composable
    fun BlogList(blogList: List<Blog>) {
        LazyColumn {
            items(blogList) { blog ->
                BlogItem(blog = blog)
            }
        }
    }

    @Composable
    fun BlogItem(blog: Blog) {
        // Customize this according to your blog item UI
        Text(text = blog.title)
    }

    // Other composable functions and code...

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        HomeContent()
    }
}
