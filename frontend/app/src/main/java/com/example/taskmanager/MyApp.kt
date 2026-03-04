package com.example.taskmanager

import android.app.Application
import com.example.taskmanager.ui.data.remote.TokenManager


class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        TokenManager.init(this)
    }
}