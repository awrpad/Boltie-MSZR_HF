package aut.bme.hu.boltie

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import aut.bme.hu.boltie.ui.theme.BoltieTheme

class LoginActivityCompose : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BoltieTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CreateLoginView {
                        // TODO: Get the user first
                        val intent = Intent(
                            this,
                            FunctionSelectorActivityCompose::class.java
                        )
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun CreateLoginView(onLogin: () -> Unit) {
    var userName by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(top = 48.dp, bottom = 48.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = userName,
            onValueChange = { userName = it },
            label = {Text(text = stringResource(id = R.string.txt_user_name))}
        )
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = {Text(text = stringResource(id = R.string.txt_password))},
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
        )
        Spacer(modifier = Modifier.height(48.dp))
        Button(
            modifier = Modifier
                .wrapContentWidth(Alignment.CenterHorizontally),
            onClick = { onLogin() }
        ) {
            Text(text = stringResource(id = R.string.login))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    BoltieTheme {
        //Greeting("Android")
        //CreateLoginView()
    }
}