package com.example.testroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.activity.viewModels
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.testroom.data.ItemDao
import com.example.testroom.data.ItemViewModel
import com.example.testroom.data.ItemViewModelFactory
import com.example.testroom.databinding.ActivityItemDetailBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class ItemDetail : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityItemDetailBinding

    // widgets
    private lateinit var tvTextView: TextView
    private lateinit var btnUpdate: Button
    private lateinit var btnDelete: Button

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
        binding = ActivityItemDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        tvTextView = binding.itemName

        viewModel.retrieveItem(itemId).observe(this) { selectedItem ->
            if (selectedItem != null) {
                tvTextView.text = selectedItem.itemName
            }
            else {
                tvTextView.text = "deleted item"
            }
        }

        updateItem(itemId)
        deleteItem(itemId)
    }

    private fun updateItem(itemId: Int) {
        btnUpdate = binding.btnUpdate

        // set click listener
        btnUpdate.setOnClickListener {
            val intent = Intent(this, AddItem::class.java)
            intent.putExtra("item_id", itemId)
            startActivity(intent)

            // finish activity
            // finish()
        }
    }

    private fun deleteItem(itemId: Int): Int {
        btnDelete = binding.btnDelete

        // set click listener
        btnDelete.setOnClickListener {
            // TODO: create dialogue

            viewModel.retrieveItem(itemId).observe(this) { selectedItem ->
                if (selectedItem != null) {
                    viewModel.deleteItem(selectedItem)
                }
            }

            // finish activity
            finish()
        }
        return 0
    }
}