package com.imorning.accountbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imorning.accountbook.R
import com.imorning.accountbook.databinding.BookItemLayoutBinding
import com.imorning.accountbook.entity.IncomeData
import java.text.SimpleDateFormat
import java.util.*

class IncomeAdapter(private val onItemClicked: (IncomeData) -> Unit) :
    ListAdapter<IncomeData, IncomeAdapter.IncomeViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
        val viewHolder: IncomeViewHolder = IncomeViewHolder(
            BookItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class IncomeViewHolder(private var binding: BookItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(incomeData: IncomeData) {
            binding.bookItemChange.text = incomeData.value.toString()
            binding.bookItemTime.text =
                SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(incomeData.date)
            binding.bookItemType.text = incomeData.type
        }

    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<IncomeData>() {
            override fun areItemsTheSame(oldItem: IncomeData, newItem: IncomeData): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: IncomeData, newItem: IncomeData): Boolean {
                return oldItem == newItem
            }

        }
    }
}