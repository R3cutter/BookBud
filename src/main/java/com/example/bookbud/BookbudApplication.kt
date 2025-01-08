package com.example.bookbud

import android.app.Application
import android.os.StrictMode
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BookbudApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        // Strict mode'u debug'da aktif et
        if (BuildConfig.DEBUG) {
            StrictMode.enableDefaults()
        }
    }
}
