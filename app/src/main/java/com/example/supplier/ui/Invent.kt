package com.example.supplier.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// added
import android.widget.*
import android.content.Intent
import com.example.supplier.R
import com.example.supplier.pcbuilderapp.InventoryDatabase

class Invent : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.invent)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.invent)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

            // added
        val db = InventoryDatabase.getDatabase(this)
        val dao = db.inventoryDao()

        // Buttons
        val viewInventory = findViewById<Button>(R.id.btn_view_inventory)
        val addItem = findViewById<Button>(R.id.btn_add_item)

        // Go to list screen
        viewInventory.setOnClickListener {
            val intent = Intent(this, InventoryListActivity::class.java)
            startActivity(intent)
        }

        // Go to Add Item screen
        addItem.setOnClickListener {
            val intent = Intent(this, AddItemActivity::class.java)
            startActivity(intent)
        }
        }
    }