package com.example.note.data.api

import retrofit2.Call
import retrofit2.http.GET

interface ApiContract {
    @GET("posts/64")
    fun getNote(): Call<ResponseModel>
}