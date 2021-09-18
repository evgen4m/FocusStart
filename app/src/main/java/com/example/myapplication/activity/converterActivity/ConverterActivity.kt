package com.example.myapplication.activity.converterActivity

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myapplication.Helper
import com.example.myapplication.app.App
import com.example.myapplication.databinding.ActivityConverterBinding
import com.example.myapplication.model.CurrencyModel

class ConverterActivity : AppCompatActivity(), ConverterView {

    lateinit var converterBinding: ActivityConverterBinding

    companion object {
        private const val EXTRA_ID = "EXTRA_ID"
        fun start(context: Context, id: String) {
            val intent = Intent(context, ConverterActivity::class.java).apply {
                putExtra(EXTRA_ID, id)
            }
            context.startActivity(intent)
        }

    }

    private val presenter by lazy {
        val repository = (application as App).repository
        intent.getStringExtra(EXTRA_ID)?.let { ConverterPresenter(id = it, repository = repository) }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        converterBinding = ActivityConverterBinding.inflate(layoutInflater)
        setContentView(converterBinding.root)
        presenter!!.attachView(this)
    }

    @SuppressLint("SetTextI18n")
    override fun viewInfo(currencyModel: CurrencyModel) {
        Helper().getFlagFromCode(view = converterBinding.flag, charCode = currencyModel.charCode)
        val anyCurrency = Helper().getOneAnyCurrency(nominal = currencyModel.nominal, value = currencyModel.value)
        val rusCurrency = Helper().getOneRusCurrency(nominal = currencyModel.nominal, value = currencyModel.value)

        converterBinding.apply {
            currencyName.text = "${currencyModel.name}: "
            infoAnyCurrency.text = "1 ${currencyModel.name} = " + anyCurrency
            infoRusCurrency.text = "1 Российских рублей = $rusCurrency"

            imageConvert.setOnClickListener {
                val text = getRusCurrency.text.toString()
                if (text.isNotEmpty()) {
                    getAnyCurrency.text = Helper().getAny(valueRus = text, valueAny = rusCurrency).toString()
                }
                else {
                    Toast.makeText(this@ConverterActivity, "Введите сумму в рублях!", Toast.LENGTH_LONG).show()
                }
            }

        }
    }
}