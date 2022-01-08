package com.example.attendanceapp.Authentication.models.DatabaseModels

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.attendanceapp.R

class DataBaseAdapter(
    var items:ArrayList<DatabaseModel>
) : RecyclerView.Adapter<DataBaseAdapter.dataViewholder>(){


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DataBaseAdapter.dataViewholder {
        return dataViewholder(LayoutInflater.from(parent.context).inflate(R.layout.classanddiv,parent,false))
    }

    override fun onBindViewHolder(holder: DataBaseAdapter.dataViewholder, position: Int) {
            val databaseModel:DatabaseModel = items[position]
            holder.Class2.text = databaseModel.Class
            holder.Div2.text = databaseModel.div
//            holder.Student2.text = databaseModel.student

    }

    override fun getItemCount(): Int {
        return items.size
    }

    public class dataViewholder(itemView: View): RecyclerView.ViewHolder(itemView){
        val Class2 = itemView.findViewById<TextView>(R.id.txt)
        val Student2 = itemView.findViewById<TextView>(R.id.txt2)
        val Div2 = itemView.findViewById<TextView>(R.id.txt3)
    }


}
// class ViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView)
// {
//     val Class2 = itemView.findViewById<TextView>(R.id.txt)
//     val Student2 = itemView.findViewById<TextView>(R.id.txt2)
//     val Div2 = itemView.findViewById<TextView>(R.id.txt3)

//     fun initialize(item: DatabaseModel, action:OnDataItemClickListner){
//         Class2.text = item.Class
//         Student2.text = item.Student
//         Div2.text = item.Div
//
//         itemView.setOnClickListener{
//             action.onItemClick(item,adapterPosition)
//         }
//
//     }
// }
//
