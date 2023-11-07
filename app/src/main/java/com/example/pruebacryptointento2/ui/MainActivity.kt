package com.example.pruebacryptointento2.ui

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pruebacryptointento2.CryptoApp
import com.example.pruebacryptointento2.databinding.ActivityMainBinding
import com.example.pruebacryptointento2.model.local.AssetEntity

class MainActivity : AppCompatActivity(), OnItemClickListener {


    lateinit var bindind: ActivityMainBinding

    private val assetViewModel: AssetViewModel by viewModels {
        AssetViewModelFactory((application as CryptoApp).repository)
    }

    lateinit var sharedPreferences: SharedPreferences

    @SuppressLint("NotifyDataSetChanged")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindind = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bindind.root)

        initSharedPreferences()
        addEditTextListener()
        bindind.nameEt.setText(sharedPreferences.getString("username", ""))


        val recyclerView = bindind.recyclerview

        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = AssetListAdapter(this)

        recyclerView.adapter = adapter

        assetViewModel.allAssets.observe(this) {
                assets -> assets.let { adapter.submitList(it)}
        }

        bindind.swipeRefreshLayout.setOnRefreshListener {
            assetViewModel.getUpdatdData()
            bindind.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onItemClick(asset: AssetEntity) {
        val intent = Intent(this, AssetDetailActivity::class.java)
        intent.putExtra("asset", asset)
        startActivity(intent)
    }

    private fun initSharedPreferences() {
        val fileName = "com.example.crypto"
        sharedPreferences = getSharedPreferences(fileName, Context.MODE_PRIVATE)
    }

    private fun addEditTextListener() {
        bindind.nameEt.doAfterTextChanged {
            sharedPreferences.edit().putString("username", it.toString()).apply()
        }
    }
}