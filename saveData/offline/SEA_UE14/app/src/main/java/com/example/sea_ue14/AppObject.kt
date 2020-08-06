package com.example.sea_ue14

import android.app.Application

class AppObject: Application() {

    var textC: String = ""

    override fun onCreate() {
        super.onCreate()
    }

    override fun onTerminate() {
        super.onTerminate()
    }
}