package com.imorning.accountbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imorning.accountbook.databinding.RecordItemLayoutBinding
import com.imorning.accountbook.entity.ExpenseRecordEntity
import com.imorning.accountbook.utils.TimeUtils

class DisburseAdapter(private val onItemClicked: (ExpenseRecordEntity) -> Unit) :
    ListAdapter<ExpenseRecordEntity, DisburseAdapter.DisburseViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DisburseViewHolder {
        val viewHolder: DisburseViewHolder = DisburseViewHolder(
            RecordItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.bindingAdapterPosition
            if (position < 0) return@setOnClickListener
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: DisburseViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class DisburseViewHolder(private var binding: RecordItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: ExpenseRecordEntity) {
            binding.bookItemChange.text = data.value.toString()
            binding.bookItemTime.text = TimeUtils.dateFormatter(date = data.date)
            binding.bookItemType.text = data.type
        }
    }

    companion object {

        private val DiffCallback = object : DiffUtil.ItemCallback<ExpenseRecordEntity>() {
            override fun areItemsTheSame(oldItem: ExpenseRecordEntity, newItem: ExpenseRecordEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: ExpenseRecordEntity, newItem: ExpenseRecordEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}