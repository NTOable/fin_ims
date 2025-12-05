package com.example.supplier.repository

import android.content.Context
import com.example.supplier.model.AddOn
import com.example.supplier.model.CPU
import com.example.supplier.model.Case
import com.example.supplier.model.Cooling
import com.example.supplier.model.Fan
import com.example.supplier.model.GPU
import com.example.supplier.model.Memory
import com.example.supplier.model.Motherboard
import com.example.supplier.model.PSU
import com.example.supplier.model.Storage
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory

object PCPartsRepository {
    lateinit var cpus: List<CPU>
    lateinit var gpus: List<GPU>
    lateinit var motherboards: List<Motherboard>
    lateinit var memoryModules: List<Memory>
    lateinit var storageDevices: List<Storage>
    lateinit var psus: List<PSU>
    lateinit var cases: List<Case>
    lateinit var coolingOptions: List<Cooling>
    lateinit var fans: List<Fan>
    lateinit var addOns: List<AddOn>

    fun loadAll(context: Context) {
        cpus = loadJson(context, "cpus.json", CPU::class.java)
        gpus = loadJson(context, "gpus.json", GPU::class.java)
        motherboards = loadJson(context, "motherboards.json", Motherboard::class.java)
        memoryModules = loadJson(context, "memory.json", Memory::class.java)
        storageDevices = loadJson(context, "storage.json", Storage::class.java)
        psus = loadJson(context, "psus.json", PSU::class.java)
        cases = loadJson(context, "cases.json", Case::class.java)
        coolingOptions = loadJson(context, "cooling.json", Cooling::class.java)
        fans = loadJson(context, "fans.json", Fan::class.java)
        addOns = loadJson(context, "addons.json", AddOn::class.java)
    }

    private fun <T> loadJson(context: Context, filename: String, clazz: Class<T>): List<T> {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val type = Types.newParameterizedType(List::class.java, clazz)
        val adapter: JsonAdapter<List<T>> = moshi.adapter(type)

        val json = context.assets.open(filename).bufferedReader().use { it.readText() }
        return adapter.fromJson(json) ?: emptyList()
    }
}
