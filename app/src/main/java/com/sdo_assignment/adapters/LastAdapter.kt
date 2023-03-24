package com.sdo_assignment.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.sdo_assignment.BR

/**
 * Generic RecyclerView Adapter to be utilize with single item RecyclerView
 *
 * @author: Talha Bin Tariq
 */
class LastAdapter<T> internal constructor(
    private val layout: Int,
    private val itemClickListener: OnItemClickListener<T>
) : RecyclerView.Adapter<LastAdapter<T>.MyViewHolder>() {

    var items: List<T> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    interface OnItemClickListener<T> {
        fun onItemClick(item: T)

        @JvmDefault
        fun onSubItemOneClick(item: T) {
        }

        @JvmDefault
        fun onSubItemTwoClick(item: T) {
        }

        @JvmDefault
        fun onSubItemThreeClick(item: T) {
        }

        @JvmDefault
        fun onSubItemFourClick(item: T) {
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ViewDataBinding>(layoutInflater, viewType, parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.setOnClickListener {
            itemClickListener.onItemClick(item)
            holder.itemView.isSelected = true
        }
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun getItemViewType(position: Int): Int {
        return layout
    }

    inner class MyViewHolder(private val binding: ViewDataBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(item: T) {
            binding.setVariable(BR.item, item)
            binding.setVariable(BR.listener, itemClickListener)
            binding.executePendingBindings()
        }
    }
}
