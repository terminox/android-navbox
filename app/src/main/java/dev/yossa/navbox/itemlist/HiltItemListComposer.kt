package dev.yossa.navbox.itemlist

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@Composable
fun HiltItemListView(viewModel: HiltItemListViewModel = hiltViewModel()) {
  ItemListView(viewModel = viewModel.viewModel)
}

@HiltViewModel
class HiltItemListViewModel @Inject constructor(private val service: ItemFetchingService): ViewModel() {

  @Inject
  lateinit var viewModel: ItemListViewModel

  var onItemSelected: ((Item) -> Unit)? = null
    set(value) {
      viewModel.onItemSelected = value
    }
}

@InstallIn(ViewModelComponent::class)
@Module
object ItemListModule {

  @Provides
  fun provideItemListViewModel(service: ItemFetchingService): ItemListViewModel {
    return ItemListViewModel(service)
  }
}

@InstallIn(ViewModelComponent::class)
@Module
abstract class ItemListServiceModule {

  @ViewModelScoped
  @Binds
  abstract fun bindFetchingService(impl: MockItemFetchingService): ItemFetchingService
}
