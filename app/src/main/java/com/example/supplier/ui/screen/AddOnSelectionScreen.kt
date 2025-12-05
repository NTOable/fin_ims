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
import com.example.supplier.model.AddOn

@Composable
fun AddOnSelectionScreen(viewModel: PCBuilderViewModel) {
    val addOns by viewModel.addOnList.observeAsState(emptyList())
    val selectedAddOn by viewModel.selectedAddOn.observeAsState()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp)
    ) {
        items(addOns) { addOn: AddOn ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
                    .clickable { viewModel.selectAddOn(addOn) },
                colors = CardDefaults.cardColors(
                    containerColor = if (addOn == selectedAddOn)
                        MaterialTheme.colorScheme.primary.copy(alpha = 0.2f)
                    else
                        MaterialTheme.colorScheme.surface
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    AsyncImage(
                        model = addOn.imageUrl,
                        contentDescription = addOn.name,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(180.dp),
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = addOn.name,
                        style = MaterialTheme.typography.titleLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Interface: ${addOn.interfaceType}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                    Text(
                        text = "Requires slot: ${if (addOn.requiresSlot) "Yes" else "No"}",
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.colorScheme.onSurface
                    )
                }
            }
        }
    }
}
