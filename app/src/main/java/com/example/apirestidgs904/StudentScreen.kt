package com.example.apirestidgs904

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

@Composable
fun studentScreen(studentViewModel: StudentViewModel){
    val context = LocalContext.current

    val state = studentViewModel.state

    if(state.isLoading) {
        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator()
        }
    }

    LazyColumn (
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ){
        itemsIndexed(items = studentViewModel.response){ index, item ->
            Card(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxSize()
                    .background(Color.White)
            ) {
                Column (
                    modifier = Modifier
                        .padding(10.dp),
                    verticalArrangement = Arrangement.Center
                ){
                    Text(text = item.userId.toString())
                    Text(text = item.id.toString())
                    Text(text = item.title)
                    Text(text = item.body)
                }
            }
        }
    }


    BtnAdd{
        val apiService = ApiService.getInstance()
        val estudiante = Student(
            userId = 123,
            title = "Mi titulo",
            body = "Mi contenido"
        )

        apiService.addStudent(estudiante).enqueue(object : Callback<StudentResponse>{
            override fun onResponse(
                call: Call<StudentResponse>,
                response: Response<StudentResponse>
            ) {
                if (response.isSuccessful){
                    val resultado = response.body()
                    Toast.makeText(context,"OK: $resultado", Toast.LENGTH_LONG).show()
                }else{
                    println("Error: ${response.errorBody()?.string()}")
                }
            }
            override fun onFailure(call: Call<StudentResponse>, t: Throwable) {
                println("Error: ${t.message}")
            }
        })
    }

}

@Composable
fun BtnAdd(onClick: () -> Unit) {
    SmallFloatingActionButton(
        modifier = Modifier.offset(x = 250.dp, y = 10.dp),
        onClick = { onClick()},
        shape = CircleShape
    ) {
        Icon(Icons.Filled.Add,"")
    }
}
