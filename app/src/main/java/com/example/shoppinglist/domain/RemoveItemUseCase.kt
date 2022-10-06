package com.example.shoppinglist.domain

class RemoveItemUseCase (private var shopListRepository: ShopListRepository){
    fun removeItem(shopItem: ShopItem){
        shopListRepository.deleteItem(shopItem)
    }
}