package com.example.shoppinglist.domain

class GetItemUseCase (private var shopListRepository: ShopListRepository){
    fun getItem(shopItemId: Int):ShopItem{
        return shopListRepository.getItem(shopItemId)
    }
}