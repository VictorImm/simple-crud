package com.example.testroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
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
        // receive id
        val itemId = intent.getIntExtra("item_id", 0)

        super.onCreate(savedInstanceState)
        binding = ActivityAddItemBinding.inflate(layoutInflater)
        setContentView(binding.root)

        inputItem = binding.inputItem
        btnAdd = binding.btnAdd

        // update item condition
        if (itemId > 0) {
            updateItem(itemId)
        }
        // add item condition
        else {
           addItem()
        }
    }

    private fun addItem() {
        btnAdd.text = "Add"
        btnAdd.setOnClickListener {
            // add item in database
            if(viewModel.isEntryValid(inputItem.text.toString())) {
                viewModel.addNewItem(inputItem.text.toString())

                // finish activity
                finish()
            }
        }
    }

    private fun updateItem(itemId: Int) {
        // autofill input
        viewModel.retrieveItem(itemId).observe(this) { selectedItem ->
            inputItem.setText(selectedItem.itemName, TextView.BufferType.SPANNABLE)
        }

        btnAdd.text = "Update"
        btnAdd.setOnClickListener {
            // update item in database
            if(viewModel.isEntryValid(inputItem.text.toString())) {
                viewModel.updateItem(
                    itemId,
                    this.inputItem.text.toString()
                )

                // finish activity
                finish()
            }
        }
    }
}
