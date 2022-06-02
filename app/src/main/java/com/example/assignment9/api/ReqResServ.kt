package com.example.assignment9.api

import com.example.assignment9.api.dto.ReqResData
import com.example.assignment9.api.dto.Resource
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReqResServ{
    @GET("unknown")
    fun getResources(@Query("page")page: Int):Call<ReqResData<List<Resource>>>
    @GET("unknown/{unknownId}")
    fun getResource(@Path("unknownId")id: Long):Call<ReqResData<Resource>>
}