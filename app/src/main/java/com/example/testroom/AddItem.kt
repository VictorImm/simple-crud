package com.example.testroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import com.example.testroom.data.ItemViewModel
import com.example.testroom.data.ItemViewModelFactory
import com.example.testroom.databinding.ActivityAddItemBinding

class AddItem : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityAddItemBinding

    // widgets
    private lateinit var inputItem: com.google.android.material.textfield.TextInputEditText
    private lateinit var btnAdd: Button

    // property initialization
    private val viewModel: ItemViewModel by viewModels {
        ItemViewModelFactory(
            (application as ItemApplication).database.itemDao()
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputItem = binding.inputItem
        btnAdd = binding.btnAdd

        btnAdd.setOnClickListener {
            // add item in database
            if(viewModel.isEntryValid(inputItem.text.toString())) {
                viewModel.addNewItem(inputItem.text.toString())
            }

            // finish activity
            finish()
        }
    }
}