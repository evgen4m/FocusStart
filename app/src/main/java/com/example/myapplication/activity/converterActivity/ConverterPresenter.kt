package com.example.myapplication.activity.converterActivity

import com.example.myapplication.base.BasePresenter
import com.example.myapplication.model.CurrencyRepository

class ConverterPresenter(
    private val id: String,
    private val repository: CurrencyRepository
): BasePresenter<ConverterView>() {

    override fun onAttachView() {
        repository.getFromId(currencyId = id) {
            view?.viewInfo(it)
        }
    }

}