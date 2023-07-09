package com.example.testroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testroom.adapter.ItemAdapter
import com.example.testroom.data.*
import com.example.testroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // binding
    private lateinit var binding: ActivityMainBinding

    // widgets
    private lateinit var rvItems: RecyclerView

    // property initialization
    private val viewModel: ItemViewModel by viewModels {
        ItemViewModelFactory(
            (application as ItemApplication).database.itemDao()
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvItems = binding.rvItems
        rvItems.setHasFixedSize(true)

        viewModel.allItems.observe(this) { items ->
            showRecyclerList(items)
        }

        intentManager()
    }

    private fun showRecyclerList(items: List<Item>) {
        rvItems.layoutManager = LinearLayoutManager(this)
        // show recycle view from selected month
        val itemAdapter = ItemAdapter(items)
        rvItems.adapter = itemAdapter
    }

    private fun intentManager() {
        // button to add daily expense
        val btnAdd: Button = findViewById(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val intent = Intent(this, AddItem::class.java)
            startActivity(intent)
        }
    }
}