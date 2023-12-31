package dev.yossa.navbox.itemdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun ItemDetailView(viewModel: ItemDetailViewModel) {

  val item = viewModel.item

  Column(modifier = Modifier.fillMaxSize().background(Color.Yellow).clickable { viewModel.select(item) }) {
    Text(text = item.title)

    item.detail?.let {
      Text(text = it)
    }
  }
}