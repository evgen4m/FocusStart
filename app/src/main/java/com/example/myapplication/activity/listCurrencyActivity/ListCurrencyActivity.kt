package com.example.myapplication.activity.listCurrencyActivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.inputmethod.EditorInfo
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.activity.converterActivity.ConverterActivity
import com.example.myapplication.app.App
import com.example.myapplication.databinding.ActivityMainBinding
import com.example.myapplication.model.CurrencyModel
import java.util.*


class ListCurrencyActivity : AppCompatActivity(), ListCurrencyView {

    lateinit var binding: ActivityMainBinding

    var timer: Timer? = null

    private val presenter by lazy {
        ListCurrencyPresenter((application as App).repository)
    }

    private val currencyAdapter = CurrencyListAdapter {
        presenter.openConvertScreen(it)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        presenter.attachView(this)


        // Запускаем таймер для обращения на сервер раз в минуту
        timer = Timer()
        timer!!.scheduleAtFixedRate(Task(), 0, 60000)

        val lim = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.currencyList.apply {
            layoutManager = lim
            adapter = currencyAdapter
        }

    }

    override fun getCurrencyList(list: List<CurrencyModel>) {
        currencyAdapter.updateList(list)
    }

    override fun openConvertScreen(id: String) {
        ConverterActivity.start(this, id)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        val searchItem = menu?.findItem(R.id.searchNote)
        val searchView = searchItem?.actionView as SearchView
        searchView.imeOptions = EditorInfo.IME_ACTION_DONE
        searchView.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                currencyAdapter.filter.filter(newText)
                return false
            }
        })
        return super.onCreateOptionsMenu(menu)
    }

    inner class Task: TimerTask() {
        override fun run() {
            runOnUiThread {
                presenter.getAllCurrency()
            }
        }
    }
}