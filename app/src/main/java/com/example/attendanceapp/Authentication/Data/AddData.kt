package com.example.attendanceapp.Authentication.Data

import android.content.Context
import android.content.Intent
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import com.example.attendanceapp.Authentication.models.DatabaseModels.DatabaseModel
import com.example.attendanceapp.Authentication.models.HomePage
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await

class AddData {
    val db1 = FirebaseFirestore.getInstance()
    private lateinit var mAuth: FirebaseAuth
    private lateinit var homePage: HomePage

    fun getDateById(items : String): Task<DocumentSnapshot> {
        return db1.document(items).get()

    }
//
//    fun openActivity(items : String){
//        val currentUser = mAuth.currentUser!!.uid
//        GlobalScope.launch {
//            val post = getDateById(items).await().toObject(DatabaseModel::class.java)!!
//            var isclick = post!!.student.contains(currentUser)
//            if(isclick) {
//                post.student.contains(currentUser)
//                homePage.click()
//
//            } else {
//
//            }
//            db1.document(items).set(post)
//
//        }
//    }


}

