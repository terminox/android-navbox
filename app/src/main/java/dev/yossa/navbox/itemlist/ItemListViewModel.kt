package dev.yossa.navbox.itemlist

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
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
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

interface ItemListViewDelegate {
  fun didSelectItem(item: Item)
}

interface ItemFetchingService {
  suspend fun fetchItems(): Result<List<Item>>
}

class ItemListViewModel(private val service: ItemFetchingService): ViewModel() {

  var onItemSelected: ((Item) -> Unit)? = null

//  var items by mutableStateOf<List<Item>>(listOf())
  private val _viewState = MutableStateFlow<ItemListState>(ItemListState.Loading)
  val viewState: StateFlow<ItemListState> = _viewState

  init {
    Log.d("ItemListViewModel", "init")
  }

  fun onAppear() {
    viewModelScope.launch {
      fetchItems()
    }
  }

  fun select(item: Item) {
    onItemSelected?.invoke(item)
  }

  suspend fun fetchItems() {
//    items = service.fetchItems()
    val result = service.fetchItems()
    result
      .onSuccess { items ->
        _viewState.value = ItemListState.Success(items)
      }
      .onFailure { throwable ->
        _viewState.value = ItemListState.Error(throwable.message)
      }
  }
}

sealed class ItemListState {
  object Loading: ItemListState()
  class Success(val items: List<Item>): ItemListState()
  class Error(val message: String?): ItemListState()
}
