package dev.yossa.navbox.itemlist

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.FragmentComponent
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

class MockItemFetchingService @Inject constructor(): ItemFetchingService {
  override suspend fun fetchItems(): Result<List<Item>> {
    return Result.success(
      listOf(
        Item(id = "0", title = "Item 0", detail = null),
        Item(id = "1", title = "Item 1", detail = "Lorem Ipsum"),
        Item(id = "2", title = "Item 2", detail = "Bye bye yesterday"),
      )
    )
  }
}