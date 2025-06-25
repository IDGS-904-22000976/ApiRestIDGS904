package com.example.apirestidgs904
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path


interface ApiService {
    //@GET("students")
    @GET("posts")
    suspend fun getStudents(): List<Student>

    //Se pone en el directorio del api, en este caso "posts"
    //En un post se tiene que hacer una calse StudentResponse
    //para las respuestas que se envian al servidor
    @POST("posts")
    fun addStudent(@Body misDatos: Student): Call<StudentResponse>

    @PUT("posts/{id}")
    fun updateStudent( @Path("id") ItemId: Int, @Body misDatos: Student): Call<StudentResponse>

    @DELETE("posts/{id}")
    fun deleteStudent(@Path("id") ItemId: Int): Call<Void>


    companion object {
        private var apiService: ApiService? = null
        private var url: String = "https://jsonplaceholder.typicode.com/"

        fun getInstance(): ApiService {
            if (apiService == null) {
                val retrofit = Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                // Asignar la instancia creada a apiService
                apiService = retrofit.create(ApiService::class.java)
            }
            return apiService!!
        }
    }
}