package com.example.supplier.ui

import android.widget.ListView
import android.os.Bundle
import kotlinx.coroutines.*
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.supplier.R
import com.example.supplier.data.InventoryDatabase


class InventoryListActivity: AppCompatActivity() {

    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState:Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.inventory_list_activity)
        listView= findViewById(R.id.listView)
        loadData()
    }

    private fun loadData(){
        CoroutineScope(Dispatchers.IO).launch{
            val db= InventoryDatabase.getDatabase(this@InventoryListActivity)
            val items= db.inventoryDao().getAllItems()
            val names= items.map{ "${it.barcode} (${it.name})"}

            withContext(Dispatchers.Main) {
                listView.adapter= ArrayAdapter(
                    this@InventoryListActivity,
                    android.R.layout.simple_list_item_1,
                    names
                )
            }
        }

    }
}