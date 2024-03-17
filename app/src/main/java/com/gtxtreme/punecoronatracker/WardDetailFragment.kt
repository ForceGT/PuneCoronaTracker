package com.gtxtreme.punecoronatracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.gtxtreme.punecoronatracker.databinding.FragmentWardDetailBinding

class WardDetailFragment : Fragment() {

    private lateinit var binding: FragmentWardDetailBinding

    private val dataList = ArrayList<WardData>()
    private val wardAdapter: WardAdapter = WardAdapter(dataList)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_ward_detail,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.wardDetailRV.adapter = wardAdapter
        binding.wardDetailRV.layoutManager =
            LinearLayoutManager(
                view.context,
                LinearLayoutManager.VERTICAL,
                false
            )
        val dataHandlerInstance = object : APIDataEventHandler {
            override fun timeSeriesCaseCounts(map: Map<String, ArrayList<String>>) = Unit

            override fun ageWiseData(map: Map<String, ArrayList<String>>) = Unit

            override fun dailyWardWiseData(map: Map<String, ArrayList<String>>) {
                val ward = map["Ward"]
                val positiveCases = map["Total Cases"] ?: emptyList()
                val totalDeaths = map["Total Deaths"] ?: emptyList()
                val totalRecovered = map["Total Recovered"] ?: emptyList()
                val totalActive = map["Total Active"] ?: emptyList()

                for (i in 1 until ward!!.size) {
                    dataList.add(
                        WardData(
                            ward[i],
                            positiveCases[i],
                            totalActive[i],
                            totalDeaths[i],
                            totalRecovered[i]
                        )
                    )
                }
                wardAdapter?.notifyDataSetChanged()
            }

            override fun timeSeriesWardWise(map: Map<String, ArrayList<String>>) = Unit

            override fun onFailure(e: Exception) {
                binding.errorText.visibility = View.VISIBLE
                binding.errorText.text = e.message
            }
        }

        ApiFetcher(dataHandlerInstance, ApiFetcher.WARD_DAILY).execute()

    }
}