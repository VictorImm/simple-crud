package com.example.testroom.data

import androidx.lifecycle.*
import kotlinx.coroutines.launch

class ItemViewModel(private val itemDao: ItemDao): ViewModel() {
    // Cache all items form the database using LiveData.
    val allItems: LiveData<List<Item>> = itemDao.getItems().asLiveData()

    // insert item as live data
    private fun insertItem(item: Item) {
        viewModelScope.launch {
            itemDao.insert(item)
        }
    }

    // get entry
    private fun getNewItemEntry(itemName: String): Item {
        return Item(
            itemName = itemName,
        )
    }

    // to call when need to insert some items
    fun addNewItem(itemName: String) {
        val newItem = getNewItemEntry(itemName)
        insertItem(newItem)
    }

    // to check if the input is valid
    fun isEntryValid(itemName: String): Boolean {
        if (itemName.isBlank()) {
            return false
        }
        return true
    }

    // to retrieve item
    fun retrieveItem(id: Int): LiveData<Item> {
        return itemDao.getItem(id).asLiveData()
    }

    // update item with coroutines
    private fun updateItem(item: Item) {
        viewModelScope.launch {
            itemDao.update(item)
        }
    }

    // get entry
    private fun getUpdatedItemEntry(
        itemId: Int,
        itemName: String
    ): Item {
        return Item(
            id = itemId,
            itemName = itemName
        )
    }

    // to call when need to update some items
    fun updateItem(
        itemId: Int,
        itemName: String
    ) {
        val updatedItem =
            getUpdatedItemEntry(
                itemId,
                itemName
            )
        updateItem(updatedItem)
    }

    // to delete item
    fun deleteItem(item: Item) {
        viewModelScope.launch {
            itemDao.delete(item)
        }
    }
}

class ItemViewModelFactory(private val itemDao: ItemDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ItemViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ItemViewModel(itemDao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}