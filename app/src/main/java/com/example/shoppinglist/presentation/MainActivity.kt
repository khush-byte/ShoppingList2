package com.example.shoppinglist.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.shoppinglist.R
import com.example.shoppinglist.domain.ShopItem

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: ShopListAdaptor


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupRecyclerView()

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        //viewModel.removeItem(viewModel.getShopItem(1))
        //viewModel.changeEnableState(viewModel.getShopItem(0))
        viewModel.shopList.observe(this) {
            //Log.d("MainActivityTest", it.toString())
            //showList(it)
            adapter.shopList = it
        }
    }

    /*private fun showList(list: List<ShopItem>) {
        llShopList.removeAllViews()
        for (shopItem in list) {
            val layoutId = if (shopItem.enabled) {
                R.layout.item_enabled
            } else {
                R.layout.item_disabled
            }
            val view = LayoutInflater.from(this).inflate(layoutId, llShopList, false)
            val tvName = view.findViewById<TextView>(R.id.p_name)
            val tvCount = view.findViewById<TextView>(R.id.p_count)
            tvName.text = shopItem.name
            tvCount.text = shopItem.count.toString()
            view.setOnClickListener{
                viewModel.changeEnableState(shopItem)
                true
            }
            llShopList.addView(view)
        }
    }*/

    private fun setupRecyclerView(){
        val rvShopList = findViewById<RecyclerView>(R.id.rv_shop_list)
        adapter = ShopListAdaptor()
        rvShopList.adapter = adapter
        rvShopList.recycledViewPool.setMaxRecycledViews(ShopListAdaptor.VIEW_TYPE_ENABLED, ShopListAdaptor.MAX_POOL_SIZE)
        rvShopList.recycledViewPool.setMaxRecycledViews(ShopListAdaptor.VIEW_TYPE_DISABLED, ShopListAdaptor.MAX_POOL_SIZE)
    }
}