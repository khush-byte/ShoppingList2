package com.example.shoppinglist.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.*

class MainViewModel:ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val getShopListUseCase = GetShopListUseCase(repository)
    private val removeItemUseCase = RemoveItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)

    var shopList = getShopListUseCase.getShopList()

    fun removeItem(shopItem: ShopItem){
        removeItemUseCase.removeItem(shopItem)
    }

    fun changeEnableState(shopItem: ShopItem){
        val newItem = shopItem.copy(enabled = !shopItem.enabled)
        editItemUseCase.editItem(newItem)
    }
}