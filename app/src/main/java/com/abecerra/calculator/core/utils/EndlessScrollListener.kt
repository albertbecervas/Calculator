package com.abecerra.calculator.core.utils

import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView

class EndlessScrollListener(private val loadMore: EndlessScrollListener.() -> Unit) : RecyclerView.OnScrollListener() {
    var currentPage = 0
    var keepLoading = false

    override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
        when (newState) {
            RecyclerView.SCROLL_STATE_IDLE -> {
                val linearLayoutManager = if (recyclerView.layoutManager is LinearLayoutManager) {
                    recyclerView.layoutManager as LinearLayoutManager
                } else {
                    recyclerView.layoutManager as GridLayoutManager
                }

                val totalItems = linearLayoutManager.itemCount
                val lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition()

                if (lastVisibleItem != 0 && lastVisibleItem == totalItems - 1 && keepLoading) {
                    currentPage = currentPage.inc()
                    loadMore()
                }
            }
        }
    }
}