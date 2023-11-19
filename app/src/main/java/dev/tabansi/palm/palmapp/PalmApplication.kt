package dev.tabansi.palm.palmapp

import android.app.Application
import dev.tabansi.palm.palmapp.data.AppContainer
import dev.tabansi.palm.palmapp.data.AppDataContainer

class PalmApplication : Application() {

    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}