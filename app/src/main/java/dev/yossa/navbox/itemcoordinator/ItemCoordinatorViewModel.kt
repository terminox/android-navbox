package dev.yossa.navbox.itemcoordinator

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dev.yossa.navbox.itemlist.Item
import dev.yossa.navbox.itemlist.ItemListViewDelegate
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ItemCoordinatorViewModel: ViewModel() {

  private val _state = MutableStateFlow<ItemCoordinatorState>(ItemCoordinatorState.RootState)
  val state: StateFlow<ItemCoordinatorState> = _state
  var selectedItem: Item? = null

  fun didSelectItem(item: Item) {
    selectedItem = item
    _state.value = ItemCoordinatorState.ItemDetailState(item)
    Log.d("coor", "selected ${item}")
  }
}

sealed class ItemCoordinatorState {
  object RootState: ItemCoordinatorState() {}
  class ItemDetailState(val item: Item): ItemCoordinatorState() {}
}
