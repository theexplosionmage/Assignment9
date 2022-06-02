package com.example.assignment9

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.work.*
import com.example.assignment9.workers.Deletor
import com.example.assignment9.workers.Insertor

class MainActivity : AppCompatActivity() {
    
    private lateinit var recyclerView: RecyclerView
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val insertor: OneTimeWorkRequest = OneTimeWorkRequestBuilder<Insertor>().build()
        val deletor: OneTimeWorkRequest = OneTimeWorkRequestBuilder<Deletor>().build()

        WorkManager.getInstance(this)
            .beginWith(insertor)
            .then(deletor)
            .enqueue()
        recyclerView = findViewById(R.id.recyclerView)
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(insertor.id).observe(
            this, Observer {
                if (it != null && it.state.isFinished){
                    val resources = App.instance.db.getResourceDao().selectAll()
                    recyclerView.adapter = RecyclerAdapter(resources)
                    recyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                }
            }
        )



    }
}