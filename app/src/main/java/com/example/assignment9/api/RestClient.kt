package com.example.assignment9.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RestClient {
    private lateinit var retrofit: Retrofit
    private lateinit var okHttpClient: OkHttpClient

    fun initClient(){
        okHttpClient = OkHttpClient.Builder().build()
        retrofit = Retrofit.Builder().baseUrl("https://reqres.in/api/").addConverterFactory(GsonConverterFactory.create()).build()
    }

    private fun<S> getService(serviceClass: Class<S>): S{
        return retrofit.create(serviceClass)
    }

    val reqResApi: ReqResServ
        get() = getService(ReqResServ::class.java)
}