package com.example.noteapp.convertors

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

fun formatDate(time:Long):String{
    var date = Date(time)
    var format = SimpleDateFormat("hh:mm aaa" +
            "d MMM, EEE", Locale.getDefault())
    return format.format(date)
}