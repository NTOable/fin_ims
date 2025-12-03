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
import com.example.supplier.data.InventoryDatabase
import com.example.supplier.data.InventoryItem

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

            val new_item = InventoryItem(
                code = "13451230",
                name = "Test",
                brand = "Intel"
            )

            // Redirect
            // buttons to go to each button page
            // shows their info, price, current stock

            val prod_1 = findViewById<ImageButton>(R.id.product_1)

            prod_1.setOnClickListener {
                Toast.makeText(this, "Redirecting...", Toast.LENGTH_SHORT) .show()
                val intent = Intent(this, InventoryListActivity::class.java)
                startActivity(intent)
            }
        }
    }