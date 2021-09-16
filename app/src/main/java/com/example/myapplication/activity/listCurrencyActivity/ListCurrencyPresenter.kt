package com.example.myapplication.activity.listCurrencyActivity

import com.example.myapplication.base.BasePresenter
import com.example.myapplication.model.CurrencyRepository

class ListCurrencyPresenter(private val repository: CurrencyRepository): BasePresenter<ListCurrencyView>() {

    fun onResume() {
        repository.getAll {
            view?.onScreenResumed(it)
            println(it)
        }
    }

}