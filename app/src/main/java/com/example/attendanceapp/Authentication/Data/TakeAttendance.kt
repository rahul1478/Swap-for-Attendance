
package com.example.attendanceapp.Authentication.Data

import android.app.DatePickerDialog
import android.icu.lang.UCharacter.IndicPositionalCategory.LEFT
import android.media.MediaRouter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.attendanceapp.Authentication.RecyclerViewAdapter
import com.example.attendanceapp.Authentication.models.DatabaseModels.AttebdanceModels
//import com.example.attendanceapp.Authentication.models.DatabaseModels.AttebdanceModels
//import com.example.attendanceapp.Authentication.models.DatabaseModels.AttebdanceModels
import com.example.attendanceapp.R
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.lang.Exception
import java.util.*
import kotlin.collections.ArrayList


class TakeAttendance : AppCompatActivity() {

    lateinit var attendanceData: AttendanceData
    private lateinit var mAuth: FirebaseFirestore
    private lateinit var  items : ArrayList<String>
    lateinit var datelist : ArrayList<AttebdanceModels>
    private var data:ArrayList<String> = ArrayList()
    private  var  db1 = Firebase.firestore
    private lateinit var adapter: SwpieToAttendAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_take_attendance)



        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview3)
        recyclerView.layoutManager = LinearLayoutManager(this)

        items = fetchData()
        adapter = SwpieToAttendAdapter(items)
        recyclerView.adapter = adapter


        mAuth = FirebaseFirestore.getInstance()


        val date  = findViewById<TextView>(R.id.date)
        val btn = findViewById<Button>(R.id.savedata)
        val img = findViewById<ImageView>(R.id.Calender)

        val Calenders = Calendar.getInstance()
        val year = Calenders.get(Calendar.YEAR)
        val month = Calenders.get(Calendar.MONTH)
        val Day = Calenders.get(Calendar.DAY_OF_MONTH)

       img.setOnClickListener {
            val dpd = DatePickerDialog(this,DatePickerDialog.OnDateSetListener{view,mYear,mMonth,mDay ->
                 date.setText(""+mDay+"/"+mMonth+"/"+mYear)
            },year,month,Day)
            dpd.show()

        }

//        Toast.makeText(this,""+date2,Toast.LENGTH_SHORT).show()

        btn.setOnClickListener {

            val date1:String = ""+Day +"/"+month+"/"+year
            val student = data
            val Class = intent.getStringExtra("Class").toString()

//            for (i in 1.rangeTo(key)) {


                if ( student.isNotEmpty() && date1.isNotEmpty()) {

                    val model = AttebdanceModels(date1,Class,student)

                    mAuth.collection("Dates/").add(model)
                        .addOnSuccessListener(object : OnSuccessListener<DocumentReference> {
                            override fun onSuccess(p0: DocumentReference?) {

                                date.setText("")

//                                for (i in 1.rangeTo(key)){
//
//
//
//                                }


                                Toast.makeText(applicationContext, "Data Saved", Toast.LENGTH_SHORT)
                                    .show()

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
                        "Take Attendace first And write date",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
//        }
        val itemTouchHelper = ItemTouchHelper(swipeText)
        itemTouchHelper.attachToRecyclerView(recyclerView)




    }

    val swipeText = object : ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT)
    {
        override fun onMove(
            recyclerView: RecyclerView,
            viewHolder: RecyclerView.ViewHolder,
            target: RecyclerView.ViewHolder
        ): Boolean {
            return false
        }

        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
            var position = viewHolder.adapterPosition

            for (i in items) {

                when (direction) {
                    ItemTouchHelper.LEFT -> {

                        items.remove(items[position])
                        adapter.notifyItemRemoved(position)

//                        Toast.makeText(this@TakeAttendance, "Absent : "+i , Toast.LENGTH_SHORT).show()
                        data.add(i+"  :  Absent")

                    }
                    ItemTouchHelper.RIGHT -> {
                        items.remove(items[position])
                        adapter.notifyItemRemoved(position)

//                        Toast.makeText(this@TakeAttendance, "Present : "+i, Toast.LENGTH_SHORT).show()
                        data.add(i+"  :  Present")
                    }
                }
                break
            }
//            Toast.makeText(this@TakeAttendance, ""+data, Toast.LENGTH_SHORT).show()

        }


    }

    private fun fetchData(): ArrayList<String>{
        val list = ArrayList<String>()
        val data:Int = intent.getStringExtra("Student")!!.toInt()

        for (i in 1.rangeTo(data)){
            list.add(""+i)
        }
        return list
    }


}


