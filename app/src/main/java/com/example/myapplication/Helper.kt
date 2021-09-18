package com.example.myapplication

import android.widget.ImageView

class Helper {

    fun getFlagFromCode(view: ImageView, charCode: String) {
            view.apply {
                setImageResource(
                    when(charCode) {
                        "CHF" -> R.drawable.chf_flag
                        "KZT" -> R.drawable.kzt_flag
                        "ZAR" -> R.drawable.zar_flag
                        "INR" -> R.drawable.inr_flag
                        "CNY" -> R.drawable.chy_flag
                        "UZS" -> R.drawable.uzs_flag
                        "AUD" -> R.drawable.aud_flag
                        "KRW" -> R.drawable.krw_flag
                        "JPY" -> R.drawable.jpy_flag
                        "PLN" -> R.drawable.pln_flag
                        "GBP" -> R.drawable.gbp_flag
                        "MDL" -> R.drawable.mld_flag
                        "BYN" -> R.drawable.byn_flag
                        "AMD" -> R.drawable.amd_flag
                        "HUF" -> R.drawable.huf_flag
                        "TRY" -> R.drawable.try_flag
                        "TJS" -> R.drawable.tjs_flag
                        "HKD" -> R.drawable.hkd_flag
                        "EUR" -> R.drawable.eur_flag
                        "DKK" -> R.drawable.dkk_flag
                        "USD" -> R.drawable.usd_flag
                        "CAD" -> R.drawable.cad_flag
                        "XDR" -> 0
                        "BGN" -> R.drawable.bgn_flag
                        "NOK" -> R.drawable.nok_flag
                        "RON" -> R.drawable.ron_flag
                        "SGD" -> R.drawable.sgd_flag
                        "AZN" -> R.drawable.azn_flag
                        "CZK" -> R.drawable.czk_flag
                        "KGS" -> R.drawable.kgs_flag
                        "SEK" -> R.drawable.sweden_flag
                        "TMT" -> R.drawable.tmt_flag
                        "BRL" -> R.drawable.brl_flag
                        "UAH" -> R.drawable.uah_flag
                        else -> 0
                    }
                )
            }
    }

    fun getOneAnyCurrency(nominal: Float, value: Float): Float = value / nominal

    fun getOneRusCurrency(nominal: Float, value: Float): Float = 1 / getOneAnyCurrency(nominal = nominal, value = value)

    fun getAny(valueRus: String, valueAny: Float): Float = valueRus.toFloat() * valueAny

}