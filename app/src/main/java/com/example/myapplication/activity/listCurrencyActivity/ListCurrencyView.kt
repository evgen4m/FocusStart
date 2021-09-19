package com.example.myapplication.activity.listCurrencyActivity

import com.example.myapplication.base.BaseView
import com.example.myapplication.model.CurrencyModel

interface ListCurrencyView: BaseView {

    fun getCurrencyList(list: List<CurrencyModel>)

    fun openConvertScreen(id: String)

}