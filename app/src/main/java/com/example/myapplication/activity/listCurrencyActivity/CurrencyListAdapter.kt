package com.example.myapplication.activity.listCurrencyActivity

import android.annotation.SuppressLint
import android.content.Context
import android.os.Message
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Helper
import com.example.myapplication.R
import com.example.myapplication.databinding.CurrencyListItemBinding
import com.example.myapplication.model.CurrencyModel
import java.util.*
import kotlin.collections.ArrayList

class CurrencyListAdapter(private val onItemClick: (CurrencyModel) -> Unit): RecyclerView.Adapter<CurrencyListAdapter.ViewHolder>(), Filterable {

    private lateinit var context: Context
    private lateinit var searchList: ArrayList<CurrencyModel>

    private var listItems = ArrayList<CurrencyModel>()
    set(value) {
        field = value
        searchList = ArrayList(listItems)
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


    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val binding = CurrencyListItemBinding.bind(view)
        fun bind(model: CurrencyModel) {
            binding.apply {
                currencyName.text = model.name
                currencyChar.text = model.charCode
                currencyValue.text = model.value.toString()
            }

            itemView.setOnClickListener {
                onItemClick(model)
            }

            Helper().getFlagFromCode(view = binding.currencyImage, charCode = model.charCode)

        }
    }

    override fun getFilter(): Filter {
        return filterList
    }

    private val filterList: Filter = object : Filter() {
        override fun performFiltering(constraint: CharSequence): FilterResults {
            val filteredList: MutableList<CurrencyModel> = ArrayList()
            if (constraint.isEmpty()) {
                filteredList.addAll(searchList)
            } else {
                val filterPattern =
                    constraint.toString().lowercase(Locale.getDefault()).trim { it <= ' ' }
                for (item in searchList) {
                    if (item.name.lowercase(Locale.getDefault()).contains(filterPattern)) {
                        filteredList.add(item)
                    }else if (item.charCode.lowercase(Locale.getDefault()).contains(filterPattern)) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(constraint: CharSequence, results: FilterResults) {
            listItems.clear()
            listItems.addAll(results.values as ArrayList<CurrencyModel>)
            notifyDataSetChanged()
        }
    }

}