package com.abecerra.calculator.presentation.home


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseFragment
import com.abecerra.calculator.presentation.home.bank.BankAccountFragment
import com.abecerra.calculator.presentation.home.transactions.TransactionsFragment

class HomeFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        childFragmentManager.beginTransaction()
            .replace(R.id.flAccount, BankAccountFragment())
            .commit()

        childFragmentManager.beginTransaction()
            .replace(R.id.flTransactions, TransactionsFragment())
            .commit()
    }


}
