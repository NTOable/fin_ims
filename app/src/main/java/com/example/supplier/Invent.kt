package com.example.supplier

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// added
import android.widget.*
import android.content.Intent
import com.example.supplier.data.InventoryDatabase

class back_activity_main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.invent)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.invent)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

            // added

            val local_db = InventoryDatabase.getDatabase(this)
            val dao = local_db.inventoryDao()

            // Redirect
            // buttons to go to each button page
            // shows their info, price, current stock

            val prod_1 = findViewById<Button>(R.id.product_1)

            prod_1.setOnClickListener {
                val intent = Intent(this, )
                startActivity(intent)
            }

            // each product will have its own row in their respective table (GPUs, Fans, SSDs, etc)
            // after connecting to MySQL, query to retrieve all items owned by User currently logged in
            // take it via inventory_id (user_id), which will be a FK to every table under the column

            // Tables: Users (user_id, inventory_id, ) Products (gpu_id, cpu_id, fan_id, ssd_id, ) GPU (gpu_id, product_id,
            // Establish database connection first
            // Finalize the tables, make sure it all makes sense
            // Proceed to coding actual app functions

        }
    }
}