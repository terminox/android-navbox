package dev.yossa.navbox

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class MainApplication @Inject constructor(): Application() {
}