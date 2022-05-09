package aut.bme.hu.boltie

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import aut.bme.hu.boltie.model.Role
import aut.bme.hu.boltie.model.User
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
                    val u = User(
                        31415,
                        "x@y.z",
                        "Pista",
                        "Kis",
                        "Kispista",
                        "", ""
                    )
                    CreateFunctionSelectorView(context = this, user = u, role = Role.Manager)
                }
            }
        }
    }
}

@Composable
fun CreateFunctionSelectorView(context: Context, user: User, role: Role) {
    val buttonModifier = Modifier
        .fillMaxWidth()
        .width(IntrinsicSize.Max)
        .requiredHeight(64.dp)
        .padding(horizontal = 16.dp)
    Scaffold(topBar = {TopAppBar(title = {Text(text = stringResource(id = R.string.fn_select))})}) {
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            stringResource(id = R.string.greeting, user.nickName),
            textAlign = TextAlign.Center,
            fontSize = 30.sp,
            modifier = Modifier
                .padding(8.dp)
                .fillMaxWidth()
        ) // TODO: Horizontally center
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 48.dp, bottom = 48.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            if(role.ordinal >= Role.Seller.ordinal) {
                Button(modifier = buttonModifier, onClick = {
                    val intent = Intent(context, BarcodeActivityCompose::class.java)
                    context.startActivity(intent)
                }) {
                    Text(text = stringResource(id = R.string.sale))
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
            if(role.ordinal >= Role.Loader.ordinal) {
                Button(modifier = buttonModifier, onClick = { /*TODO*/ }) {
                    Text(text = stringResource(id = R.string.restock))
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
            if(role.ordinal >= Role.Loader.ordinal) {
                Button(modifier = buttonModifier, onClick = { /*TODO*/ }) {
                    Text(text = stringResource(id = R.string.waste))
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
            if(role.ordinal >= Role.Seller.ordinal) {
                Button(modifier = buttonModifier, onClick = { /*TODO*/ }) {
                    Text(text = stringResource(id = R.string.buy_back))
                }
                Spacer(modifier = Modifier.height(24.dp))
            }
            Button(modifier = buttonModifier, onClick = { /*TODO*/ }) {
                Text(text = stringResource(R.string.select_workplace))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FSPreview() {
    BoltieTheme {
        var u = User(
            1,
            "x@y.z",
            "Pista",
            "Kis",
            "Kispista",
            "", ""
        )
        //CreateFunctionSelectorView(u, Role.Manager)
    }
}