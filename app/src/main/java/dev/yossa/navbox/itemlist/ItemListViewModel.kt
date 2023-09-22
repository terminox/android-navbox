package dev.yossa.navbox.itemlist

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.Module
import dagger.Provides
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ItemListViewDelegate {
  fun didSelectItem(item: Item)
}

interface ItemFetchingService {
  suspend fun fetchItems(): List<Item>
}

class ItemListViewModel(private val service: ItemFetchingService): ViewModel() {

  var onItemSelected: ((Item) -> Unit)? = null

  var items by mutableStateOf<List<Item>>(listOf())

  fun onAppear() {
    viewModelScope.launch {
      fetchItems()
    }
  }

  fun select(item: Item) {
    onItemSelected?.invoke(item)
  }

  suspend fun fetchItems() {
    items = service.fetchItems()
  }
}
