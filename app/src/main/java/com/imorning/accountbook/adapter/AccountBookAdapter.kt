package com.imorning.accountbook.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.imorning.accountbook.R
import com.imorning.accountbook.databinding.BookItemLayoutBinding
import com.imorning.accountbook.entity.Book
import com.imorning.accountbook.utils.Const
import java.text.SimpleDateFormat
import java.util.*

class AccountBookAdapter(private val onItemClicked: (Book) -> Unit) :
    ListAdapter<Book, AccountBookAdapter.BookViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val viewHolder: BookViewHolder = BookViewHolder(
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

    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class BookViewHolder(private var binding: BookItemLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(book: Book) {
            when (book.isIncome) {
                Const.IsIncome.YES.ordinal -> {
                    binding.bookItemChange.apply {
                        text = "+${book.income}"
                        setTextColor(resources.getColor(R.color.income, resources.newTheme()))
                    }
                }
                Const.IsIncome.NO.ordinal -> {
                    binding.bookItemChange.apply {
                        text = "-${book.disburse}"
                        setTextColor(resources.getColor(R.color.disburse, resources.newTheme()))
                    }
                }
            }
            binding.bookItemTime.text =
                SimpleDateFormat("yyyy-MM-dd hh:mm:ss", Locale.getDefault()).format(book.date)
            binding.bookItemType.text = book.remark
            binding.bookItemBalance.text =
                "${binding.root.context.getString(R.string.balance)}: ${book.balance}"
        }

    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<Book>() {
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }

        }
    }
}