package com.example.attendanceapp.Authentication.models.DatabaseModels

import android.widget.TextView
import com.google.gson.annotations.SerializedName

class AttebdanceModels(
    val dates:String,
    val classes:String,
    val student:ArrayList<String>



        ){
    constructor():this("","",ArrayList())
}