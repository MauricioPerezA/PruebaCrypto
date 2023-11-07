package com.example.pruebacryptointento2.utils

import android.util.Log
import com.example.pruebacryptointento2.CryptoApp
import com.example.pruebacryptointento2.model.remote.AssetRetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class UpdateData {
    companion object {

        // ESTE METODO SIRVE PARA OBTENER LOS DATOS ACTUALIZADOS CUANDO SE INSTALA LA APP

        suspend fun getUpdatedData() = coroutineScope {
            launch(Dispatchers.IO) {
                val service = AssetRetrofitClient.retrofitInstance()
                val response = service.getData()

                val data = response.body()

                val app = CryptoApp()

                app.repository.deleteAssets()

                if (response.isSuccessful) {
                    if (data != null) {

                        for (asset in data.data) {
                            Log.i("Asset ", asset.id)
                            app.repository.insertAsset(asset)
                        }
                    }
                } else {
                    Log.e("UpdateData Error", "Ocurrió un error al ejecutar getUpdatedData")
                }
            }
        }
    }
}