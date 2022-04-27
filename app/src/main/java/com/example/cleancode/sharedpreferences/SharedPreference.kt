package com.example.cleancode.sharedpreferences

import android.content.Context


class SharedPreference (private val context: Context) {

    private var sharedName = "Entidad"
    private var sharedUser = ""
    private var storage = context.getSharedPreferences(sharedName, 0)


    fun saveName(name: String) {
        storage.edit().putString(sharedUser, name).apply()
    }

    fun getName() : String {
        return storage.getString(sharedUser, "").toString()
    }

    fun clear() {
        storage.edit().clear().apply()
    }

}