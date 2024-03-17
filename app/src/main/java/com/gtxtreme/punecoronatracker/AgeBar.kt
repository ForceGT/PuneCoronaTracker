package com.gtxtreme.punecoronatracker


import com.github.mikephil.charting.data.BarDataSet

data class AgeBar(
    val entryList: List<BarDataSet>,
    val labelList: ArrayList<String>
)