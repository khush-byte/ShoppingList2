package com.example.shoppinglist.domain

import androidx.lifecycle.LiveData

class GetShopListUseCase (private var shopListRepository: ShopListRepository){
    fun getShopList(): LiveData<List<ShopItem>>{
        return shopListRepository.getShopList()
    }
}