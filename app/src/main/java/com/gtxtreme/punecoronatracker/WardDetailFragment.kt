package com.gtxtreme.punecoronatracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_ward_detail.*
import java.lang.Exception

class WardDetailFragment:Fragment() {

    private val dataList = ArrayList<WardData>()
    private val wardAdapter : WardAdapter? = WardAdapter(dataList)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_ward_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val wardRV = view.findViewById<RecyclerView>(R.id.wardDetailRV)
        wardRV.adapter = wardAdapter
        wardRV.layoutManager = LinearLayoutManager(view.context,LinearLayoutManager.VERTICAL,false)
        val dataHandlerInstance = object :APIDataEventHandler{
            override fun timeSeriesCaseCounts(map: Map<String, ArrayList<String>>) {
                TODO("Not yet implemented")
            }

            override fun ageWiseData(map: Map<String, ArrayList<String>>) {

            }

            override fun dailyWardWiseData(map: Map<String, ArrayList<String>>) {
                val ward = map["Ward"]
                val positiveCases = map["Total Cases"]
                val totalDeaths = map["Total Deaths"]
                val totalRecovered  = map["Total Recovered"]
                val totalActive = map["Total Active"]

                for (i in 1 until ward!!.size){
                    dataList.add(WardData(ward[i],positiveCases!![i],totalActive!![i],totalDeaths!![i],totalRecovered!![i]))
                }
                wardAdapter!!.notifyDataSetChanged()
            }

            override fun timeSeriesWardWise(map: Map<String, ArrayList<String>>) {

            }

            override fun onFailure(e: Exception) {
                errorText.visibility = View.VISIBLE
                errorText.text = e.message
            }
        }

        ApiFetcher(dataHandlerInstance,ApiFetcher.WARD_DAILY).execute()

    }
}