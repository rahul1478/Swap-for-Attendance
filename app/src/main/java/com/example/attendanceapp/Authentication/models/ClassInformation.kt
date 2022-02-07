package com.example.attendanceapp.Authentication.models


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.attendanceapp.Authentication.models.DatabaseModels.DatabaseModel
import com.example.attendanceapp.Authentication.models.DatabaseModels.Students
import com.example.attendanceapp.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.android.gms.tasks.Task
import com.google.common.reflect.ClassPath
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GetTokenResult
import com.google.firebase.database.DatabaseReference
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.auth.User
import java.lang.Exception

class ClassInformation : AppCompatActivity() {

    private lateinit var mAuth1:FirebaseAuth
    private lateinit var mAuth:FirebaseFirestore
    private lateinit var reference: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_class_information)

        mAuth1 = FirebaseAuth.getInstance()
        val currentUser = mAuth1.currentUser
        mAuth = FirebaseFirestore.getInstance()

        val start = findViewById<Button>(R.id.senddata)
        val Class = findViewById<EditText>(R.id.class1)
        val Div = findViewById<EditText>(R.id.div)
        val Student = findViewById<EditText>(R.id.noOfStudent)


        start.setOnClickListener {



                    val Class1 = Class.text.toString().trim()
                    val Div1 = Div.text.toString().trim()
                    val Student1 = Student.text.toString().trim()


            if (Class1.isNotEmpty() && Div1.isNotEmpty() && Student1.isNotEmpty()) {
                var model = DatabaseModel(Class1, Div1, Student1)
                        mAuth.collection("ClassInfo/").add(model)
                            .addOnSuccessListener(object : OnSuccessListener<DocumentReference> {
                                override fun onSuccess(p0: DocumentReference?) {

                                    Class.setText("")
                                    Div.setText("")



                                    Toast.makeText(applicationContext, "Data Saved", Toast.LENGTH_SHORT).show()

                                }

                            }).addOnFailureListener(object : OnFailureListener {
                            override fun onFailure(p0: Exception) {
                                Toast.makeText(applicationContext, "Failed", Toast.LENGTH_SHORT)
                                    .show()
                            }

                        })


                    } else {
                        Toast.makeText(
                            applicationContext,
                            "Filled the Information",
                            Toast.LENGTH_SHORT
                        ).show()
                    }

            startActivity(Intent(applicationContext,HomePage::class.java))
            finish()

        }

    }
}




