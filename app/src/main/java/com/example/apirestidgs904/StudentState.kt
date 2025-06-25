package com.example.apirestidgs904

data class StudentState(
    val students: List<Student> = emptyList(),
    val isLoading: Boolean = false
)
