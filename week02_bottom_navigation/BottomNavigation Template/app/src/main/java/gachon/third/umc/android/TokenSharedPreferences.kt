package gachon.third.umc.android

import android.content.Context
import android.content.Context.MODE_PRIVATE

class TokenSharedPreferences (context: Context) {

    private val prefNm="mPref"
    private val prefs=context.getSharedPreferences(prefNm,MODE_PRIVATE)

    var token:String?
        get() = prefs.getString("token",null)
        set(value){
            prefs.edit().putString("token",value).apply()
        }
}