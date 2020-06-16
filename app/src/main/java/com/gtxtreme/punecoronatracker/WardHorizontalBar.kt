package com.gtxtreme.punecoronatracker

import com.github.mikephil.charting.data.BarDataSet

data class WardHorizontalBar (val entryList: List<BarDataSet>,val labelList: ArrayList<String>,val lastUpdatedAt:String){
}