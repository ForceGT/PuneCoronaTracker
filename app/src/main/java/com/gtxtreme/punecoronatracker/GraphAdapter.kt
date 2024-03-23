package com.gtxtreme.punecoronatracker

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.charts.HorizontalBarChart
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.charts.PieChart
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet
import com.github.mikephil.charting.utils.ColorTemplate


class GraphAdapter(private var mData: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TYPE_PIE = 0
    private val TYPE_LINE = 1
    private val TYPE_BAR = 2
    private val TYPE_HBAR = 3

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        var layout = 0
        val viewHolder: RecyclerView.ViewHolder?
        when (viewType) {
            TYPE_PIE -> {
                layout = R.layout.pie_chart_item
                val pieChartView =
                    LayoutInflater.from(parent.context).inflate(layout, parent, false)
                viewHolder = PieChartViewHolder(pieChartView)
            }
            TYPE_LINE -> {
                layout = R.layout.line_chart_item
                val lineChartView =
                    LayoutInflater.from(parent.context).inflate(layout, parent, false)
                viewHolder = LineChartViewHolder(lineChartView)
            }
            TYPE_HBAR -> {
                layout = R.layout.wardwise_horizontal_bar_item
                val horizontalBarView =
                    LayoutInflater.from(parent.context).inflate(layout, parent, false)
                viewHolder = WardHorizontalBarViewHolder(horizontalBarView)
            }
            TYPE_BAR -> {
                layout = R.layout.agewise_bar_item
                val ageBarView = LayoutInflater.from(parent.context).inflate(layout, parent, false)
                viewHolder = AgeBarChartViewHolder(ageBarView)
            }
            else -> viewHolder = null
        }
        return viewHolder!!
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            TYPE_PIE -> {
                val pie = mData[position] as Pie
                (holder as PieChartViewHolder).showDetails(pie)
                setFadeAnimation(holder.itemView)
            }
            TYPE_LINE -> {
                val line = mData[position] as Line
                (holder as LineChartViewHolder).showDetails(line)
                setFadeAnimation(holder.itemView)
            }
            TYPE_HBAR -> {
                val hBar = mData[position] as WardHorizontalBar
                (holder as WardHorizontalBarViewHolder).showDetails(hBar)
                setFadeAnimation(holder.itemView)
            }
            TYPE_BAR -> {
                val ageBar = mData[position] as AgeBar
                (holder as AgeBarChartViewHolder).showDetails(ageBar)
                setFadeAnimation(holder.itemView)
            }
        }


    }

    private fun setFadeAnimation(itemView: View) {
        val anim = AlphaAnimation(0.2f, 1.0f)
        anim.duration = 1000
        itemView.startAnimation(anim)
    }

    override fun getItemViewType(position: Int): Int {
        when (mData[position]) {
            is Pie -> return TYPE_PIE
            is Line -> return TYPE_LINE
            is WardHorizontalBar -> return TYPE_HBAR
            is AgeBar -> return TYPE_BAR
        }
        return -1
    }

    inner class PieChartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var pieChart: PieChart? = null

        private val typeFace = ResourcesCompat.getFont(itemView.context,R.font.poppins_light)
        init {
            pieChart = itemView.findViewById(R.id.pieChart)
            pieChart!!.setUsePercentValues(true);
            pieChart!!.description.isEnabled = false;
            pieChart!!.setExtraOffsets(5f, 10f, 5f, 5f);
            pieChart!!.animateXY(800, 800)
            pieChart!!.dragDecelerationFrictionCoef = 0.95f
            pieChart!!.isRotationEnabled = true;

            pieChart!!.isDrawHoleEnabled = false
            val l: Legend = pieChart!!.legend
            l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            l.orientation = Legend.LegendOrientation.VERTICAL
            l.setDrawInside(false)
            l.textColor = Color.WHITE
            l.xEntrySpace = 7f
            l.yEntrySpace = 0f
            l.yOffset = 0f
            l.typeface = typeFace
        }

        fun showDetails(pie: Pie) {
            val dataList = pie.entryList
            val dataSet = PieDataSet(dataList,"")
            dataSet.valueTypeface = typeFace
            val colors = arrayListOf<Int>()
            for (c in ColorTemplate.MATERIAL_COLORS) colors.add(c)
            dataSet.colors = colors
            val data = PieData(dataSet)
            data.setValueFormatter(PercentFormatter(pieChart))
            data.setValueTextSize(11f)
            data.setValueTextColor(Color.WHITE)
            data.setValueTypeface(typeFace)
            pieChart?.data = data
        }
    }

    inner class LineChartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var lineChart: LineChart? = null

        private val typeFace = ResourcesCompat.getFont(itemView.context,R.font.poppins_light)
        private val typeFace2 = ResourcesCompat.getFont(itemView.context,R.font.poppins_extralight)
        init {
            lineChart = itemView.findViewById(R.id.lineChart)
            lineChart!!.setDrawGridBackground(false)
            lineChart!!.isDoubleTapToZoomEnabled = false
            //lineChart!!.setVisibleYRangeMaximum(40f,YAxis.AxisDependency.LEFT)
            val l = lineChart!!.legend
            l.orientation = Legend.LegendOrientation.VERTICAL
            l.setDrawInside(true)
            l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.LEFT
            l.xOffset = 40f
            l.textColor = Color.WHITE
            l.typeface = typeFace
            val xAxis: XAxis = lineChart!!.xAxis
            xAxis.textColor = Color.WHITE
            xAxis.position = XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(true)
            xAxis.typeface = typeFace2
            val leftAxis: YAxis = lineChart!!.axisLeft
//            leftAxis.setLabelCount(5, false)
            leftAxis.textColor = Color.WHITE
            leftAxis.typeface = typeFace2
            leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)
            val rightAxis: YAxis = lineChart!!.axisRight
            rightAxis.isEnabled = false

        }

        fun showDetails(line: Line) {
            val dataSetList = line.entryList
            val sets: ArrayList<ILineDataSet> = ArrayList()
            val colors = arrayListOf<Int>()
            for (c in ColorTemplate.MATERIAL_COLORS) colors.add(c)
            for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
            for (c in ColorTemplate.PASTEL_COLORS) colors.add(c)
            for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
            for (c in ColorTemplate.LIBERTY_COLORS) colors.add(c)
            dataSetList.forEachIndexed() { index, dataSet ->
                dataSet.color = colors[index]
                //dataSet.circleHoleColor = colors[index]
//                dataSet.circleRadius = 0f
//                dataSet.circleHoleRadius = 0f

                dataSet.setCircleColor(Color.rgb(126,163,213))
                dataSet.lineWidth = 2.5f
                dataSet.circleRadius = 2f
                dataSet.fillAlpha = 45
                dataSet.setDrawCircleHole(false)
                dataSet.valueTextColor = Color.WHITE
                dataSet.valueTypeface = typeFace2
                sets.add(dataSet)

            }
            lineChart?.xAxis?.valueFormatter =
                IndexAxisValueFormatter(line.labelList.subList(1, line.labelList.size))
            lineChart?.data = LineData(sets)
            lineChart?.animateX(2000)
            lineChart?.invalidate()
            // lineChart?.setVisibleYRangeMinimum(40f,YAxis.AxisDependency.LEFT)
            lineChart?.isDragEnabled = true
            lineChart?.setScaleEnabled(true)
            lineChart?.description?.isEnabled = false
            //TODO Change Nested Scroll


        }

    }

    inner class AgeBarChartViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var ageBarChart: BarChart? = null

        private val typeFace = ResourcesCompat.getFont(itemView.context,R.font.poppins_light)
        private val typeFace2 = ResourcesCompat.getFont(itemView.context,R.font.poppins_extralight)
        init {
            ageBarChart = itemView.findViewById(R.id.ageWiseBarChart)
            ageBarChart!!.setDrawBarShadow(false);

            ageBarChart!!.setDrawValueAboveBar(true);
            val l = ageBarChart!!.legend
            l.orientation = Legend.LegendOrientation.VERTICAL
            l.setDrawInside(true)
            l.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            l.xOffset = 40f
            l.textColor = Color.WHITE
            l.typeface = typeFace
            val xAxis: XAxis = ageBarChart!!.xAxis
            xAxis.textColor = Color.WHITE
            xAxis.position = XAxisPosition.BOTTOM
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(true)
            xAxis.typeface = typeFace2
            val leftAxis: YAxis = ageBarChart!!.axisLeft
            leftAxis.textColor = Color.WHITE
            leftAxis.typeface = typeFace2
            leftAxis.axisMinimum = 0f // this replaces setStartAtZero(true)
            val rightAxis: YAxis = ageBarChart!!.axisRight
            rightAxis.isEnabled = false
        }

        fun showDetails(ageBar: AgeBar) {
            val dataSetList = ageBar.entryList
            val sets: ArrayList<IBarDataSet> = ArrayList()
            val colors = arrayListOf<Int>()
            for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
            dataSetList.forEachIndexed() { index, dataSet ->
                dataSet.color = colors[index]
                dataSet.valueTextColor = Color.WHITE
                dataSet.valueTypeface = typeFace2
                sets.add(dataSet)
            }
            val barData = BarData(sets)
            barData.barWidth = 0.9f
            ageBarChart!!.data = barData
            ageBarChart?.xAxis?.valueFormatter = IndexAxisValueFormatter(ageBar.labelList.subList(1, ageBar.labelList.size))
            ageBarChart!!.animateY(2000)
            ageBarChart!!.invalidate()
            ageBarChart!!.description?.isEnabled = false
        }


    }

    inner class WardHorizontalBarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var wardHorizontalBarChart: HorizontalBarChart? = null
        private var descriptionText: TextView? = null
        private val typeFace = ResourcesCompat.getFont(itemView.context,R.font.poppins_light)
        private val typeFace2 = ResourcesCompat.getFont(itemView.context,R.font.poppins_extralight)
        init {
            wardHorizontalBarChart = itemView.findViewById(R.id.wardWiseHorizontalBarChart)
            descriptionText = itemView.findViewById(R.id.lastUpdatedText)
            wardHorizontalBarChart!!.setDrawGridBackground(false)
            wardHorizontalBarChart!!.setDrawBarShadow(false);
            wardHorizontalBarChart!!.setDrawValueAboveBar(true);
            wardHorizontalBarChart!!.description.isEnabled = false;
            wardHorizontalBarChart!!.setFitBars(true);
            wardHorizontalBarChart!!.animateY(2500);
            val l = wardHorizontalBarChart!!.legend
            l.orientation = Legend.LegendOrientation.HORIZONTAL
            l.setDrawInside(true)
            l.verticalAlignment = Legend.LegendVerticalAlignment.BOTTOM
            l.horizontalAlignment = Legend.LegendHorizontalAlignment.RIGHT
            l.xOffset = 10f
            l.textColor = Color.WHITE
            l.typeface= typeFace
            val xAxis: XAxis = wardHorizontalBarChart!!.xAxis
            xAxis.textColor = Color.WHITE
            xAxis.setDrawGridLines(false)
            xAxis.setDrawAxisLine(true)
            xAxis.position = XAxisPosition.TOP_INSIDE
            xAxis.typeface = typeFace2
            val leftAxis: YAxis = wardHorizontalBarChart!!.axisLeft
            leftAxis.textColor = Color.WHITE
            leftAxis.setDrawAxisLine(true);
            leftAxis.setDrawGridLines(true);
            leftAxis.typeface= typeFace2
            leftAxis.axisMinimum = 0f;// this replaces setStartAtZero(true)
            val yr: YAxis = wardHorizontalBarChart!!.axisRight
            yr.setDrawAxisLine(true)
            yr.setDrawGridLines(false)
            yr.isEnabled = false
            yr.axisMinimum = 0f
        }

        fun showDetails(wardHorizontalBar: WardHorizontalBar) {
            descriptionText!!.text = "Ward Data As Of ${wardHorizontalBar.lastUpdatedAt}"
            val dataSetList = wardHorizontalBar.entryList
            val sets: ArrayList<IBarDataSet> = ArrayList()
            val colors = arrayListOf<Int>()
            for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
            dataSetList.forEachIndexed() { index, dataSet ->
                dataSet.color = colors[index]
                dataSet.valueTextColor = Color.WHITE
                dataSet.valueTypeface = typeFace2
                sets.add(dataSet)
            }
            val barData = BarData(sets)
            barData.barWidth = 0.9f
            wardHorizontalBarChart!!.data = barData
            wardHorizontalBarChart!!.xAxis.valueFormatter = IndexAxisValueFormatter(wardHorizontalBar.labelList.subList(1, wardHorizontalBar.labelList.size))
            wardHorizontalBarChart!!.animateY(2000)
            wardHorizontalBarChart!!.invalidate()
        }
    }
}