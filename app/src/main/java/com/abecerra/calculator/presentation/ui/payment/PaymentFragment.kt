package com.abecerra.calculator.presentation.ui.payment


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseFragment
import com.abecerra.calculator.presentation.ui.payment.interest.InterestCalculatorFragment
import kotlinx.android.synthetic.main.fragment_payment.*

class PaymentFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_payment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUserCards()

        childFragmentManager.beginTransaction()
            .replace(R.id.flInterestCalculator, InterestCalculatorFragment())
            .commit()

    }

    private fun setUserCards() {
        val cardsAdapter = PaymentMethodsPagerAdapter()
        vpCards.adapter = cardsAdapter
        cardsAdapter.setItems(listOf("", "", ""))
        tlCards.setupWithViewPager(vpCards)
    }


}
