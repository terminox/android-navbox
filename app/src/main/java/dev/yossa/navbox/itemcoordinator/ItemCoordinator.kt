package dev.yossa.navbox.itemcoordinator

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
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
import dev.yossa.navbox.itemlist.ItemListView
import dev.yossa.navbox.itemlist.ItemListViewModel
import dev.yossa.navbox.itemlist.MockItemFetchingService

@Composable
fun ItemCoordinator(modifier: Modifier = Modifier, navController: NavHostController, viewModel: ItemCoordinatorViewModel = ItemCoordinatorViewModel()) {

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
        val itemListViewModel = hiltViewModel<HiltItemListViewModel>()
        itemListViewModel.onItemSelected = { item ->
          viewModel.selectedItem = item
          navController.navigate("ItemDetail")
        }
        HiltItemListView(viewModel = itemListViewModel)
      }

      composable("ItemDetail") {
        val item = viewModel.selectedItem!!
        val itemDetailViewModel = ItemDetailViewModel(item = item)
        ItemDetailView(viewModel = itemDetailViewModel)
      }
    }
  }
}
