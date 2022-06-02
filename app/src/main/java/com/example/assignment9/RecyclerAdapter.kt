package com.example.assignment9

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.assignment9.room.Resource

class RecyclerAdapter(private val resources: List<Resource>): RecyclerView.Adapter<RecyclerAdapter.ResourceViewHolder>(){
    companion object{
        const val RESOURCE_ID = "RESOURCE_ID"
    }
    class ResourceViewHolder(itemView: View): RecyclerView.ViewHolder(itemView), View.OnClickListener{
        init {
            itemView.setOnClickListener(this)
        }
        private val nameView: TextView = itemView.findViewById(R.id.txtName)
        private val colorView: TextView = itemView.findViewById(R.id.txtValue)
        private val pantoneView: TextView = itemView.findViewById(R.id.txtPantone)
        private lateinit var resource: Resource
        fun onBind(resource: Resource){
            nameView.text = resource.name
            colorView.text = resource.color
            pantoneView.text = resource.pantoneValue
            this.resource = resource
        }

        override fun onClick(clickedView: View?) {
            val context = itemView.context
            val intent = Intent(context, MainActivity::class.java)
            intent.putExtra(RESOURCE_ID, resource.id)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ResourceViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.items, parent, false)
        return ResourceViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ResourceViewHolder, position: Int) {
        holder.onBind(resources[position])
    }

    override fun getItemCount() = resources.size
}