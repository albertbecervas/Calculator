package com.abecerra.calculator.presentation.ui.main

import android.os.Bundle
import android.support.v4.content.ContextCompat
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseActivity
import com.abecerra.calculator.core.utils.CustomPagerAdapter
import com.abecerra.calculator.presentation.ui.home.HomeFragment
import com.abecerra.calculator.presentation.ui.payment.PaymentFragment
import com.abecerra.calculator.presentation.ui.statistics.StatisticsFragment
import com.aurelhubert.ahbottomnavigation.AHBottomNavigation
import com.aurelhubert.ahbottomnavigation.AHBottomNavigationItem
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setViews()
    }

    private fun setViews() {
        val pagerAdapter = CustomPagerAdapter(supportFragmentManager)

        pagerAdapter.addFragments(HomeFragment(), getString(R.string.home))
        pagerAdapter.addFragments(PaymentFragment(), getString(R.string.finance))
        pagerAdapter.addFragments(StatisticsFragment(), getString(R.string.statistics))
        viewPager.adapter = pagerAdapter
        viewPager.setPagingEnabled(false)

        val home = AHBottomNavigationItem(getString(R.string.home), R.drawable.ic_home)
        val finances = AHBottomNavigationItem(getString(R.string.finance), R.drawable.ic_finance)
        val calculator = AHBottomNavigationItem(getString(R.string.calculator), R.drawable.ic_calculator)
        val statistics = AHBottomNavigationItem(getString(R.string.statistics), R.drawable.ic_statistics)
        val about = AHBottomNavigationItem(getString(R.string.about), R.drawable.ic_about)
        val itemList = listOf(home, finances, calculator, statistics, about)
        bottomNavigation.addItems(itemList)

        bottomNavigation.titleState = AHBottomNavigation.TitleState.ALWAYS_SHOW
        bottomNavigation.defaultBackgroundColor =
            ContextCompat.getColor(this, R.color.white)
        bottomNavigation.accentColor = ContextCompat.getColor(this, R.color.original_blue)
        bottomNavigation.isForceTint = true

        bottomNavigation.setOnTabSelectedListener { position, wasSelected ->
            return@setOnTabSelectedListener if (!wasSelected) {
                navigateTo(position)
            } else false
        }
    }

    private fun navigateTo(position: Int): Boolean {
        return when (position) {
            0 -> {
                viewPager.setCurrentItem(0, false)
                true
            }
            1 -> {
                viewPager.setCurrentItem(1, false)
                true
            }
            2 -> {
                navigator.navigateToCalculator()
                false
            }
            3 -> {
                viewPager.setCurrentItem(2, false)
                true
            }
            4 -> {
                navigator.navigateToAbout()
                false
            }
            else -> false
        }
    }

}
