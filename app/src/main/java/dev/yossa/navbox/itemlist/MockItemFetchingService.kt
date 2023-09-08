package dev.yossa.navbox.itemlist

class MockItemFetchingService: ItemFetchingService {
  override suspend fun fetchItems(): List<Item> {
    return listOf(
      Item(id = "0", title = "Item 0", detail = null),
      Item(id = "1", title = "Item 1", detail = "Lorem Ipsum"),
      Item(id = "2", title = "Item 2", detail = "Bye bye yesterday"),
    )
  }
}