package com.example.myapplication.activity.listCurrencyActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.app.App
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.CurrencyModel


class ListCurrencyActivity : AppCompatActivity(), ListCurrencyView {

    lateinit var binding: ActivityMainBinding
    lateinit var currencyAdapter: CurrencyListAdapter

    private val presenter by lazy {
        ListCurrencyPresenter((application as App).repository)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.attachView(this)

        currencyAdapter = CurrencyListAdapter()
        val lim = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.currencyList.apply {
            layoutManager = lim
            adapter = currencyAdapter

        }

    }

    override fun onResume() {
        super.onResume()
        presenter.onResume()
    }

    override fun onScreenResumed(list: List<CurrencyModel>) {
        currencyAdapter.updateList(list)
    }
}