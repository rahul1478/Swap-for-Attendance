package com.example.attendanceapp.Authentication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.attendanceapp.Authentication.models.ClassInformation
import com.example.attendanceapp.Authentication.models.HomePage
import com.example.attendanceapp.R
import com.google.firebase.auth.FirebaseAuth

class DashBoardActivity : AppCompatActivity() {

    private lateinit var mAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dash_board)

        mAuth = FirebaseAuth.getInstance()
        val currentUser = mAuth.currentUser

        val userId = findViewById<TextView>(R.id.id)
        val name = findViewById<TextView>(R.id.name)
        val mail = findViewById<TextView>(R.id.emailid)
        val img = findViewById<ImageView>(R.id.img1)
        val btn = findViewById<Button>(R.id.btn)


        userId.text = currentUser?.uid
        name.text = currentUser?.displayName
        mail.text = currentUser?.email



        Glide.with(this).load(currentUser?.photoUrl).into(img)

        btn.setOnClickListener {
            mAuth.signOut()

            val intent =  Intent(this, SignIn::class.java)
            startActivity(intent)
            finish()
        }







    }
}