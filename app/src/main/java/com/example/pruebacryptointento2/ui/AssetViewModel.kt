package com.example.pruebacryptointento2.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.pruebacryptointento2.model.local.AssetEntity
import com.example.pruebacryptointento2.model.local.AssetRepository
import com.example.pruebacryptointento2.utils.UpdateData
import kotlinx.coroutines.runBlocking

class AssetViewModel(private val repository: AssetRepository) : ViewModel() {
    val allAssets: LiveData<List<AssetEntity>> by lazy {
        repository.allAssets.also {
            getUpdatdData()
        }
    }

    fun getUpdatdData() {
        runBlocking {
            UpdateData.getUpdatedData()
        }
    }
}

class AssetViewModelFactory(private val repository: AssetRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AssetViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AssetViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}