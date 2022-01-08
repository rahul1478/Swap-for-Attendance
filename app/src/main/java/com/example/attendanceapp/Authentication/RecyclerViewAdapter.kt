package com.example.attendanceapp.Authentication

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.attendanceapp.Authentication.Data.AttendanceData
import com.example.attendanceapp.Authentication.models.DatabaseModels.AttebdanceModels
import com.example.attendanceapp.Authentication.models.DatabaseModels.DatabaseModel
import com.example.attendanceapp.Authentication.models.HomePage
import com.example.attendanceapp.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class RecyclerViewAdapter(
    options: FirestoreRecyclerOptions<DatabaseModel>,
    var listener : OnDataItemClickListner
)

    :FirestoreRecyclerAdapter<DatabaseModel,RecyclerViewAdapter.dataViewHolder>(options) {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): dataViewHolder {
        val viewHolder = dataViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.classanddiv, parent, false)
        )

        viewHolder.itemView.setOnClickListener {
            listener.onItemClick(snapshots.getSnapshot(viewHolder.adapterPosition),viewHolder.adapterPosition)
        }

//        viewHolder.itemView.setOnClickListener {
//            listner.onItemClick(snapshots.getSnapshot(viewHolder.adapterPosition).id)
//        }


        return viewHolder
    }

    override fun onBindViewHolder(holder: dataViewHolder, position: Int, model: DatabaseModel) {
        val Class2 = holder.itemView.findViewById<TextView>(R.id.txt)
        val Div2 = holder.itemView.findViewById<TextView>(R.id.txt2)
        val Student2 = holder.itemView.findViewById<TextView>(R.id.txt3)


        Class2.setText("Class      :" + "  " + model.Class)
        Div2.setText("Div          :" + "  " + model.div)
        Student2.setText("Student  :"+"  "+model.student)

        val auth = Firebase.auth
        val currentUser = auth.currentUser!!.uid
//        var isclick = model.student.contains(currentUser)



    }



    class dataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val Class2 = itemView.findViewById<TextView>(R.id.txt)
        val Student2 = itemView.findViewById<TextView>(R.id.txt2)
        val Div2 = itemView.findViewById<TextView>(R.id.txt3)



//        fun NoteHolder(itemView: View){
//
//            val Class2 = itemView.findViewById<TextView>(R.id.txt)
//            val Student2 = itemView.findViewById<TextView>(R.id.txt2)
//            val Div2 = itemView.findViewById<TextView>(R.id.txt3)
//
//
//
//        }


    }
     interface OnDataItemClickListner{
        fun onItemClick(documetSnapshot: DocumentSnapshot,position: Int)

    }

    public fun setOnItemClickListener(listener: OnDataItemClickListner){
        this.listener = listener

    }

}






//
