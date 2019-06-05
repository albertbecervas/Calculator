package com.abecerra.calculator.presentation.ui.payment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseFragment
import com.abecerra.calculator.core.utils.extensions.Data
import com.abecerra.calculator.core.utils.extensions.DataState.*
import com.abecerra.calculator.core.utils.extensions.observe
import com.abecerra.calculator.presentation.model.Currency
import kotlinx.android.synthetic.main.fragment_payment.*
import org.koin.android.viewmodel.ext.android.viewModel

class PaymentFragment : BaseFragment() {

    private val viewModel: PaymentViewModel by viewModel()

    private var fromAdapter: ArrayAdapter<String>? = null
    private var toAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observe(viewModel.currencyList, ::updateCurrencyList)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fromAdapter = ArrayAdapter(view.context, R.layout.item_currency, R.id.tvCurrency)
        toAdapter = ArrayAdapter(view.context, android.R.layout.simple_spinner_item)

        spinnerFrom.adapter = fromAdapter
        spinnerTo.adapter = toAdapter

        viewModel.getCurrencies()
    }

    private fun updateCurrencyList(data: Data<List<Currency>>?) {
        data?.let {
            when (it.dataState) {
                LOADING -> {

                }
                SUCCESS -> {
                    it.data?.let { currencyList ->
                        fromAdapter?.addAll(currencyList.map { it.id })
                        toAdapter?.addAll(currencyList.map { it.id })
                    }
                }
                ERROR -> {

                }
            }
        }
    }

}
