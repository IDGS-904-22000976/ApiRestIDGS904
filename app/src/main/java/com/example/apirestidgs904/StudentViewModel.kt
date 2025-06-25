package com.example.apirestidgs904

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class StudentViewModel: ViewModel() {
    //variable de estado mutable
    //Cuando cambia recompone los componentes del estado
    var state by mutableStateOf(StudentState())
        private set

    var response:List<Student> by mutableStateOf(listOf())
        private set

    //init se ejecuta despues del cosntructor, si es que existe
    init {
        //corrutina para viewModel, se cansela cuando viewModel es limpiado
        //Corrutina, programacion asincrona
        viewModelScope.launch {
            //cambia el estado a cargando
            state = state.copy(isLoading = true)
            //llama al servicio de api
            response = ApiService.getInstance().getStudents()
            //cambia el estado a no cargando
            state = state.copy(isLoading = false, students = response)
        }

    }
}