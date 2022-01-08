package com.example.attendanceapp.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.attendanceapp.Authentication.models.ClassInformation
import com.example.attendanceapp.Authentication.models.HomePage
import com.example.attendanceapp.R
import com.google.firebase.auth.FirebaseAuth


private  lateinit var mAuth: FirebaseAuth
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()
        val user = mAuth.currentUser

        Handler().postDelayed({
        if (user != null){
            val intent = Intent(this, HomePage::class.java)
            startActivity(intent)
            finish()

        }else{
            val intent1 = Intent(this, SignIn::class.java)
            startActivity(intent1)
            finish()

        } 

        },1000)
    }
}
