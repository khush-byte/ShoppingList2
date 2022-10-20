package com.example.shoppinglist.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.shoppinglist.data.ShopListRepositoryImpl
import com.example.shoppinglist.domain.*

class ShopItemViewModel: ViewModel() {
    private val repository = ShopListRepositoryImpl
    private val getItemUseCase = GetItemUseCase(repository)
    private val addItemUseCase = AddItemUseCase(repository)
    private val editItemUseCase = EditItemUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errorInputName: LiveData<Boolean>
        get() = _errorInputName

    fun getErrorInputNameLD(): LiveData<Boolean>{
        return errorInputName
    }

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    fun getErrorInputCountLD(): LiveData<Boolean>{
        return errorInputCount
    }

    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
        get() = _shopItem

    private val _shouldCLoseScreen = MutableLiveData<Unit>()
    val shouldCLoseScreen: LiveData<Unit>
        get() = _shouldCLoseScreen

    private val getShopListUseCase = GetShopListUseCase(repository)
    var shopList = getShopListUseCase.getShopList()

    fun getShopItem(shopItemId: Int) {
        val item = getItemUseCase.getItem(shopItemId)
        _shopItem.value = item
    }

    fun addShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if(fieldsValid){
            val shopItem = ShopItem(name, count, true)
            addItemUseCase.addItem(shopItem)
            finishWork()
        }
    }

    fun editShopItem(inputName: String?, inputCount: String?){
        val name = parseName(inputName)
        val count = parseCount(inputCount)
        val fieldsValid = validateInput(name, count)
        if(fieldsValid){
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count)
                editItemUseCase.editItem(item)
                finishWork()
            }
        }
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: NumberFormatException) {
            0
        }
    }

    private fun validateInput(name: String, count: Int): Boolean {
        var result = true
        if(name.isBlank()){
            _errorInputName.value = true
            result = false
        }
        if(count <= 0){
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    fun resetErrorInputName(){
        _errorInputName.value = false
    }

    fun resetErrorInputCount(){
        _errorInputCount.value = false
    }

    fun finishWork(){
        _shouldCLoseScreen.value = Unit
    }
}