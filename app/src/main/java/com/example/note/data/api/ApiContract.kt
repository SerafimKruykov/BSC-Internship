package com.example.note.data.api

import com.example.note.Constants.Api.URL_TARGET
import retrofit2.Call
import retrofit2.http.GET

interface ApiContract {
    @GET(URL_TARGET)
    fun getNote(): Call<ResponseModel>
}