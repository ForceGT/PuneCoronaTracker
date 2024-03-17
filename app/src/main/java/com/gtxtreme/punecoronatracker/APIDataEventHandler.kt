package com.gtxtreme.punecoronatracker

import java.lang.Exception


interface APIDataEventHandler {

    fun timeSeriesCaseCounts(map: Map<String,ArrayList<String>>)
    fun ageWiseData(map: Map<String,ArrayList<String>>)
    fun dailyWardWiseData(map: Map<String,ArrayList<String>>)
    fun timeSeriesWardWise(map: Map<String,ArrayList<String>>)
    fun onFailure(e:Exception)
}