package com.abecerra.calculator.presentation.home.transactions

import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.abecerra.calculator.R
import com.abecerra.calculator.core.base.BaseAdapter
import com.abecerra.calculator.core.utils.extensions.inflate
import com.abecerra.calculator.presentation.data.Transaction
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_transaction.view.*


class TransactionsAdapter : BaseAdapter<TransactionsAdapter.TransactionViewHolder, Transaction>() {
    override fun onBindViewHolder(holder: TransactionViewHolder, item: Transaction, position: Int) {
        with(holder) {
            name.text = item.name
            date.text = item.date
            amount.text = item.amount

            if (item.isReceived) {
                Glide.with(transaction.context).load(R.drawable.ic_trans_enter).into(transaction)
            } else {
                Glide.with(transaction.context).load(R.drawable.ic_trans_out).into(transaction)
            }
        }
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): TransactionViewHolder {
        return TransactionViewHolder(inflate(R.layout.item_transaction, p0))
    }

    class TransactionViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val name: TextView = view.tvName
        val date: TextView = view.tvDate
        val amount: TextView = view.tvTransactionAmount
        val transaction: ImageView = view.ivTransaction

    }

}