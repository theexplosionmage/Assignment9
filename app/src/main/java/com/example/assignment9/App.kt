package com.example.assignment9

import android.app.Application
import androidx.room.Room
import com.example.assignment9.room.AppDatabase

class App: Application() {

    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()

        instance = this

        db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "Database1"
        ).allowMainThreadQueries().build()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}