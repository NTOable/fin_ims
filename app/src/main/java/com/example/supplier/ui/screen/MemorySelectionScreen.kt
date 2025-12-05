package com.example.supplier.ui.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.supplier.viewmodel.PCBuilderViewModel
import com.example.supplier.model.Memory

@Composable
fun MemorySelectionScreen(viewModel: PCBuilderViewModel) {
    val memoryModules by viewModel.compatibleMemory.observeAsState(emptyList())
    val selectedMemory by viewModel.selectedMemory.observeAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(memoryModules) { mem: Memory ->
            val isSelected = mem == selectedMemory
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { viewModel.selectMemory(mem) },
                colors = CardDefaults.cardColors(
                    containerColor = if (isSelected)
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                    else
                        MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    AsyncImage(
                        model = mem.imageUrl,
                        contentDescription = mem.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp)
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = mem.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = if (isSelected)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Capacity: ${mem.capacityGb} GB",
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isSelected)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Type: ${mem.type}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isSelected)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Modules: ${mem.modules}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isSelected)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Speed: ${mem.speedMHz} MHz",
                        style = MaterialTheme.typography.bodyLarge,
                        color = if (isSelected)
                            MaterialTheme.colorScheme.onPrimary
                        else
                            MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
