package aut.bme.hu.boltie

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import aut.bme.hu.boltie.ui.theme.BoltieTheme

class FunctionSelectorActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoltieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                }
            }
        }
    }
}

@Composable
fun CreateFunctionSelectorView() {
    Scaffold(topBar = {TopAppBar(title = { Text(text = "Funkcióválasztás")})})  {
        Text(text = "Oldaltartalom")
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview2() {
    BoltieTheme {
        CreateFunctionSelectorView()
    }
}