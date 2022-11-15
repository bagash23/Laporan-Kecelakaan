package com.bagas.laporankecelakaan.utils

import android.content.Context
import android.content.SharedPreferences

class Preferences (val context: Context) {
    companion object {
        const val USER_PREFF = "USER_PREFF"
    }

    var sharedPreferences = context.getSharedPreferences(USER_PREFF, 0)
    fun setvalues (key : String, value : String) {
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getValues (key: String) : String ? {
        return  sharedPreferences.getString(key, "")
    }

    fun removeValues() {
        sharedPreferences.edit().clear()
        sharedPreferences.edit().commit()
    }


}