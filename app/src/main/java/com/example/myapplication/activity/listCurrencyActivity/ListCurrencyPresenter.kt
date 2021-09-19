package com.example.myapplication.activity.listCurrencyActivity

import com.example.myapplication.base.BasePresenter
import com.example.myapplication.model.CurrencyModel
import com.example.myapplication.model.CurrencyRepository

class ListCurrencyPresenter(private val repository: CurrencyRepository): BasePresenter<ListCurrencyView>() {

    fun getAllCurrency() {
        repository.getAll {
            view?.getCurrencyList(it)
        }
    }

    fun openConvertScreen(currencyModel: CurrencyModel) {
        view?.openConvertScreen(id = currencyModel.id)
    }

}