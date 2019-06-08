package com.abecerra.calculator.presentation.ui.calculator.history

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseAdapter
import com.abecerra.calculator.core.utils.extensions.inflate
import com.abecerra.calculator.presentation.model.CalculationHistory
import kotlinx.android.synthetic.main.item_calculation_history.view.*

class CalculationHistoryAdapter :
    BaseAdapter<CalculationHistoryAdapter.CalculationHistoryViewHolder, CalculationHistory>() {
    override fun onBindViewHolder(holder: CalculationHistoryViewHolder, item: CalculationHistory, position: Int) {
        with(holder) {
            operation.text = item.operation
            result.text = item.result
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CalculationHistoryViewHolder {
        return CalculationHistoryViewHolder(inflate(R.layout.item_calculation_history, p0))
    }

    class CalculationHistoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val operation: TextView = view.tvOperation
        val result: TextView = view.tvResult
    }

}