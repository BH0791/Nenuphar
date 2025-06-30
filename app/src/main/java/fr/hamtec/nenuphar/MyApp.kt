package fr.hamtec.nenuphar

import android.app.Application

class MyApp : Application() {
    override fun onCreate() {
        super.onCreate()
        VolleyClient.init(this)
    }
}