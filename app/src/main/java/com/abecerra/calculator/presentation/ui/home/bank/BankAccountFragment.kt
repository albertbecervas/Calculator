package com.abecerra.calculator.presentation.ui.home.bank


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseFragment
import com.abecerra.calculator.core.utils.extensions.Data
import com.abecerra.calculator.core.utils.extensions.DataState.*
import com.abecerra.calculator.core.utils.extensions.observe
import com.abecerra.calculator.presentation.model.BankAccount
import kotlinx.android.synthetic.main.fragment_bank_account.*
import org.koin.android.viewmodel.ext.android.viewModel


class BankAccountFragment : BaseFragment() {

    private val viewModel: BankAccountViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observe(viewModel.bankAccount, ::updateBankAccount)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bank_account, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getBankAccount()
    }

    private fun setBankAccountData(bankAccount: BankAccount) {
        tvMoney.text = bankAccount.money
        tvBankAccount.text = bankAccount.accountNumber
    }

    private fun updateBankAccount(data: Data<BankAccount>?) {
        data?.let {
            when (it.dataState) {
                LOADING -> {
                    showLoading()
                }
                SUCCESS -> {
                    it.data?.let { bankAccount ->
                        setBankAccountData(bankAccount)
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
