package com.example.testroom

import android.app.Application
import com.example.testroom.data.ItemRoomDatabase

class ItemApplication: Application() {
    val database: ItemRoomDatabase by lazy {
        ItemRoomDatabase.getDatabase(applicationContext)
    }
}