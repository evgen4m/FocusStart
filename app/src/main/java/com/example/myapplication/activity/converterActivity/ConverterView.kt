package com.example.myapplication.activity.converterActivity

import com.example.myapplication.base.BaseView
import com.example.myapplication.model.CurrencyModel

interface ConverterView: BaseView {

    fun viewInfo(currencyModel: CurrencyModel)

}