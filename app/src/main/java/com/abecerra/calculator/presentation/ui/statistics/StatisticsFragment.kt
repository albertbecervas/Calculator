package com.abecerra.calculator.presentation.ui.statistics


import android.graphics.Color
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.TabLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseFragment
import com.abecerra.calculator.core.math.DayAxisValueFormatter
import com.abecerra.calculator.core.utils.extensions.Data
import com.abecerra.calculator.core.utils.extensions.DataState
import com.abecerra.calculator.core.utils.extensions.observe
import com.abecerra.calculator.presentation.model.Transaction
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.components.XAxis.XAxisPosition
import com.github.mikephil.charting.components.YAxis
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.android.synthetic.main.fragment_statistics.*
import org.koin.android.viewmodel.ext.android.viewModel


class StatisticsFragment : BaseFragment() {

    private enum class FILTER { All, Income, Expenses }

    private val viewModel: StatisticsViewModel by viewModel()

    private val labels = arrayListOf<String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.transactions, ::updateTransactions)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setFilters()
        setChart()

        viewModel.getTransactions()

    }

    override fun onResume() {
        super.onResume()
        tlFilter?.getTabAt(0)?.select()
    }

    private fun setFilters() {
        with(tlFilter) {
            setSelectedTabIndicatorColor(Color.TRANSPARENT)
            FILTER.values().forEach {
                addTab(createTab(it.name))
            }

            addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                override fun onTabReselected(p0: TabLayout.Tab?) {
                    p0?.customView?.findViewById<ConstraintLayout>(R.id.tab)
                        ?.setBackgroundResource(R.drawable.bg_corners_blue)
                }

                override fun onTabUnselected(p0: TabLayout.Tab?) {
                    p0?.customView?.findViewById<ConstraintLayout>(R.id.tab)
                        ?.setBackgroundResource(R.drawable.bg_corners_gray)
                }

                override fun onTabSelected(tab: TabLayout.Tab?) {
                    tab?.customView?.findViewById<ConstraintLayout>(R.id.tab)
                        ?.setBackgroundResource(R.drawable.bg_corners_blue)
                    tab?.position?.let {
                        filterChart(it)
                    }
                }
            })
        }
    }

    private fun setChart() {

        val xAxis = chart.xAxis
        xAxis.setXAxis()

        val left = chart.axisLeft
        left.setYAxis()

        with(chart) {
            setBackgroundColor(Color.WHITE)
            extraTopOffset = -30f
            extraBottomOffset = 10f
            extraLeftOffset = 30f
            extraRightOffset = 30f
            setDrawBarShadow(false)
            setDrawValueAboveBar(true)
            description.isEnabled = false
            setDrawGridBackground(false)
            setPinchZoom(false)
            axisRight.isEnabled = false
            legend.isEnabled = false
        }


    }

    private fun YAxis.setYAxis() {
        setDrawLabels(false)
        spaceTop = 25f
        spaceBottom = 25f
        setDrawAxisLine(false)
        setDrawGridLines(false)
        setDrawZeroLine(true) // draw a zero line
        zeroLineColor = Color.GRAY
        zeroLineWidth = 0.7f
    }

    private fun XAxis.setXAxis() {
        position = XAxisPosition.BOTTOM
        setDrawGridLines(false)
        setDrawAxisLine(false)
        textColor = Color.LTGRAY
        textSize = 13f
        labelCount = 5
        setCenterAxisLabels(true)
        granularity = 1f
        valueFormatter = DayAxisValueFormatter(chart)

    }


    private fun setData(dataList: List<Transaction>) {

        chart?.let { chart ->

            val values = ArrayList<BarEntry>()
            val colors = ArrayList<Int>()

            val green = Color.rgb(110, 190, 102)
            val red = Color.rgb(211, 74, 88)

            for (i in dataList.indices) {

                val d = dataList[i]
                val entry = BarEntry(i.toFloat(), d.amount.toFloat())
                values.add(entry)
                labels.add(d.amount.toString())

                // specific colors
                if (d.amount.toFloat() >= 0)
                    colors.add(green)
                else
                    colors.add(red)
            }

            val set: BarDataSet

            set = BarDataSet(values, "Values")
            set.colors = colors
            set.setValueTextColors(colors)

            val data = BarData(set)
            data.setValueTextSize(13f)
            data.barWidth = 0.8f

            chart.data = data
            chart.invalidate()

        }
    }

    fun filterChart(filter: Int) {

        val totalTransactions = viewModel.transactions.value?.data ?: arrayListOf()

        when (filter) {
            FILTER.All.ordinal -> {
                setData(totalTransactions)
            }
            FILTER.Income.ordinal -> {
                setData(totalTransactions.filter { it.isReceived })
            }
            FILTER.Expenses.ordinal -> {
                setData(totalTransactions.filter { !it.isReceived })
            }
        }


    }

    private fun createTab(title: String): TabLayout.Tab {
        val tab = tlFilter.newTab().setCustomView(R.layout.item_filter_tab)
        tab.customView?.findViewById<TextView>(R.id.tvFilterTitle)?.text = title
        return tab
    }

    private fun updateTransactions(data: Data<List<Transaction>>?) {
        data?.let {
            when (it.dataState) {
                DataState.LOADING -> {
                    showLoading()
                }
                DataState.SUCCESS -> {
                    it.data?.let { transactions ->
                        setData(transactions)
                    }
                    hideLoading()
                }
                DataState.ERROR -> {
                    showError(it.message)
                }
            }
        }
    }

}
