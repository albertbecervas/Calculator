package com.abecerra.calculator.presentation.finance


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseFragment

class FinanceFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_finance, container, false)
    }


}
