package com.cajusoftware.fipe.ui.components

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import com.cajusoftware.fipe.R
import com.cajusoftware.fipe.data.domain.Historic
import com.cajusoftware.fipe.databinding.LayoutChartViewBinding
import com.cajusoftware.fipe.utils.exts.toChartLabel
import com.cajusoftware.fipe.utils.exts.toSafeFloat
import com.github.mikephil.charting.components.Legend
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.Entry
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.formatter.IAxisValueFormatter
import com.github.mikephil.charting.formatter.IValueFormatter
import com.github.mikephil.charting.formatter.LargeValueFormatter
import java.text.NumberFormat
import java.util.*


class ChartView(context: Context, attributes: AttributeSet) : LinearLayout(context, attributes) {

    private val binding = LayoutChartViewBinding.inflate(LayoutInflater.from(context), this, true)

    var title: CharSequence? = null
        set(value) {
            binding.title.text = value
            field = value
        }

    var historic: Historic? = null
        set(value) {
            value?.let { setValuesOnChart(it) }
            field = value
        }

    var fullScreenIcon: Int? = null
        set(value) {
            value?.let { binding.fullscreenButton.setImageResource(it) }
            field = value
        }

    var fullscreenClickListener: (() -> Unit)? = null

    private val chartData = LineData()

    private val priceValues = ArrayList<Entry>()

    init {
        val typedArray = context.obtainStyledAttributes(attributes, R.styleable.ChartView)
        title = typedArray.getString(R.styleable.ChartView_title)
        fullScreenIcon =
            typedArray.getResourceId(R.styleable.ChartView_fullscreenIcon, R.drawable.ic_fullscreen)
        typedArray.recycle()
        createChart()
    }

    private fun setValuesOnChart(values: Historic) {
        binding.chart.apply {

            chartData.clearValues()
            priceValues.clear()

            var i = 0f
            values.historic.forEach {
                priceValues.add(Entry(i++, it.price.toSafeFloat()))

                val chartDataSet =
                    LineDataSet(priceValues, it.month.toChartLabel()).apply {
                        circleRadius = 4f
                        setCircleColor(Color.MAGENTA)
                        setDrawFilled(true)
                        valueTextSize = 14F
                        fillColor = Color.GREEN + Color.GREEN + Color.GREEN
                        mode = LineDataSet.Mode.CUBIC_BEZIER
                        valueFormatter =
                            IValueFormatter { value, _, _, _ ->
                                NumberFormat.getCurrencyInstance(Locale.forLanguageTag("pt-BR"))
                                    .format(value)
                            }
                    }
                chartData.addDataSet(chartDataSet)
            }

            data = chartData
            notifyDataSetChanged()
            invalidate()
        }
    }

    private fun createChart() {
        binding.chart.apply {
            setFullscreen()
            xAxis.setDrawGridLines(true)
            xAxis.position = XAxis.XAxisPosition.BOTTOM
            xAxis.granularity = 1F
            xAxis.valueFormatter =
                IAxisValueFormatter { value, _ ->
                    historic?.historic?.get(value.toInt())?.month?.toChartLabel()
                }

            val left: YAxis = axisLeft
            left.valueFormatter = LargeValueFormatter()

            legend.orientation = Legend.LegendOrientation.VERTICAL
            legend.verticalAlignment = Legend.LegendVerticalAlignment.TOP
            legend.horizontalAlignment = Legend.LegendHorizontalAlignment.CENTER
            legend.textSize = 14F
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