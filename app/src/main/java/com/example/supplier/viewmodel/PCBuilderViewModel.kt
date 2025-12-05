package com.example.supplier.viewmodel

import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.supplier.repository.PCPartsRepository
import com.example.supplier.compatibility.CompatibilityEngine
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
import org.json.JSONObject

class PCBuilderViewModel : ViewModel() {

    // --- Selected parts ---
    private val _selectedCPU = MutableLiveData<CPU?>()
    val selectedCPU: LiveData<CPU?> = _selectedCPU

    private val _selectedGPU = MutableLiveData<GPU?>()
    val selectedGPU: LiveData<GPU?> = _selectedGPU

    private val _selectedMotherboard = MutableLiveData<Motherboard?>()
    val selectedMotherboard: LiveData<Motherboard?> = _selectedMotherboard

    private val _selectedMemory = MutableLiveData<Memory?>()
    val selectedMemory: LiveData<Memory?> = _selectedMemory

    private val _selectedStorage = MutableLiveData<Storage?>()
    val selectedStorage: LiveData<Storage?> = _selectedStorage

    private val _selectedPSU = MutableLiveData<PSU?>()
    val selectedPSU: LiveData<PSU?> = _selectedPSU

    private val _selectedCase = MutableLiveData<Case?>()
    val selectedCase: LiveData<Case?> = _selectedCase

    private val _selectedCooling = MutableLiveData<Cooling?>()
    val selectedCooling: LiveData<Cooling?> = _selectedCooling

    private val _selectedFan = MutableLiveData<Fan?>()
    val selectedFan: LiveData<Fan?> = _selectedFan

    private val _selectedAddOn = MutableLiveData<AddOn?>()
    val selectedAddOn: LiveData<AddOn?> = _selectedAddOn

    // --- Lists of all parts (loaded from repository) ---
    val cpuList: LiveData<List<CPU>> = MutableLiveData(PCPartsRepository.cpus)
    val gpuList: LiveData<List<GPU>> = MutableLiveData(PCPartsRepository.gpus)
    val storageList: LiveData<List<Storage>> = MutableLiveData(PCPartsRepository.storageDevices)
    val fanList: LiveData<List<Fan>> = MutableLiveData(PCPartsRepository.fans)
    val addOnList: LiveData<List<AddOn>> = MutableLiveData(PCPartsRepository.addOns)

    // --- Compatibility-filtered lists ---
    private val _compatibleMotherboards =
        MutableLiveData<List<Motherboard>>(PCPartsRepository.motherboards)
    val compatibleMotherboards: LiveData<List<Motherboard>> = _compatibleMotherboards

    private val _compatibleMemory = MutableLiveData<List<Memory>>(PCPartsRepository.memoryModules)
    val compatibleMemory: LiveData<List<Memory>> = _compatibleMemory

    private val _compatiblePSUs = MutableLiveData<List<PSU>>(PCPartsRepository.psus)
    val compatiblePSUs: LiveData<List<PSU>> = _compatiblePSUs

    private val _compatibleCases = MutableLiveData<List<Case>>(PCPartsRepository.cases)
    val compatibleCases: LiveData<List<Case>> = _compatibleCases

    private val _compatibleCooling =
        MutableLiveData<List<Cooling>>(PCPartsRepository.coolingOptions)
    val compatibleCooling: LiveData<List<Cooling>> = _compatibleCooling

    private val _compatibleFans = MutableLiveData<List<Fan>>(PCPartsRepository.fans)
    val compatibleFans: LiveData<List<Fan>> = _compatibleFans

    // --- Selection functions ---
    fun selectCPU(cpu: CPU) {
        _selectedCPU.value = cpu
        _compatibleMotherboards.value = CompatibilityEngine.getCompatibleMotherboards(
            cpu,
            _selectedCase.value,
            PCPartsRepository.motherboards
        )
        _compatiblePSUs.value = CompatibilityEngine.getCompatiblePSUs(
            cpu,
            _selectedGPU.value,
            _selectedCase.value,
            PCPartsRepository.psus
        )
        _compatibleCooling.value = CompatibilityEngine.getCompatibleCooling(
            cpu,
            _selectedCase.value,
            PCPartsRepository.coolingOptions
        )
        _compatibleCases.value = CompatibilityEngine.getCompatibleCases(
            _selectedMotherboard.value,
            _selectedGPU.value,
            _selectedCooling.value,
            _selectedPSU.value,
            PCPartsRepository.cases
        )
    }

    fun selectGPU(gpu: GPU) {
        _selectedGPU.value = gpu
        _compatiblePSUs.value = CompatibilityEngine.getCompatiblePSUs(
            _selectedCPU.value,
            gpu,
            _selectedCase.value,
            PCPartsRepository.psus
        )
        _compatibleCases.value = CompatibilityEngine.getCompatibleCases(
            _selectedMotherboard.value,
            gpu,
            _selectedCooling.value,
            _selectedPSU.value,
            PCPartsRepository.cases
        )
    }

    fun selectMotherboard(mb: Motherboard) {
        _selectedMotherboard.value = mb
        _compatibleMemory.value =
            CompatibilityEngine.getCompatibleMemory(mb, PCPartsRepository.memoryModules)
        _compatibleCases.value = CompatibilityEngine.getCompatibleCases(
            mb,
            _selectedGPU.value,
            _selectedCooling.value,
            _selectedPSU.value,
            PCPartsRepository.cases
        )
    }

    fun selectMemory(mem: Memory) {
        _selectedMemory.value = mem
    }

    fun selectStorage(storage: Storage) {
        _selectedStorage.value = storage
    }

    fun selectPSU(psu: PSU) {
        _selectedPSU.value = psu
        _compatibleCases.value = CompatibilityEngine.getCompatibleCases(
            _selectedMotherboard.value,
            _selectedGPU.value,
            _selectedCooling.value,
            psu,
            PCPartsRepository.cases
        )
    }

    fun selectCase(case: Case) {
        _selectedCase.value = case
        _compatibleMotherboards.value = CompatibilityEngine.getCompatibleMotherboards(
            _selectedCPU.value,
            case,
            PCPartsRepository.motherboards
        )
        _compatibleCooling.value = CompatibilityEngine.getCompatibleCooling(
            _selectedCPU.value,
            case,
            PCPartsRepository.coolingOptions
        )
        _compatiblePSUs.value = CompatibilityEngine.getCompatiblePSUs(
            _selectedCPU.value,
            _selectedGPU.value,
            case,
            PCPartsRepository.psus
        )
        _compatibleCases.value = CompatibilityEngine.getCompatibleCases(
            _selectedMotherboard.value,
            _selectedGPU.value,
            _selectedCooling.value,
            _selectedPSU.value,
            PCPartsRepository.cases
        )
        _compatibleFans.value = CompatibilityEngine.getCompatibleFans(case, PCPartsRepository.fans)
    }

    fun selectCooling(cooling: Cooling) {
        _selectedCooling.value = cooling
        _compatibleCases.value = CompatibilityEngine.getCompatibleCases(
            _selectedMotherboard.value,
            _selectedGPU.value,
            cooling,
            _selectedPSU.value,
            PCPartsRepository.cases
        )
    }

    fun selectFan(fan: Fan) {
        _selectedFan.value = fan
    }

    fun selectAddOn(addOn: AddOn) {
        _selectedAddOn.value = addOn
    }

    // --- Reset build ---
    fun resetBuild() {
        _selectedCPU.value = null
        _selectedGPU.value = null
        _selectedMotherboard.value = null
        _selectedMemory.value = null
        _selectedStorage.value = null
        _selectedPSU.value = null
        _selectedCase.value = null
        _selectedCooling.value = null
        _selectedFan.value = null
        _selectedAddOn.value = null

        _compatibleMotherboards.value = PCPartsRepository.motherboards
        _compatibleMemory.value = PCPartsRepository.memoryModules
        _compatiblePSUs.value = PCPartsRepository.psus
        _compatibleCases.value = PCPartsRepository.cases
        _compatibleCooling.value = PCPartsRepository.coolingOptions
        _compatibleFans.value = PCPartsRepository.fans
    }

    // --- Save build to SharedPreferences ---
    fun saveBuild(context: Context) {
        val buildSummary = JSONObject().apply {
            put("CPU", _selectedCPU.value?.name ?: "")
            put("GPU", _selectedGPU.value?.name ?: "")
            put("Motherboard", _selectedMotherboard.value?.name ?: "")
            put("Memory", _selectedMemory.value?.name ?: "")
            put("Storage", _selectedStorage.value?.name ?: "")
            put("PSU", _selectedPSU.value?.name ?: "")
            put("Case", _selectedCase.value?.name ?: "")
            put("Cooling", _selectedCooling.value?.name ?: "")
            put("Fan", _selectedFan.value?.name ?: "")
            put("AddOn", _selectedAddOn.value?.name ?: "")
        }

        val prefs: SharedPreferences =
            context.getSharedPreferences("pc_builder_prefs", Context.MODE_PRIVATE)
        prefs.edit().putString("saved_build", buildSummary.toString()).apply()

        println("✅ Build saved: $buildSummary")
    }

    // --- Load build from SharedPreferences ---
    fun loadBuild(context: Context) {
        val prefs: SharedPreferences =
            context.getSharedPreferences("pc_builder_prefs", Context.MODE_PRIVATE)
        val savedJson = prefs.getString("saved_build", null) ?: return

        val obj = JSONObject(savedJson)

        // Rehydrate selections by looking up parts by name in repository
        _selectedCPU.value = PCPartsRepository.cpus.find { it.name == obj.getString("CPU") }
        _selectedGPU.value = PCPartsRepository.gpus.find { it.name == obj.getString("GPU") }
        _selectedMotherboard.value =
            PCPartsRepository.motherboards.find { it.name == obj.getString("Motherboard") }
        _selectedMemory.value =
            PCPartsRepository.memoryModules.find { it.name == obj.getString("Memory") }
        _selectedStorage.value =
            PCPartsRepository.storageDevices.find { it.name == obj.getString("Storage") }
        _selectedPSU.value = PCPartsRepository.psus.find { it.name == obj.getString("PSU") }
        _selectedCase.value = PCPartsRepository.cases.find { it.name == obj.getString("Case") }
        _selectedCooling.value =
            PCPartsRepository.coolingOptions.find { it.name == obj.getString("Cooling") }
        _selectedFan.value = PCPartsRepository.fans.find { it.name == obj.getString("Fan") }
        _selectedAddOn.value = PCPartsRepository.addOns.find { it.name == obj.getString("AddOn") }

        println("📂 Loaded Build: $obj")

        // Refresh compatibility lists based on loaded selections
        _compatibleMotherboards.value = CompatibilityEngine.getCompatibleMotherboards(
            _selectedCPU.value,
            _selectedCase.value,
            PCPartsRepository.motherboards
        )
        _compatibleMemory.value = CompatibilityEngine.getCompatibleMemory(
            _selectedMotherboard.value,
            PCPartsRepository.memoryModules
        )
        _compatiblePSUs.value = CompatibilityEngine.getCompatiblePSUs(
            _selectedCPU.value,
            _selectedGPU.value,
            _selectedCase.value,
            PCPartsRepository.psus
        )
        _compatibleCooling.value = CompatibilityEngine.getCompatibleCooling(
            _selectedCPU.value,
            _selectedCase.value,
            PCPartsRepository.coolingOptions
        )
        _compatibleCases.value = CompatibilityEngine.getCompatibleCases(
            _selectedMotherboard.value,
            _selectedGPU.value,
            _selectedCooling.value,
            _selectedPSU.value,
            PCPartsRepository.cases
        )
        _compatibleFans.value =
            CompatibilityEngine.getCompatibleFans(_selectedCase.value, PCPartsRepository.fans)
    }
}
