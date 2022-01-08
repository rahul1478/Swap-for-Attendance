package com.example.attendanceapp.Authentication.models.DatabaseModels

data class DatabaseModel(
    var Class:String,
    var div: String,
    var student:String,

) {
    constructor(): this("", "","")
}