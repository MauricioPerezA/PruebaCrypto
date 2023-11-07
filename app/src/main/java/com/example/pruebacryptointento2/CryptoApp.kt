package com.example.pruebacryptointento2

import android.app.Application
import com.example.pruebacryptointento2.model.local.AssetDatabase
import com.example.pruebacryptointento2.model.local.AssetRepository

class CryptoApp : Application() {

    private val database by lazy {
        AssetDatabase.getDatabase(this)
    }

    val repository by lazy {
        AssetRepository(database.assetDao())
    }
}