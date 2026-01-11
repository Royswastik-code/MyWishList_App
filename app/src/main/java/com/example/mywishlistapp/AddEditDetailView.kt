package com.example.mywishlistapp

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mywishlistapp.Data.Wish
import kotlinx.coroutines.launch

@Composable
fun AddEditDetailsView(
    id: Long,
    viewModel: WishViewModel,
    navController: NavController
) {

    val snackMessage=remember{
        mutableStateOf("")
    }
    val scope=rememberCoroutineScope()
    val scaffoldState= rememberScaffoldState()

    if(id!=0L){
        val wish=viewModel.getAWishById(id).collectAsState(initial = Wish(0L,"",""))
        viewModel.wishTitleState=wish.value.title
        viewModel.wishDescriptionState=wish.value.description
    }
    else{
        viewModel.wishTitleState=""
        viewModel.wishDescriptionState=""
    }
    Scaffold(
        topBar = {
            AppBarView(
                title = if (id != 0L)
                    stringResource(id = R.string.update_wish)
                else
                    stringResource(id = R.string.add_wish)
            )
            {
                navController.navigateUp()
            }
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .wrapContentSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Spacer(modifier = Modifier.height(10.dp))
            // Example TextField usage
            WishTextField(
                label = "Title",
                value = viewModel.wishTitleState,
                onValueChange = {
                    viewModel.onWishTitleChanged(it)
                }
            )
            Spacer(Modifier.height(10.dp))
            WishTextField(
                label = "Description",
                value = viewModel.wishDescriptionState,
                onValueChange = {
                    viewModel.onWishDescriptionChanged(it)
                }
            )
            Spacer(Modifier.height(10.dp))

            Button(onClick = {
                if(viewModel.wishTitleState.isNotEmpty() &&
                    viewModel.wishDescriptionState.isNotEmpty()){
                    
                    if(id!=0L){
                        //update wish
                        viewModel.updateWish(
                            Wish(
                                id=id,
                                title=viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                        )
                        )
                    }
                    else{
                        //add wish
                        viewModel.addWish(
                            Wish(
                                title=viewModel.wishTitleState.trim(),
                                description = viewModel.wishDescriptionState.trim()
                            )
                        )
                        snackMessage.value="Wish has been created successfully"
                    }
                }
                else{
                    snackMessage.value="Enter fields to create a wish"
                }
                
                scope.launch{
                    //scaffoldState.snackbarHostState.showSnackbar(snackMessage.value)
                    navController.navigateUp()
                }
                
            }) {
                Text(
                    text= if(id != 0L)
                        stringResource(id = R.string.update_wish)
                    else
                        stringResource(id = R.string.add_wish),
                    style= TextStyle(
                        fontSize = 18.sp)
                )
            }
        }
    }
}

@Composable
fun WishTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = { Text(text = label, color = Color.Black) },
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedBorderColor = colorResource(id = R.color.black),
            unfocusedBorderColor = colorResource(id = R.color.black),
            cursorColor = colorResource(id = R.color.black),
            focusedLabelColor = colorResource(id = R.color.black),
            unfocusedLabelColor = colorResource(id = R.color.black)
        )
    )
}

@Preview(showBackground = true)
@Composable
fun WishTextFieldPreview() {
    WishTextField(label = "Title", value = "Hello", onValueChange = {})
}
