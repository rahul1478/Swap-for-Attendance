package com.example.attendanceapp.Authentication.Data

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.attendanceapp.Authentication.models.DatabaseModels.Students
import com.example.attendanceapp.R

class SwpieToAttendAdapter(private val items:ArrayList<String>) : RecyclerView.Adapter<SwpieToAttendAdapter.ExampleViewHolder>()
{


    class ExampleViewHolder(itemView: View) :RecyclerView.ViewHolder(itemView){
        val key:TextView = itemView.findViewById(R.id.swipetext)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val view =  LayoutInflater.from(parent.context).inflate(R.layout.swipe_texts,parent,false)
        return ExampleViewHolder(view)
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val key:TextView = holder.itemView.findViewById(R.id.swipetext)
            key.text = items[position]


    }

    override fun getItemCount() = items.size
}