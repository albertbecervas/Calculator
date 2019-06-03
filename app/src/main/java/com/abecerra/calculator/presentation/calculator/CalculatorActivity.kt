package com.abecerra.calculator.presentation.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseActivity
import com.abecerra.calculator.core.utils.extensions.Data
import com.abecerra.calculator.core.utils.extensions.DataState.*
import com.abecerra.calculator.core.utils.extensions.observe
import com.abecerra.calculator.core.utils.extensions.toInt
import com.abecerra.calculator.core.utils.extensions.toast
import kotlinx.android.synthetic.main.content_display.*
import kotlinx.android.synthetic.main.content_numeric_pad.*
import kotlinx.android.synthetic.main.content_operations_pad.*
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
        btDelete.setOnLongClickListener {
            viewModel.clearData()
            true
        }
        btDelete.setOnClickListener(this)
        btDot.setOnClickListener(this)

        btZero.setOnClickListener(this)
        btOne.setOnClickListener(this)
        btTwo.setOnClickListener(this)
        btThree.setOnClickListener(this)
        btFour.setOnClickListener(this)
        btFive.setOnClickListener(this)
        btSix.setOnClickListener(this)
        btSeven.setOnClickListener(this)
        btEight.setOnClickListener(this)
        btNine.setOnClickListener(this)

        btDivide.setOnClickListener(this)
        btMultiply.setOnClickListener(this)
        btDif.setOnClickListener(this)
        btSum.setOnClickListener(this)
        btEqual.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let { view ->
            when (view.id) {
                R.id.btZero, R.id.btOne, R.id.btTwo, R.id.btThree, R.id.btFour,
                R.id.btFive, R.id.btSix, R.id.btSeven, R.id.btEight, R.id.btNine -> {
                    (view as? Button)?.text?.toString()?.let {
                        viewModel.addNumber(it.toInt())
                    }
                }
                R.id.btDivide, R.id.btMultiply, R.id.btDif, R.id.btSum -> {
                    (view as? Button)?.text?.toString()?.let {
                        viewModel.addOperation(it)
                    }
                }
                R.id.btEqual -> {
                    viewModel.calculate()
                }
                R.id.btDot -> {
                    viewModel.addDot()
                }
                R.id.btDelete -> {
                    viewModel.clearLastCharacter()
                }
                else -> {
                }
            }
        }
    }

    private fun updateOperationData(data: Data<String>?) {
        data?.let {
            when (it.dataState) {
                LOADING -> {
                }
                SUCCESS -> {
                    it.data?.let { operation ->
                        etOperation.setText(operation)
                    }
                }
                ERROR -> {
                    it.message?.let { errorMsg ->
                        toast(errorMsg)
                    }
                }
            }
        }
    }

    private fun updateResult(data: Data<String>?) {
        data?.let {
            when (it.dataState) {
                LOADING -> {
                }
                SUCCESS -> {
                    it.data?.let { result ->
                        tvResult.text = result
                    }
                }
                ERROR -> {
                    it.message?.let { errorMsg ->
                        toast(errorMsg)
                    }
                }
            }
        }
    }

}
