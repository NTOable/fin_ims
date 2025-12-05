package com.example.supplier.ui.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.graphics.Color
import com.example.supplier.viewmodel.PCBuilderViewModel

@Composable
fun OverviewScreen(viewModel: PCBuilderViewModel) {
    val context = LocalContext.current

    val selectedCPU by viewModel.selectedCPU.observeAsState()
    val selectedGPU by viewModel.selectedGPU.observeAsState()
    val selectedMotherboard by viewModel.selectedMotherboard.observeAsState()
    val selectedMemory by viewModel.selectedMemory.observeAsState()
    val selectedStorage by viewModel.selectedStorage.observeAsState()
    val selectedPSU by viewModel.selectedPSU.observeAsState()
    val selectedCase by viewModel.selectedCase.observeAsState()
    val selectedCooling by viewModel.selectedCooling.observeAsState()
    val selectedFan by viewModel.selectedFan.observeAsState()
    val selectedAddOn by viewModel.selectedAddOn.observeAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp)
    ) {
        Text(
            text = "Build Overview",
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onSurface,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        BuildPartRow("CPU", selectedCPU?.name)
        BuildPartRow("GPU", selectedGPU?.name)
        BuildPartRow("Motherboard", selectedMotherboard?.name)
        BuildPartRow("Memory", selectedMemory?.name)
        BuildPartRow("Storage", selectedStorage?.name)
        BuildPartRow("PSU", selectedPSU?.name)
        BuildPartRow("Case", selectedCase?.name)
        BuildPartRow("Cooling", selectedCooling?.name)
        BuildPartRow("Fan", selectedFan?.name)
        BuildPartRow("Add-On", selectedAddOn?.name)

        Spacer(modifier = Modifier.height(24.dp))

        val missingParts = mutableListOf<String>()
        if (selectedCPU == null) missingParts.add("CPU")
        if (selectedGPU == null) missingParts.add("GPU")
        if (selectedMotherboard == null) missingParts.add("Motherboard")
        if (selectedMemory == null) missingParts.add("Memory")
        if (selectedStorage == null) missingParts.add("Storage")
        if (selectedPSU == null) missingParts.add("PSU")
        if (selectedCase == null) missingParts.add("Case")
        if (selectedCooling == null) missingParts.add("Cooling")
        if (selectedFan == null) missingParts.add("Fan")
        if (selectedAddOn == null) missingParts.add("Add-On")

        if (missingParts.isEmpty()) {
            Text(
                text = "Congratulations! Your build is complete!",
                style = MaterialTheme.typography.titleMedium.copy(
                    fontWeight = FontWeight.Bold
                ),
                color = Color.White,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        } else {
            Text(
                text = "Missing parts: ${missingParts.joinToString()}",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }

        Button(
            onClick = { viewModel.resetBuild() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Reset Build", style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.saveBuild(context) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Save Build", style = MaterialTheme.typography.bodyLarge)
        }

        Spacer(modifier = Modifier.height(12.dp))

        Button(
            onClick = { viewModel.loadBuild(context) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Load Build", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
private fun BuildPartRow(label: String, value: String?) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "$label:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.width(120.dp),
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = value ?: "None",
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}
