package dev.yossa.navbox

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import dev.yossa.navbox.itemdetail.ItemDetailView
import dev.yossa.navbox.itemdetail.ItemDetailViewModel
import dev.yossa.navbox.itemlist.Item
import dev.yossa.navbox.itemlist.ItemListView
import dev.yossa.navbox.itemlist.ItemListViewModel
import dev.yossa.navbox.itemlist.MockItemFetchingService
import dev.yossa.navbox.ui.theme.NavboxTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      NavboxTheme {
        Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
          val navController = rememberNavController()
          ItemCoordinator(navController = navController)
        }
      }
    }
  }
}
