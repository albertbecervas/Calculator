package com.abecerra.calculator.presentation.ui.calculator

import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.view.Gravity
import android.view.View
import android.widget.Button
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseActivity
import com.abecerra.calculator.core.utils.extensions.Data
import com.abecerra.calculator.core.utils.extensions.DataState
import com.abecerra.calculator.core.utils.extensions.observe
import com.abecerra.calculator.core.utils.extensions.toast
import com.abecerra.calculator.presentation.ui.calculator.history.CalculatorHistoryFragment
import kotlinx.android.synthetic.main.activity_calculator.*
import kotlinx.android.synthetic.main.content_advanced_pad.*
import kotlinx.android.synthetic.main.content_display.*
import kotlinx.android.synthetic.main.content_numeric_pad.*
import kotlinx.android.synthetic.main.fragment_calculator.drawerLayout
import kotlinx.android.synthetic.main.fragment_calculator.ivArrow
import kotlinx.android.synthetic.main.fragment_calculator.llAdvanced
import kotlinx.android.synthetic.main.fragment_calculator.padContent
import org.jetbrains.anko.childrenRecursiveSequence
import org.koin.android.viewmodel.ext.android.viewModel

class CalculatorActivity : BaseActivity(), View.OnClickListener {

    private val viewModel: CalculatorViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calculator)

        observe(viewModel.operationData, ::updateOperationData)
        observe(viewModel.result, ::updateResult)

        setViews()
    }

    private fun setViews() {

        supportFragmentManager.beginTransaction()
            .replace(R.id.flHistory, CalculatorHistoryFragment())
            .commit()

        drawerLayout.childrenRecursiveSequence().forEach { view ->
            if (view is Button) view.setOnClickListener(this)
        }

        btDelete.setOnLongClickListener {
            viewModel.clearData()
            true
        }

        ivBack.setOnClickListener {
            onBackPressed()
        }

        btDec.setOnClickListener {
            val decResult = viewModel.result.value?.data
            decResult?.let {
                tvResult.text = it.toString()
            }
        }
        btHex.setOnClickListener {

            if (tvResult.text.isNotBlank()) {
                val decResult = viewModel.result.value?.data
                decResult?.let {
                    val hexResult = Integer.toHexString(it.toInt())
                    tvResult.text = hexResult
                }
            }

        }
        btBin.setOnClickListener {

            val decResult = viewModel.result.value?.data
            decResult?.let {
                val hexResult = Integer.toBinaryString(it.toInt())
                tvResult.text = hexResult
            }
        }

        setAdvancedPad()
    }

    private fun setAdvancedPad() {
        llAdvanced.setOnClickListener {
            if (drawerLayout.isDrawerOpen(Gravity.END)) {
                drawerLayout.closeDrawer(Gravity.END)
            } else {
                drawerLayout.openDrawer(Gravity.END)
            }
        }

        val actionBarDrawerToggle =
            object : ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close) {
                override fun onDrawerSlide(drawerView: View, slideOffset: Float) {
                    super.onDrawerSlide(drawerView, slideOffset)
                    val slideX = drawerView.width * slideOffset * -1
                    padContent.translationX = slideX
                }

                override fun onDrawerClosed(drawerView: View) {
                    super.onDrawerClosed(drawerView)
                    ivArrow.rotation = ivArrow.rotation + 180
                }

                override fun onDrawerOpened(drawerView: View) {
                    super.onDrawerOpened(drawerView)
                    ivArrow.rotation = ivArrow.rotation + 180
                }
            }

        drawerLayout.addDrawerListener(actionBarDrawerToggle)

    }

    override fun onClick(v: View?) {
        v?.let { view ->
            when (view.id) {
                R.id.btZero, R.id.btOne, R.id.btTwo, R.id.btThree, R.id.btFour,
                R.id.btFive, R.id.btSix, R.id.btSeven, R.id.btEight, R.id.btNine, R.id.btDot,
                R.id.btDivide, R.id.btMultiply, R.id.btDif, R.id.btSum, R.id.btOpenPar,
                R.id.btClosePar, R.id.btSqrt, R.id.btSin, R.id.btCos, R.id.btTan, R.id.btLn,
                R.id.btLog, R.id.btFact -> {
                    addValue(view)
                }
                R.id.btEqual -> {
                    viewModel.calculateCurrent()
                }
                R.id.btBin -> {

                }
                R.id.btDelete -> {
                    viewModel.clearLastCharacter()
                }
                else -> {
                }
            }
        }
    }

    private fun addValue(view: View) {
        (view as? Button)?.text?.toString()?.let {
            viewModel.addValueToExpression(it)
            setDefaultColors()
        }
    }

    private fun setErrorColors() {
        tvResult.setTextColor(ContextCompat.getColor(this, R.color.red))
        tvResult.text = getString(R.string.error)
        tvOperation.setTextColor(ContextCompat.getColor(this, R.color.red))

    }

    private fun setDefaultColors() {
        if (tvResult.currentTextColor != R.color.original_blue) {
            tvResult.setTextColor(ContextCompat.getColor(this, R.color.original_blue))
            tvResult.text = ""
            tvOperation.setTextColor(ContextCompat.getColor(this, R.color.black))

        }
    }

    private fun updateOperationData(data: Data<String>?) {
        data?.data?.let {
            tvOperation.text = it
        }
    }

    private fun updateResult(data: Data<Float>?) {
        data?.let {
            when (it.dataState) {
                DataState.LOADING -> {
                    tvResult.text = ""
                }
                DataState.SUCCESS -> {
                    it.data?.let { result ->
                        tvResult.text = result.toString()
                    }
                }
                DataState.ERROR -> {
                    it.message?.let { errorMsg ->
                        setErrorColors()
                        toast(errorMsg)
                    }
                }
            }
        }
    }


}
