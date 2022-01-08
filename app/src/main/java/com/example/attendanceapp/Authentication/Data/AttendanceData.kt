package com.example.attendanceapp.Authentication.Data

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.system.Os.remove
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attendanceapp.Authentication.RecyclerViewAdapter
import com.example.attendanceapp.Authentication.StudentAttendance
import com.example.attendanceapp.Authentication.models.DatabaseModels.AttebdanceModels
import com.example.attendanceapp.Authentication.models.DatabaseModels.DatabaseModel
import com.example.attendanceapp.Authentication.models.HomePage
import com.example.attendanceapp.R
import com.firebase.ui.firestore.FirestoreRecyclerAdapter
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.core.View
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AttendanceData : AppCompatActivity(), RecyclerviewAdapter2.OnDataItemClickListners {


    private lateinit var adapter:RecyclerviewAdapter2


    private  var  db1 = Firebase.firestore
    val dataCollection1: CollectionReference = db1.collection( "Dates")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_attendance_data)

        setUpRecyclerViewHolder1()
}



    private fun setUpRecyclerViewHolder1() {
        val dataCollection: CollectionReference = db1.collection( "Dates")
//        val query = dataCollection.orderBy("class",Query.Direction.DESCENDING)
        var recyclerViewOptions = FirestoreRecyclerOptions.Builder<AttebdanceModels>().setQuery(dataCollection1,AttebdanceModels::class.java)
            .setLifecycleOwner(this).build()

        adapter = RecyclerviewAdapter2(recyclerViewOptions,this)
        val rycl2 = findViewById<RecyclerView>(R.id.rycl2)



        rycl2.adapter = adapter
        rycl2.layoutManager = LinearLayoutManager(this)



    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onItemClick(documetSnapshot: DocumentSnapshot, position: Int) {
        val post = documetSnapshot.toObject(AttebdanceModels::class.java)
        val id:String = documetSnapshot.id
        val path:String = documetSnapshot.reference.path
        val intent = Intent(this,StudentAttendance::class.java)
        intent.putExtra("Id",id)
        intent.putExtra("Path",path)
        intent.putExtra("Class",post?.classes)
        intent.putExtra("Dates",post?.dates)
        intent.putExtra("Ss",post?.student)
        startActivity(intent)
    }

}
