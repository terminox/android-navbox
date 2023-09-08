package dev.yossa.navbox.itemlist

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

interface ItemFetchingService {
  suspend fun fetchItems(): List<Item>
}

class ItemListViewModel(private val service: ItemFetchingService): ViewModel() {

  var onItemSelected: ((Item) -> Unit)? = null

  var items by mutableStateOf<List<Item>>(listOf())

  init {
    onAppear()
  }

  fun onAppear() {
    viewModelScope.launch {
      Log.d("navbox", "fetch")
      fetchItems()
    }
  }

  fun select(item: Item) {
    onItemSelected?.invoke(item)
  }

  suspend fun fetchItems() {
    items = service.fetchItems()
    Log.d("navbox", "fetched")
  }
}