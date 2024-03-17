package com.gtxtreme.punecoronatracker

import android.os.AsyncTask
import org.apache.commons.csv.CSVFormat
import org.apache.commons.csv.CSVParser
import java.net.URL
import java.nio.charset.Charset
import java.lang.Exception

class ApiFetcher : AsyncTask<Void, Void, Void> {

    private val urlArray = arrayOf(
        "https://raw.githubusercontent.com/omkarmarkad/COVID19-Pune/master/timeseries-case-counts.csv",
        "https://raw.githubusercontent.com/omkarmarkad/COVID19-Pune/master/timeseries-ward-wise.csv",
        "https://raw.githubusercontent.com/omkarmarkad/COVID19-Pune/master/daily-wardwise.csv",
        "https://raw.githubusercontent.com/omkarmarkad/COVID19-Pune/master/age-wise.csv"
    )

    companion object {
        val TIMESERIES_CASE_COUNTS = 0
        val TIMSERIES_WARD_WISE = 1
        val WARD_DAILY = 2
        val AGE_WISE = 3
        val ALL = 4
    }

    private var mDataEventHandler: APIDataEventHandler? = null
    private var mException: Exception? = null
    private var mType: Int? = null
    private var finalMap: Map<String, ArrayList<String>>? = null
//    private var arrayOfAll: Array<Map<String,ArrayList<String>>>? = null

    constructor() : super()
    constructor(dataHandler: APIDataEventHandler, type: Int) {
        mDataEventHandler = dataHandler
        mType = type
    }

    private var date = ArrayList<String>()
    private var tPos = ArrayList<String>()
    private var newPos = ArrayList<String>()
    private var tDeaths = ArrayList<String>()
    private var newDeaths = ArrayList<String>()
    private var tRecovered = ArrayList<String>()
    private var newRecovered = ArrayList<String>()
    private var tActive = ArrayList<String>()
    private var tCritical = ArrayList<String>()
    private var lastUpdatedAt = ArrayList<String>()
    private var AundhBaner = ArrayList<String>()
    private var KothrudBawdhan = ArrayList<String>()
    private var SinhagadRoad = ArrayList<String>()
    private var WarjeKVn = ArrayList<String>()
    private var ShivajiGhole = ArrayList<String>()
    private var KasbaVish = ArrayList<String>()
    private var DhankaSaha = ArrayList<String>()
    private var BhawaniP = ArrayList<String>()
    private var Bibwewadi = ArrayList<String>()
    private var DPRoad = ArrayList<String>()
    private var YWKLDH = ArrayList<String>()
    private var NagarWadg = ArrayList<String>()
    private var WanawRamt = ArrayList<String>()
    private var KondhYew = ArrayList<String>()
    private var HadapMundhwa = ArrayList<String>()
    private var OutPMC = ArrayList<String>()
    private var ward = ArrayList<String>()
    private var age = ArrayList<String>()
    private var male = ArrayList<String>()
    private var female = ArrayList<String>()

    override fun doInBackground(vararg params: Void): Void? {

        try {
            when (mType) {
                TIMESERIES_CASE_COUNTS -> {
                    val url = URL(urlArray[TIMESERIES_CASE_COUNTS])
                    val parser = CSVParser.parse(
                        url,
                        Charset.defaultCharset(),
                        CSVFormat.DEFAULT
                    )
                    parser.forEach { csvRecord ->
                        date.add(csvRecord[0])
                        tPos.add(csvRecord[1])
                        newPos.add(csvRecord[2])
                        tDeaths.add(csvRecord[3])
                        newDeaths.add(csvRecord[4])
                        tRecovered.add(csvRecord[5])
                        newRecovered.add(csvRecord[6])
                        tActive.add(csvRecord[7])
                        tCritical.add(csvRecord[8])
                        lastUpdatedAt.add(csvRecord[9])
                    }
                    parser.close()
                    finalMap = mapOf(
                        "Date" to date,
                        "Total Positive" to tPos,
                        "New Positive" to newPos,
                        "Total Deaths" to tDeaths,
                        "New Deaths" to newDeaths,
                        "Total Recovered" to tRecovered,
                        "New Recovered" to newRecovered,
                        "Total Active" to tActive,
                        "Total Critical" to tCritical,
                        "Last Update" to lastUpdatedAt
                    )
                }

                TIMSERIES_WARD_WISE -> {
                    val url = URL(urlArray[TIMSERIES_WARD_WISE])
                    val parser = CSVParser.parse(
                        url,
                        Charset.defaultCharset(),
                        CSVFormat.DEFAULT
                    )
                    parser.forEach { csvRecord ->
                        date.add(csvRecord[0])
                        AundhBaner.add(csvRecord[1])
                        KothrudBawdhan.add(csvRecord[2])
                        SinhagadRoad.add(csvRecord[3])
                        WarjeKVn.add(csvRecord[4])
                        ShivajiGhole.add(csvRecord[5])
                        KasbaVish.add(csvRecord[6])
                        DhankaSaha.add(csvRecord[7])
                        BhawaniP.add(csvRecord[8])
                        Bibwewadi.add(csvRecord[9])
                        DPRoad.add(csvRecord[10])
                        YWKLDH.add(csvRecord[11])
                        NagarWadg.add(csvRecord[12])
                        WanawRamt.add(csvRecord[13])
                        KondhYew.add(csvRecord[14])
                        HadapMundhwa.add(csvRecord[15])
                        OutPMC.add(csvRecord[16])
                    }
                    parser.close()
                    finalMap = mapOf(
                        "Date" to date,
                        "Aundh - Baner" to AundhBaner,
                        "Kothrud - Bawdhan" to KothrudBawdhan,
                        "Sinhagad Road" to SinhagadRoad,
                        "Warje - Karvenagar" to WarjeKVn,
                        "Shivajinagar - Ghole Road" to ShivajiGhole,
                        "Kasba - Vishrambaugwada" to KasbaVish,
                        "Dhankawadi - Sahakarnagar" to DhankaSaha,
                        "Bhawani Peth" to BhawaniP,
                        "Bibwewadi" to Bibwewadi,
                        "Dhole Patil Road" to DPRoad,
                        "Yerwada - Kalas - Dhanori" to YWKLDH,
                        "Nagar Road - Vadgaonsheri" to NagarWadg,
                        "Wanawadi - Ramtekadi" to WanawRamt,
                        "Kondhwa - Yewalewadi" to KondhYew,
                        "Hadapsar - Mundhwa" to HadapMundhwa,
                        "Outside PMC" to OutPMC
                    )
                }

                WARD_DAILY -> {
                    val url = URL(urlArray[WARD_DAILY])
                    val parser = CSVParser.parse(
                        url,
                        Charset.defaultCharset(),
                        CSVFormat.DEFAULT
                    )
                    parser.forEach { csvRecord ->
                        ward.add(csvRecord[2])
                        tPos.add(csvRecord[3])
                        newPos.add(csvRecord[4])
                        tDeaths.add(csvRecord[5])
                        tRecovered.add(csvRecord[6])
                        tActive.add(csvRecord[7])
                    }
                    parser.close()
                    finalMap = mapOf(
                        "Ward" to ward,
                        "Total Cases" to tPos,
                        "New Cases" to newPos,
                        "Total Deaths" to tDeaths,
                        "Total Recovered" to tRecovered,
                        "Total Active" to tActive
                    )

                }

                AGE_WISE -> {
                    val url = URL(urlArray[AGE_WISE])
                    val parser = CSVParser.parse(
                        url,
                        Charset.defaultCharset(),
                        CSVFormat.DEFAULT
                    )
                    parser.forEach { csvRecord ->
                        age.add(csvRecord[0])
                        male.add(csvRecord[1])
                        female.add(csvRecord[2])
                        tDeaths.add(csvRecord[3])
                    }
                    parser.close()
                    finalMap = mapOf(
                        "Age" to age,
                        "Male" to male,
                        "Female" to female,
                        "Deaths" to tDeaths
                    )
                }
            }


        } catch (e: Exception) {
            mException = e
        }
        return null
    }

    override fun onPostExecute(result: Void?) {
        if (mDataEventHandler != null) {
            if (mException == null) {
                when (mType) {
                    TIMESERIES_CASE_COUNTS -> finalMap?.let {
                        mDataEventHandler!!.timeSeriesCaseCounts(
                            it
                        )
                    }

                    TIMSERIES_WARD_WISE -> finalMap?.let { mDataEventHandler!!.timeSeriesWardWise(it) }
                    WARD_DAILY -> finalMap?.let { mDataEventHandler!!.dailyWardWiseData(it) }
                    AGE_WISE -> finalMap?.let { mDataEventHandler!!.ageWiseData(it) }
                }
            } else {
                mDataEventHandler!!.onFailure(mException!!)
            }
        }

    }
}