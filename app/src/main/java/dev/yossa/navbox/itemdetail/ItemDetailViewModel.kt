package dev.yossa.navbox.itemdetail

import androidx.lifecycle.ViewModel
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.yossa.navbox.itemlist.Item

class ItemDetailViewModel(val item: Item): ViewModel() {
  var onItemSelected: ((Item) -> Unit)? = null

  fun select(item: Item) {
    onItemSelected?.invoke(item)
//    delegate?.didSelectItem(item)
  }
}
