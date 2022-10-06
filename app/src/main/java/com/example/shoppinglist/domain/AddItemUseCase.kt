package com.example.shoppinglist.domain

class AddItemUseCase(private var shopListRepository: ShopListRepository) {
    fun addItem(shopItem: ShopItem){
        shopListRepository.addItem(shopItem)
    }
}