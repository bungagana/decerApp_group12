package com.bulam.projekakhir.Fragments

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bulam.projekakhir.Model.Blog
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {
    private val _filteredBlogs = MutableStateFlow<List<Blog>>(emptyList())
    val filteredBlogs: StateFlow<List<Blog>> = _filteredBlogs.asStateFlow()

    fun filterBlogs(query: String) {
        // Perform filtering logic here, for now, let's just clear the list
        viewModelScope.launch {
            _filteredBlogs.emit(emptyList())
        }
    }
}
