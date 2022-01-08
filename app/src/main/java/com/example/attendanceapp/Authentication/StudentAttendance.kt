package com.example.attendanceapp.Authentication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.core.util.rangeTo
import com.example.attendanceapp.R

class StudentAttendance : AppCompatActivity() {
    private lateinit var data:ArrayList<String>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_student_attendance)

        val text = findViewById<TextView>(R.id.StudentDeatials)

        intent.getStringExtra("Id")
        intent.getStringExtra("Path")
        val data = intent.getStringArrayListExtra("Ss")
        var key = ""

//        val data =intent.getStringExtra("Ss")

            for (i in data!!){
                key += "\n" + i
            }

        key += "\n\n"

        text.setText(""+key)


    }

}