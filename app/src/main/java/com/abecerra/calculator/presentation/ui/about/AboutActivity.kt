package com.abecerra.calculator.presentation.ui.about

import android.os.Bundle
import com.abecerra.calculator.BuildConfig
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseActivity
import kotlinx.android.synthetic.main.content_app_info.*

class AboutActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tvVersion.text = BuildConfig.VERSION_NAME

        tvContact.setOnClickListener {
            navigator.sendEmail(getString(R.string.contact_email))
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

}
