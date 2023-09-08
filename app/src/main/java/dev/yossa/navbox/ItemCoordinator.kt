package dev.yossa.navbox

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import dev.yossa.navbox.itemdetail.ItemDetailView
import dev.yossa.navbox.itemdetail.ItemDetailViewModel
import dev.yossa.navbox.itemlist.Item
import dev.yossa.navbox.itemlist.ItemListView
import dev.yossa.navbox.itemlist.ItemListViewModel
import dev.yossa.navbox.itemlist.MockItemFetchingService

@Composable
fun ItemCoordinator(modifier: Modifier = Modifier, navController: NavHostController, viewModel: ItemCoordinatorViewModel = ItemCoordinatorViewModel()) {
  NavHost(modifier = modifier, navController = navController, startDestination = "ItemCoordinator") {
    navigation(startDestination = "ItemList", route = "ItemCoordinator") {
      composable("ItemList") {
        val itemListViewModel = ItemListViewModel(service = MockItemFetchingService())
        itemListViewModel.onItemSelected = { item ->
          viewModel.selectedItem = item
          navController.navigate("ItemDetail")
        }
        ItemListView(viewModel = itemListViewModel)
      }

      composable("ItemDetail") {
        val item = viewModel.selectedItem!!
        val itemDetailViewModel = ItemDetailViewModel(item = item)
        ItemDetailView(viewModel = itemDetailViewModel)
      }
    }
  }
}

class ItemCoordinatorViewModel: ViewModel() {
  var selectedItem: Item? = null
}