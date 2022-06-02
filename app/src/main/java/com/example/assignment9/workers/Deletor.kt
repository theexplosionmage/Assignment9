package com.example.assignment9.workers

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.assignment9.App

class Deletor(context: Context, params: WorkerParameters): Worker(context, params) {
    override fun doWork(): Result {
        App.instance.db.getResourceDao().delete()
        return Result.success()
    }
}