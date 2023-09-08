package dev.yossa.navbox.itemlist

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Item(val id: String, val title: String, val detail: String?): Parcelable