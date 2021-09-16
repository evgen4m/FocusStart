package com.example.myapplication.activity.listCurrencyActivity

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.databinding.CurrencyListItemBinding
import com.example.myapplication.model.CurrencyModel

class CurrencyListAdapter: RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>() {

    private lateinit var context: Context

    var listItems = ArrayList<CurrencyModel>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    fun updateList(list: List<CurrencyModel>) {
        listItems = list as ArrayList<CurrencyModel>
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.currency_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount(): Int = listItems.size


    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = CurrencyListItemBinding.bind(view)
        fun bind(model: CurrencyModel) {
            binding.apply {
                currencyName.text = model.name
                currencyChar.text = model.charCode
                currencyValue.text = model.value.toString()

            }
        }
    }

}