package com.gtxtreme.punecoronatracker

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gtxtreme.punecoronatracker.databinding.FragmentHomeBinding
import java.lang.Exception

/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment() {


    // Views
    private lateinit var binding: FragmentHomeBinding
    private lateinit var homeRecyclerView: RecyclerView
    private lateinit var errorText: TextView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_home,
            container,
            false
        )
        homeRecyclerView = binding.homeRv
        errorText = binding.errorText
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val homeRv = view.findViewById<RecyclerView>(R.id.home_rv)
        val homeProgress = view.findViewById<ProgressBar>(R.id.homeProgress)
        val lastUpdatedText = view.findViewById<TextView>(R.id.lastUpdated)
        val dataHandlerInstance = object : APIDataEventHandler {
            override fun timeSeriesCaseCounts(map: Map<String, ArrayList<String>>) {
                homeProgress.visibility = View.GONE
                homeRv.visibility = View.VISIBLE
                lastUpdatedText.visibility = View.VISIBLE

                val lastUpdatedAt = map["Last Update"]?.lastOrNull()
                val totalPos = map["Total Positive"]?.lastOrNull()
                val newPos = map["New Positive"]?.lastOrNull()
                val totalRecovered = map["Total Recovered"]?.lastOrNull()
                val totalDeaths = map["Total Deaths"]?.lastOrNull()
                val newRecovered = map["New Recovered"]?.lastOrNull()
                val totalActive = map["Total Active"]?.lastOrNull()
                val totalCritical = map["Total Critical"]?.lastOrNull()

                lastUpdatedText.text = "Last Updated On: " + lastUpdatedAt
                val dataList = ArrayList<HomeData>()
                dataList.add(HomeData("Total Positives", totalPos.toString()))
                dataList.add(HomeData("New Cases", newPos.toString()))
                dataList.add(HomeData("Total Recovered", totalRecovered.toString()))
                dataList.add(HomeData("New Recovered", newRecovered.toString()))
                dataList.add(HomeData("Total Deaths", totalDeaths.toString()))
                dataList.add(HomeData("Total Active", totalActive.toString()))
                dataList.add(HomeData("Total Critical", totalCritical.toString()))

                val homeAdapter = HomeAdapter(dataList)
                homeRv.adapter = homeAdapter
                homeRv.itemAnimator = DefaultItemAnimator()
                homeRv.layoutManager =
                    LinearLayoutManager(view.context, LinearLayoutManager.VERTICAL, false)


            }

            override fun ageWiseData(map: Map<String, ArrayList<String>>) = Unit

            override fun dailyWardWiseData(map: Map<String, ArrayList<String>>) = Unit

            override fun timeSeriesWardWise(map: Map<String, ArrayList<String>>) = Unit

            override fun onFailure(e: Exception) {
                homeRecyclerView.visibility = View.GONE
                homeProgress.visibility = View.GONE
                errorText.visibility = View.VISIBLE
                errorText.text = e.message
            }


        }

        ApiFetcher(dataHandlerInstance, ApiFetcher.TIMESERIES_CASE_COUNTS).execute()

    }


}
