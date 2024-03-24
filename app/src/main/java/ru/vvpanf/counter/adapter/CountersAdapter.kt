package ru.vvpanf.counter.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.vvpanf.counter.R
import ru.vvpanf.counter.model.Counter

class CountersAdapter(
    var context: Context,
    var itemListener: ItemListener
) : ListAdapter<Counter, RecyclerView.ViewHolder>(CounterCallback()) {
    override fun getItemId(position: Int): Long = getItem(position).id!!

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        CounterViewHolder(LayoutInflater.from(context).inflate(R.layout.item_counter, parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is CounterViewHolder -> {
                holder.bind(getItem(position))
            }
        }
    }

    interface ItemListener {
        fun onOpen(counter: Counter)
        fun onPlus(counter: Counter)
        fun onMinus(counter: Counter)
    }

    inner class CounterViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val mName: TextView = itemView.findViewById(R.id.item_counter_name)
        private val mValue : TextView = itemView.findViewById(R.id.item_counter_value)
        private val mTint: View = itemView.findViewById(R.id.item_counter_tint)
        private val mBtnMinus: Button = itemView.findViewById(R.id.item_counter_minus)
        private val mBtnPlus: Button = itemView.findViewById(R.id.item_counter_plus)

        fun bind(counter: Counter) {
            mName.text = counter.name
            mValue.text = counter.value.toString()
            mTint.setBackgroundColor(counter.color)
            mBtnMinus.setOnClickListener {
                itemListener.onMinus(counter)
            }
            mBtnPlus.setOnClickListener {
                itemListener.onPlus(counter)
            }
            itemView.setOnClickListener {
                itemListener.onOpen(counter)
            }
        }
    }

    class CounterCallback : DiffUtil.ItemCallback<Counter>() {
        override fun areItemsTheSame(old: Counter, new: Counter): Boolean =
            old.id == new.id

        override fun areContentsTheSame(old: Counter, new: Counter): Boolean =
            old == new
    }
}