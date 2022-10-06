package com.example.shoppinglist.domain

class EditItemUseCase (private var shopListRepository: ShopListRepository){
    fun editItem(shopItem: ShopItem){
        shopListRepository.editItem(shopItem)
    }
}