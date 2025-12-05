package com.example.supplier.compatibility

import com.example.supplier.model.CPU
import com.example.supplier.model.Case
import com.example.supplier.model.Cooling
import com.example.supplier.model.Fan
import com.example.supplier.model.GPU
import com.example.supplier.model.Memory
import com.example.supplier.model.Motherboard
import com.example.supplier.model.PSU

object CompatibilityEngine {

    // Typed constant for storage interfaces
    private val supportedInterfaces: Set<String> = setOf("NVMe", "SATA")

    fun getCompatibleMotherboards(cpu: CPU?, case: Case?, allMotherboards: List<Motherboard>): List<Motherboard> {
        return allMotherboards.filter { mb ->
            (cpu == null || mb.socket == cpu.socket) &&
                    (case == null || mb.formFactor == case.formFactor) &&
                    (cpu == null || mb.supportsPcieVersion >= cpu.maxMemoryChannels)
        }
    }

    fun getCompatibleMemory(mb: Motherboard?, allMemory: List<Memory>): List<Memory> {
        return allMemory.filter { mem ->
            (mb == null || mem.type == mb.ramType) &&
                    (mb == null || mem.capacityGb <= mb.maxMemoryGb) &&
                    (mb == null || mem.modules <= mb.ramSlots) &&
                    (mb == null || mem.speedMHz <= 6400)
        }
    }

    fun getCompatiblePSUs(cpu: CPU?, gpu: GPU?, case: Case?, allPSUs: List<PSU>): List<PSU> {
        val requiredWattage = (cpu?.tdpWatt ?: 0) + (gpu?.powerDrawWatt ?: 0) + 150
        return allPSUs.filter { psu ->
            psu.wattage >= requiredWattage &&
                    (case == null || psu.size == case.supportedPsuSize) &&
                    (gpu == null || psu.pcieConnectors >= gpu.requiredPcieConnectors)
        }
    }

    fun getCompatibleCases(
        mb: Motherboard?, gpu: GPU?, cooling: Cooling?, psu: PSU?, allCases: List<Case>
    ): List<Case> {
        return allCases.filter { case ->
            (mb == null || case.formFactor == mb.formFactor) &&
                    (gpu == null || gpu.lengthMm <= case.maxGpuLengthMm) &&
                    (cooling == null || cooling.heightMm <= case.maxCoolingHeightMm) &&
                    (psu == null || psu.size == case.supportedPsuSize) &&
                    (mb == null || mb.storageInterfaces.all { it: String -> supportedInterfaces.contains(it) })
        }
    }

    fun getCompatibleCooling(cpu: CPU?, case: Case?, allCooling: List<Cooling>): List<Cooling> {
        return allCooling.filter { cooling ->
            (cpu == null || cooling.tdpSupportWatt >= cpu.tdpWatt) &&
                    (cpu == null || cooling.supportedSockets.contains(cpu.socket)) &&
                    (case == null || cooling.heightMm <= case.maxCoolingHeightMm)
        }
    }

    fun getCompatibleFans(case: Case?, allFans: List<Fan>): List<Fan> {
        return allFans.filter { fan ->
            (case == null || case.supportedFanSizes.contains(fan.sizeMm.toInt())) &&
                    (case == null || fan.rgbSupport)
        }
    }

}
