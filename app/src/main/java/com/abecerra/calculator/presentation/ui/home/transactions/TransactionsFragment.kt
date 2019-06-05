package com.abecerra.calculator.presentation.ui.home.transactions


import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseFragment
import com.abecerra.calculator.core.utils.extensions.Data
import com.abecerra.calculator.core.utils.extensions.DataState.*
import com.abecerra.calculator.core.utils.extensions.observe
import com.abecerra.calculator.presentation.model.Transaction
import kotlinx.android.synthetic.main.fragment_transactions.*
import org.koin.android.viewmodel.ext.android.viewModel


class TransactionsFragment : BaseFragment() {

    private val viewModel: TransactionsViewModel by viewModel()

    private var adapter: TransactionsAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observe(viewModel.transactions, ::updateTransaction)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_transactions, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = TransactionsAdapter()
        rvTransactions.layoutManager = LinearLayoutManager(view.context)
        rvTransactions.adapter = adapter

        viewModel.getTransactions()
    }

    private fun updateTransaction(data: Data<List<Transaction>>?) {
        data?.let {
            when (it.dataState) {
                LOADING -> {
                    showLoading()
                }
                SUCCESS -> {
                    it.data?.let { transactions ->
                        adapter?.setItems(transactions)
                    }
                    hideLoading()
                }
                ERROR -> {
                    showError(it.message)
                }
            }
        }
    }

}
