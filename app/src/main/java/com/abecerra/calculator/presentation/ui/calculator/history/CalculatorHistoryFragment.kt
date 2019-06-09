package com.abecerra.calculator.presentation.ui.calculator.history


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseFragment
import com.abecerra.calculator.core.utils.extensions.Data
import com.abecerra.calculator.core.utils.extensions.observe
import com.abecerra.calculator.presentation.model.CalculationHistory
import kotlinx.android.synthetic.main.fragment_calculator_history.*
import org.koin.android.viewmodel.ext.android.viewModel

class CalculatorHistoryFragment : BaseFragment() {

    private val viewModel: CalculatorHistoryViewModel by viewModel()

    private var adapter: CalculationHistoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.history, ::updateHistory)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_calculator_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = CalculationHistoryAdapter()
        rvHistory.layoutManager = LinearLayoutManager(view.context)
        rvHistory.adapter = adapter

        viewModel.getHistory()
    }

    private fun updateHistory(data: Data<List<CalculationHistory>>?) {
        data?.data?.let {
            adapter?.setItems(it)
        }
    }


}
