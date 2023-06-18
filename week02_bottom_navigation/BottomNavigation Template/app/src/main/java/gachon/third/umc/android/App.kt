package gachon.third.umc.android

import android.app.Application

class App: Application() {
    companion object {
        lateinit var prefs: TokenSharedPreferences
    }
    override fun onCreate() {
        prefs=TokenSharedPreferences(applicationContext)
        super.onCreate()
    }
}