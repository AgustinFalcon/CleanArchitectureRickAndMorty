package com.example.cleancode

import android.app.Application
import com.example.cleancode.db.HelperDataBase
import com.example.cleancode.sharedpreferences.SharedPreference
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class App : Application() {

    val appScope = CoroutineScope(SupervisorJob())

    private val db = {
        HelperDataBase.getDatabase(this)
    }

    companion object {
        lateinit var prefs : SharedPreference
    }

    override fun onCreate() {
        super.onCreate()
        prefs = SharedPreference(applicationContext)
    }
}