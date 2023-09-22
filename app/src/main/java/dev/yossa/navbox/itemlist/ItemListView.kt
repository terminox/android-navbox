package dev.yossa.navbox.itemlist

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.scopes.ViewModelScoped

@Composable
fun ItemListView(viewModel: ItemListViewModel) {

  val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current

  DisposableEffect(lifecycleOwner) {
    val observer = LifecycleEventObserver { _, event ->
      when (event) {
        Lifecycle.Event.ON_CREATE -> {
          viewModel.onAppear()
        }

        Lifecycle.Event.ON_START -> {
          viewModel.onAppear()
        }

        Lifecycle.Event.ON_RESUME -> {
          viewModel.onAppear()
        }

        else -> {
          return@LifecycleEventObserver
        }
      }
    }

    lifecycleOwner.lifecycle.addObserver(observer)

    onDispose {
      lifecycleOwner.lifecycle.removeObserver(observer)
    }
  }

  LazyColumn(modifier = Modifier
    .fillMaxSize()
    .background(Color.Red)) {
    item {
      Text("Hello")
    }

    items(viewModel.items) {item ->
      Text(text = item.title, modifier = Modifier.clickable { viewModel.select(item) })
    }
  }
}
