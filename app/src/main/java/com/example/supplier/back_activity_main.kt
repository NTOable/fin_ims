package com.example.supplier

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
// added
import android.widget.*
import java.sql.Connection
import java.sql.DriverManager
import java.sql.SQLException

class back_activity_main : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.front_activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
// added

    fun conn {
        val
    }

    // each product will have its own row in their respective table (GPUs, Fans, SSDs, etc)
        // after connecting to MySQL, query to retrieve all items owned by User currently logged in
        // take it via inventory_id (user_id), which will be a FK to every table under the column

    // Tables: Users (user_id, inventory_id, ) Products (gpu_id, cpu_id, fan_id, ssd_id, ) GPU (gpu_id, product_id,
        // Establish database connection first
        // Finalize the tables, make sure it all makes sense
        // Proceed to coding actual app functions

}