package com.example.attendanceapp.Authentication.models

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attendanceapp.Authentication.DashBoardActivity
import com.example.attendanceapp.Authentication.Data.AttendanceData
import com.example.attendanceapp.Authentication.Data.TakeAttendance
import com.example.attendanceapp.Authentication.RecyclerViewAdapter

import com.example.attendanceapp.Authentication.models.DatabaseModels.DatabaseModel


import com.example.attendanceapp.R
import com.firebase.ui.firestore.FirestoreRecyclerOptions
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.*

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class HomePage : AppCompatActivity(), RecyclerViewAdapter.OnDataItemClickListner {

//      private lateinit var addData: AddData
//    private lateinit var recyclerView: RecyclerView
//    private lateinit var dataBaseAdapter: DataBaseAdapter
//    private lateinit var addData: AddData
//
//    lateinit var dataList: ArrayList<DatabaseModel>
    private lateinit var mAuth: FirebaseAuth

    private lateinit var adapter:RecyclerViewAdapter
    private  var  db = Firebase.firestore


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_page)

        val addimg = findViewById<ImageView>(R.id.add)
        mAuth = Firebase.auth
        val saveData = findViewById<ImageView>(R.id.savedData)

        saveData.setOnClickListener {
            startActivity(Intent(this,AttendanceData::class.java))
        }



        addimg.setOnClickListener {
            var intent = Intent(this,ClassInformation::class.java)
            startActivity(intent)
        }

        setUpRecyclerViewHolder()

//        dataBaseAdapter = DataBaseAdapter(dataList)
//        recyclerView.layoutManager = LinearLayoutManager(this)
//        recyclerView.adapter = dataBaseAdapter
//        recyclerView.setHasFixedSize(true)
//
//        dataList = arrayListOf()
//
//        recyclerView.adapter = dataBaseAdapter


    }



    private fun setUpRecyclerViewHolder() {
        val dataCollection: CollectionReference = db.collection( "ClassInfo")
//        val query = dataCollection.orderBy("class",Query.Direction.DESCENDING)
        var recyclerViewOptions = FirestoreRecyclerOptions.Builder<DatabaseModel>().setQuery(dataCollection,DatabaseModel::class.java)
            .setLifecycleOwner(this).build()

        adapter = RecyclerViewAdapter(recyclerViewOptions,this)
        val rycl = findViewById<RecyclerView>(R.id.rycl)



        rycl.adapter = adapter
        rycl.layoutManager = LinearLayoutManager(this)



    }

    override fun onStart() {
        super.onStart()
        adapter.startListening()
    }

    override fun onStop() {
        super.onStop()
        adapter.stopListening()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var intent = Intent(this,DashBoardActivity::class.java)
        startActivity(intent)
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClick(documetSnapshot: DocumentSnapshot, position: Int) {
        val currentUser = mAuth.currentUser
        val uid = currentUser?.uid
        val post = documetSnapshot.toObject(DatabaseModel::class.java)
        val id:String = documetSnapshot.id
        val path:String = documetSnapshot.reference.path
        val intent = Intent(this,TakeAttendance::class.java)
        intent.putExtra("Id",id)
        intent.putExtra("Path",path)
        intent.putExtra("Class",post?.Class)
//        intent.putExtra("Div",post?.div)
        intent.putExtra("Student",post?.student)
        startActivity(intent)
//        Toast.makeText(this,id,Toast.LENGTH_SHORT).show()




    }

}



