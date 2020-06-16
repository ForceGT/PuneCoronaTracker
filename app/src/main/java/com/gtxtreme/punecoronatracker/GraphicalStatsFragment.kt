package com.gtxtreme.punecoronatracker

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.mikephil.charting.data.*
import com.gtxtreme.punecoronatracker.ApiFetcher.Companion.AGE_WISE
import com.gtxtreme.punecoronatracker.ApiFetcher.Companion.TIMESERIES_CASE_COUNTS
import com.gtxtreme.punecoronatracker.ApiFetcher.Companion.TIMSERIES_WARD_WISE
import com.gtxtreme.punecoronatracker.ApiFetcher.Companion.WARD_DAILY
import kotlinx.android.synthetic.main.fragment_graphical_stats.*
import java.lang.NullPointerException
import kotlin.Exception


class GraphicalStatsFragment : Fragment() {

    private val graphItemList = ArrayList<Any>()
    private var graphAdapter: GraphAdapter? = GraphAdapter(graphItemList)
    private var lastUpdatedate:String? = null
    private var exceptionMessage:String? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        getSummary()
        getDayWiseSeries()
        getAgeWiseBar()
        getDailyWardWiseBar()
//        graphAdapter?.notifyDataSetChanged()
        return inflater.inflate(R.layout.fragment_graphical_stats, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        super.onViewCreated(view, savedInstanceState)
        if (graphAdapter!= null){
            graphRecyclerView.visibility = View.VISIBLE
            graphProgressBar.visibility = View.GONE
        }
        if (exceptionMessage != null)
        {
            graphProgressBar.visibility = View.GONE
            graphRecyclerView.visibility = View.GONE
            errorText.visibility = View.VISIBLE
            errorText.text = exceptionMessage
        }
        graphRecyclerView.itemAnimator = DefaultItemAnimator()
        graphRecyclerView.adapter = graphAdapter
        graphRecyclerView.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
    }

    private fun getSummary() {
        var pieEntryList: List<PieEntry>? = null
        val dataHandlerInstance1 = object : APIDataEventHandler {
            override fun timeSeriesCaseCounts(map: Map<String, ArrayList<String>>) {

                val totalRecovered = map["Total Recovered"]?.lastOrNull()?.toFloat()
                val totalActive = map["Total Active"]?.lastOrNull()?.toFloat()
                val totalDeaths = map["Total Deaths"]?.lastOrNull()?.toFloat()
                pieEntryList = listOf(
                    PieEntry(totalRecovered!!, "Total Recovered"),
                    PieEntry(totalActive!!, "Total Active"),
                    PieEntry(totalDeaths!!, "Total Deaths")
                )
                graphItemList.add(Pie(pieEntryList!!))
                graphAdapter?.notifyDataSetChanged()
            }

            override fun ageWiseData(map: Map<String, ArrayList<String>>) {
                TODO("Not yet implemented")
            }

            override fun dailyWardWiseData(map: Map<String, ArrayList<String>>) {
                TODO("Not yet implemented")
            }

            override fun timeSeriesWardWise(map: Map<String, ArrayList<String>>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(e: Exception) {
                exceptionMessage+=e.message
            }
        }

        ApiFetcher(dataHandlerInstance1, TIMESERIES_CASE_COUNTS).execute()

    }

    fun getAgeWiseBar() {
        val dataHandlerInstance2 = object : APIDataEventHandler {


            override fun timeSeriesCaseCounts(map: Map<String, ArrayList<String>>) {
                TODO("Not yet implemented")
            }

            override fun ageWiseData(map: Map<String, ArrayList<String>>) {
                val age = map["Age"]
//                val male = map["Male"]
//                val female = map["Female"]
//                val deaths = map["Deaths"]

                val labelArray = arrayOf("Male","Female","Deaths")
                val dataSetList: ArrayList<BarDataSet> = ArrayList()

                labelArray.forEach { label->
                    val entryList = arrayListOf<BarEntry>()
                    map[label]?.forEachIndexed(){index,value->
                       if (index!=0)
                           entryList.add(BarEntry((index - 1).toFloat(), value.toFloat()))
                    }
                    dataSetList.add(BarDataSet(entryList, label))
                }

                graphItemList.add(AgeBar(dataSetList, age!!))
                graphAdapter?.notifyDataSetChanged()

            }

            override fun dailyWardWiseData(map: Map<String, ArrayList<String>>) {
                TODO("Not yet implemented")
            }

            override fun timeSeriesWardWise(map: Map<String, ArrayList<String>>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(e: Exception) {
                exceptionMessage+=e.message
            }
        }
        ApiFetcher(dataHandlerInstance2, AGE_WISE).execute()
//        return column

    }

    fun getDailyWardWiseBar() {
        val dataHandlerInstance3 = object : APIDataEventHandler {
            override fun timeSeriesCaseCounts(map: Map<String, ArrayList<String>>) {
                TODO("Not yet implemented")
            }

            override fun ageWiseData(map: Map<String, ArrayList<String>>) {
                TODO("Not yet implemented")
            }

            override fun dailyWardWiseData(map: Map<String, ArrayList<String>>) {

                val ward = map["Ward"]
//                val tCases = map["Total Cases"]
//                val tDeaths = map["Total Deaths"]
//                val tActive = map["Total Active"]
                val dataSetList: ArrayList<BarDataSet> = ArrayList()
                val labelArray = arrayOf("Total Cases","Total Deaths","Total Active")
                labelArray.forEach { label->
                    val entryList = arrayListOf<BarEntry>()
                    map[label]?.forEachIndexed(){index,value->
                        if (index!=0)
                            entryList.add(BarEntry((index - 1).toFloat(), value.toFloat()))
                    }
                    dataSetList.add(BarDataSet(entryList, label))
                }
                graphItemList.add(WardHorizontalBar(dataSetList, ward!!,lastUpdatedate!!))
                graphAdapter?.notifyDataSetChanged()
            }

            override fun timeSeriesWardWise(map: Map<String, ArrayList<String>>) {
                TODO("Not yet implemented")
            }

            override fun onFailure(e: Exception) {
                exceptionMessage+=e.message
            }
        }
        ApiFetcher(dataHandlerInstance3, WARD_DAILY).execute()
        // return bar
    }


    fun getDayWiseSeries() {
        val dataHandlerInstance4 = object : APIDataEventHandler {
            override fun timeSeriesCaseCounts(map: Map<String, ArrayList<String>>) {
                TODO("Not yet implemented")
            }

            override fun ageWiseData(map: Map<String, ArrayList<String>>) {
                TODO("Not yet implemented")
            }

            override fun dailyWardWiseData(map: Map<String, ArrayList<String>>) {
                TODO("Not yet implemented")
            }

            override fun timeSeriesWardWise(map: Map<String, ArrayList<String>>) {


               val date = map["Date"]
                lastUpdatedate = date?.lastOrNull()
                val labelArray = arrayOf(
                    "Aundh - Baner",
                    "Kothrud - Bawdhan",
                    "Sinhagad Road",
                    "Warje - Karvenagar",
                    "Shivajinagar - Ghole Road",
                    "Kasba - Vishrambaugwada"
                    ,
                    "Dhankawadi - Sahakarnagar",
                    "Bhawani Peth",
                    "Bibwewadi",
                    "Dhole Patil Road",
                    "Yerwada - Kalas - Dhanori",
                    "Nagar Road - Vadgaonsheri",
                    "Wanawadi - Ramtekadi",
                    "Kondhwa - Yewalewadi",
                    "Hadapsar - Mundhwa",
                    "Outside PMC"
                )
                val dataSetList: ArrayList<LineDataSet> = ArrayList()
                labelArray.forEach { label ->
//                    val mapOfLabel = map[label]
                    val entryList = arrayListOf<Entry>()
                    map[label]?.forEachIndexed { index, string ->
                        if (index != 0)
                            entryList.add(Entry((index - 1).toFloat(), string.toFloat()))
                    }
                    dataSetList.add(LineDataSet(entryList, label))

                }
                graphItemList.add(Line(dataSetList, date!!))
                graphAdapter?.notifyDataSetChanged()
            }

            override fun onFailure(e: Exception) {
                exceptionMessage+=e.message
            }
        }

        ApiFetcher(dataHandlerInstance4, TIMSERIES_WARD_WISE).execute()
//        return line
    }

}





