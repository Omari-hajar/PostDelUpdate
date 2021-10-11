package com.example.postdelupdate

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.postdelupdate.databinding.ItemBinding


class RVAdapter: RecyclerView.Adapter<RVAdapter.ViewHolder>() {
    class ViewHolder(val binding: ItemBinding): RecyclerView.ViewHolder(binding.root) {


    }

    private val diffCallback = object : DiffUtil.ItemCallback<Data>(){
        override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem.pk == newItem.pk
        }

        override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
            return oldItem == newItem
        }

    }


    private val differ = AsyncListDiffer(this, diffCallback)
    var items: List<Data>
        get() = differ.currentList
        set(value){differ.submitList(value)}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]

        //todo: why are pk not showing up ?
       holder.binding.tvPk.text = item.pk
        holder.binding.tvItem.text = item.name
        holder.binding.tvLocation.text = item.location
    }

    override fun getItemCount(): Int {
        return items.size
    }


}