package com.example.testroom

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.viewModels
import com.example.testroom.data.ItemViewModel
import com.example.testroom.data.ItemViewModelFactory
import com.example.testroom.databinding.ActivityItemDetailBinding

class ItemDetail : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityItemDetailBinding

    // widgets
    private lateinit var tvTextView: TextView

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

        tvTextView = binding.textView

        viewModel.retrieveItem(itemId).observe(this) { selectedItem ->
            tvTextView.text = selectedItem.itemName
        }
    }
}