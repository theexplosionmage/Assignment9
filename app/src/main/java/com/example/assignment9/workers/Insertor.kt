package com.example.assignment9.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.assignment9.App
import com.example.assignment9.api.RestClient
import com.example.assignment9.api.dto.ReqResData
import com.example.assignment9.api.dto.Resource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Insertor(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        RestClient.initClient()
        RestClient.reqResApi.getResources(1).enqueue(object : Callback<ReqResData<List<Resource>>> {
            override fun onResponse(
                call: Call<ReqResData<List<Resource>>>,
                response: Response<ReqResData<List<Resource>>>
            ) {
                if(response.isSuccessful){
                    response.body()?.data?.let {
                        for(resource in it){
                            App.instance.db.getResourceDao().insert(com.example.assignment9.room.Resource(0, name=resource.name, year=resource.year, color=resource.color, pantoneValue = resource.pantoneValue))
                        }
                    }
                }
            }
            override fun onFailure(call: Call<ReqResData<List<Resource>>>, t: Throwable) {
            }

        })

        return Result.success()
    }
}