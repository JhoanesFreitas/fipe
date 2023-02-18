package com.cajusoftware.fipe.ui.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.cajusoftware.fipe.R
import com.cajusoftware.fipe.data.domain.YearPriceModel
import com.cajusoftware.fipe.databinding.LayoutChartViewBinding
import com.cajusoftware.fipe.utils.exts.toSafeFloat
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet

class ChartView(context: Context, attributes: AttributeSet) : LinearLayout(context, attributes) {

    private val binding = LayoutChartViewBinding.inflate(LayoutInflater.from(context), this, true)

    var title: CharSequence? = null
        set(value) {
            binding.title.text = value
            field = value
        }

    var values: List<YearPriceModel>? = null
        set(value) {
            values?.let { setValuesOnChart(it) }
            field = value
        }

    var fullScreenIcon: Int? = null
        set(value) {
            value?.let { binding.fullscreenButton.setImageResource(it) }
            field = value
        }

    var fullscreenClickListener: (() -> Unit)? = null

    private val chartData = LineData()

    init {
        val typedArray = context.obtainStyledAttributes(attributes, R.styleable.ChartView)
        title = typedArray.getString(R.styleable.ChartView_title)
        fullScreenIcon =
            typedArray.getResourceId(R.styleable.ChartView_fullscreenIcon, R.drawable.ic_fullscreen)
        typedArray.recycle()
        createChart()
        setValuesOnChart(
            listOf(
                YearPriceModel("2015", "120.000,00"),
                YearPriceModel("2016", "118.000,00"),
                YearPriceModel("2017", "117.300,00"),
                YearPriceModel("2018", "115.230,00"),
                YearPriceModel("2019", "115.300,00"),
                YearPriceModel("2020", "122.000,00"),
            )
        )
    }

    private fun setValuesOnChart(values: List<YearPriceModel>) {
        binding.chart.apply {
            chartData.clearValues()
            val priceValues = ArrayList<Entry>()

            values.forEach {
                priceValues.add(Entry(it.year.toSafeFloat(), it.price.toSafeFloat()))

                val chartDataSet = LineDataSet(priceValues, it.year).apply {
                    circleRadius = 4f
                    setDrawFilled(true)
                    valueTextSize = 14F
                    fillColor = Color.GREEN + Color.GREEN + Color.GREEN
                    mode = LineDataSet.Mode.CUBIC_BEZIER
                }
                chartData.addDataSet(chartDataSet)
            }

            data = chartData
        }
    }

    private fun createChart() {
        binding.chart.apply {
            setFullscreen()
            xAxis.setDrawGridLines(false)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1F

            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.textSize = 15F
            legend.form = Legend.LegendForm.LINE

            axisRight.isEnabled = false
            extraRightOffset = 30f

            legend.isEnabled = false
            description.isEnabled = false

            setBackgroundColor(Color.WHITE)
            animateXY(2000, 2000)
        }
    }

    private fun setFullscreen() {
        binding.fullscreenButton.setOnClickListener {
            fullscreenClickListener?.invoke()
        }
    }
}