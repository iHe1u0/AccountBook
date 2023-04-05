package cc.imorning.accountbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import cc.imorning.accountbook.databinding.RecordItemLayoutBinding
import cc.imorning.accountbook.entity.IncomeRecordEntity
import cc.imorning.accountbook.utils.TimeUtils

class IncomeAdapter(private val onItemClicked: (IncomeRecordEntity) -> Unit) :
    ListAdapter<IncomeRecordEntity, IncomeAdapter.IncomeViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IncomeViewHolder {
        val viewHolder: IncomeViewHolder = IncomeViewHolder(
            RecordItemLayoutBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
        viewHolder.itemView.setOnClickListener {
            val position = viewHolder.adapterPosition
            if (position < 0) return@setOnClickListener
            onItemClicked(getItem(position))
        }
        return viewHolder
    }

    override fun onBindViewHolder(holder: IncomeViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class IncomeViewHolder(private var binding: RecordItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(data: IncomeRecordEntity) {
            binding.apply {
                bookItemChange.text = data.value.toString()
                bookItemTime.text = TimeUtils.dateFormatter(date = data.date)
                bookItemType.text = data.type
            }
        }

    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<IncomeRecordEntity>() {
            override fun areItemsTheSame(oldItem: IncomeRecordEntity, newItem: IncomeRecordEntity): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: IncomeRecordEntity, newItem: IncomeRecordEntity): Boolean {
                return oldItem == newItem
            }
        }
    }
}