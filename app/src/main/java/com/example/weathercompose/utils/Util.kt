package com.example.weathercompose.utils

import java.text.SimpleDateFormat
import java.util.Date

fun formatDate(timeStamp: Long): String {
    val sdf = SimpleDateFormat("EEE,MM,d")
    val date = Date(timeStamp * 1000)
    return sdf.format(date)
}

fun formatTime(timeStamp: Long): String {
    val sdf = SimpleDateFormat("hh:mm:aa")
    val time = Date(timeStamp * 1000)
    return sdf.format(time)
}