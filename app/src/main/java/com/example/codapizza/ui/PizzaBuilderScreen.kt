package com.example.codapizza.ui


import android.icu.text.NumberFormat
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.codapizza.R
import com.example.codapizza.model.Pizza
import com.example.codapizza.model.Topping
import com.example.codapizza.model.ToppingPlacement


@Preview
@Composable
fun PizzaBuilderScreen(
    modifier: Modifier = Modifier
) {
    var pizza by rememberSaveable{
        mutableStateOf(Pizza())
    }

    Scaffold(modifier = modifier ,
        topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.app_name)) })
                 } ,
        content = { innerPadding ->
            Column(modifier = modifier.padding(innerPadding)) {
                ToppingList(
                    pizza = pizza,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f, fill = true)
                ){
                    pizza = it
                }
                OrderButton(
                    pizza = pizza,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                )
            }
        }
    )
}

@Composable
private fun OrderButton(
    pizza: Pizza,
    modifier: Modifier = Modifier
){
    Button(
        modifier = modifier,
        onClick = {
            //TODO
        }
    ) {
        val currencyFormatter = remember {
            NumberFormat.getCurrencyInstance()
        }
        val price = currencyFormatter.format(pizza.price)
        Text(text = stringResource(id = R.string.place_order_button, price).toUpperCase(Locale.current))
    }
}

@Composable
private fun  ToppingList(
    pizza: Pizza,
    modifier: Modifier = Modifier,
    onEditPizza: (Pizza) -> (Unit)
){
    var toppingBeingAdded by rememberSaveable { mutableStateOf<Topping?>(null) }
    toppingBeingAdded?.let { topping ->
        ToppingPlacementDialog(
            onDismissRequest = { toppingBeingAdded = null },
            topping = topping,
            onSetToppingPlacement = { placement ->
                onEditPizza(pizza.withTopping(topping,placement))
            }
        )
    }
    LazyColumn(modifier = modifier){
        item {
            PizzaHeroImage(
                pizza = pizza,
                modifier = Modifier.padding(16.dp)
            )
        }
        items(Topping.values()){toppingInColumn ->
          ToppingCell(topping = toppingInColumn,
              placement = pizza.toppings[toppingInColumn], onClickTopping = {
                  toppingBeingAdded = toppingInColumn
              }
          )
      }
    }
}