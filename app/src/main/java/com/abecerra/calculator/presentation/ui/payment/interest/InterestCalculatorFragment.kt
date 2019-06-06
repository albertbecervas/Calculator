package com.abecerra.calculator.presentation.ui.payment.interest


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseFragment
import com.abecerra.calculator.core.utils.CustomTextWatcher
import com.abecerra.calculator.core.utils.extensions.Data
import com.abecerra.calculator.core.utils.extensions.observe
import kotlinx.android.synthetic.main.content_interest_calculator.*
import org.koin.android.viewmodel.ext.android.viewModel


class InterestCalculatorFragment : BaseFragment() {

    private val viewModel: InterestCalculatorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        with(viewModel) {
            observe(calculatedValue, ::updateCalculatedValue)
            observe(calculatedInterest, ::updateCalculatedInterest)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_interest_calculator, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setViews()
    }

    private fun setViews() {
        clearResult()

        etTotalValue.addTextChangedListener(CustomTextWatcher {
            if (it.isNotBlank()) viewModel.totalValue = it.toFloat()
            else clearResult()
            viewModel.calculateInterest()
        })

        etRateValue.addTextChangedListener(CustomTextWatcher {
            if (it.isNotBlank()) viewModel.rate = it.toFloat()
            else clearResult()
            viewModel.calculateInterest()
        })

        etPeriodValue.addTextChangedListener(CustomTextWatcher {
            if (it.isNotBlank()) viewModel.period = it.toFloat()
            else clearResult()
            viewModel.calculateInterest()
        })
    }

    private fun clearResult() {
        tvResult.text = getString(R.string.final_value, "")
        tvInterest.text = getString(R.string.interest, "")
    }

    private fun updateCalculatedValue(data: Data<String>?) {
        data?.data?.let {
            tvResult.text = it
        }
    }

    private fun updateCalculatedInterest(data: Data<String>?) {
        data?.data?.let {
            tvResult.text = it
        }
    }

}
