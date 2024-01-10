package com.example.codapizza.ui

import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.example.codapizza.R
import com.example.codapizza.model.Topping
import com.example.codapizza.model.ToppingPlacement

@Composable
fun ToppingPlacementDialog(topping: Topping, onDismissRequest: () -> Unit) {
    Dialog(onDismissRequest = onDismissRequest ) {
        Card{
            Column {
                val toppingName = stringResource(topping.toppingName)
                Text(
                    text = stringResource(R.string.placement_prompt, toppingName),
                    style = MaterialTheme.typography.subtitle1,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(24.dp)
                )
                ToppingPlacement.values().forEach { placement ->
                    ToppingPlacementOption(
                        placementName = placement.label,
                        onClick = { /* TODO */ }
                    )
                }
                ToppingPlacementOption(
                    placementName = R.string.placement_none,
                    onClick = { /* TODO */ }
                )
            }
        }
    }
}

@Composable
private fun ToppingPlacementOption(
    @StringRes placementName: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = onClick,
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = stringResource(placementName),
            modifier = Modifier.padding(8.dp)
        )
    }
}