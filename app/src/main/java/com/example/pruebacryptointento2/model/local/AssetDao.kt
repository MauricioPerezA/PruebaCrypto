package com.example.pruebacryptointento2.model.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface AssetDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertAsset(assetEntity: AssetEntity)

    @Update
    fun updateAsset(assetEntity: AssetEntity)

    @Query("SELECT * FROM asset_table ORDER BY cast(rank as unsigned) asc")
    fun getRankedAssets(): LiveData<List<AssetEntity>>

    @Query("SELECT * FROM asset_table WHERE id = :id")
    fun getAsset(id: String): LiveData<AssetEntity>

    @Query("DELETE FROM asset_table")
    fun deleteAssets()
}