package com.example.testroom

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testroom.adapter.ItemAdapter
import com.example.testroom.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var rvItems: RecyclerView
    private lateinit var binding: ActivityMainBinding

    private val items = ArrayList<String>(listOf(
        "item 1",
        "item 2",
        "item 3",
        "item 4",
        "item 5",
    ))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        rvItems = binding.rvItems
        rvItems.setHasFixedSize(true)
        showRecyclerList()

        intentManager()
    }

    private fun showRecyclerList() {
        rvItems.layoutManager = LinearLayoutManager(this)
        // show recycle view from selected month
        val itemAdapter = ItemAdapter(items)
        rvItems.adapter = itemAdapter
    }

    private fun intentManager() {
        // button to add daily expense
        val btnAdd: Button = findViewById<Button>(R.id.btnAdd)
        btnAdd.setOnClickListener {
            val intent = Intent(this, AddItem::class.java)
            startActivity(intent)
        }
    }
}