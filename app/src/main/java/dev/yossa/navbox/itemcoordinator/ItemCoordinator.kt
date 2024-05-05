package dev.yossa.navbox.itemcoordinator

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import dev.yossa.navbox.itemdetail.ItemDetailView
import dev.yossa.navbox.itemdetail.ItemDetailViewModel
import dev.yossa.navbox.itemlist.HiltItemListView
import dev.yossa.navbox.itemlist.HiltItemListViewModel
import dev.yossa.navbox.itemlist.Item
import dev.yossa.navbox.itemlist.ItemFetchingService
import dev.yossa.navbox.itemlist.ItemListView
import dev.yossa.navbox.itemlist.ItemListViewModel
import dev.yossa.navbox.itemlist.MockItemFetchingService

@Composable
fun ItemCoordinator(
  modifier: Modifier = Modifier,
  navController: NavHostController,
  viewModel: ItemCoordinatorViewModel = ItemCoordinatorViewModel(),
  factory: ItemCoordinatorViewFactory = ItemCoordinatorViewFactory(itemFetchingService = MockItemFetchingService())
) {

//  LaunchedEffect(viewModel.state.collectAsState().value) {
//    viewModel.state.collect { state ->
//      when (state) {
//        is ItemCoordinatorState.ItemDetailState -> {
//          navController.navigate("ItemDetail")
//        }
//
//        else -> {}
//      }
//    }
//  }

  NavHost(modifier = modifier, navController = navController, startDestination = "ItemCoordinator") {
    navigation(startDestination = "ItemList", route = "ItemCoordinator") {
      composable("ItemList") {
//        val itemListViewModel = hiltViewModel<HiltItemListViewModel>()
//        val itemListViewModel = viewModel.itemListViewModel
//        val itemListViewModel = ItemListViewModel(service = MockItemFetchingService())
//        HiltItemListView(viewModel = itemListViewModel)

//        val factory = object : ViewModelProvider.Factory {
//          override fun <T : ViewModel> create(modelClass: Class<T>): T {
//            return ItemListViewModel(service = MockItemFetchingService()) as T
//          }
//        }
//
//        val itemListViewModel: ItemListViewModel = viewModel(factory = factory)
//
//        itemListViewModel.onItemSelected = { item ->
//          viewModel.selectedItem = item
//          navController.navigate("ItemDetail")
//        }
//        ItemListView(viewModel = itemListViewModel)
        factory.ItemListView { item ->
          viewModel.selectedItem = item
          navController.navigate("ItemDetail")
        }
      }

      composable("ItemDetail") {
        val item = viewModel.selectedItem!!
        val itemDetailViewModel = ItemDetailViewModel(item = item)
        ItemDetailView(viewModel = itemDetailViewModel)
      }
    }
  }
}

class ItemCoordinatorViewFactory(private val itemFetchingService: ItemFetchingService) {
  @Composable
  fun ItemListView(onItemSelected: (Item) -> Unit) {
    val factory = object : ViewModelProvider.Factory {
      override fun <T : ViewModel> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return ItemListViewModel(service = itemFetchingService) as T
      }
    }

    val itemListViewModel: ItemListViewModel = viewModel(factory = factory)
    itemListViewModel.onItemSelected = { onItemSelected(it) }

    ItemListView(viewModel = itemListViewModel)
  }
}