package com.abecerra.calculator.presentation.home.bank


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseFragment


class BankAccountFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bank_account, container, false)
    }

}
