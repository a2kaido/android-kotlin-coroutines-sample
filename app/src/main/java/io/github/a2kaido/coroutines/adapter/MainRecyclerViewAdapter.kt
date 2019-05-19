package io.github.a2kaido.coroutines.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import io.github.a2kaido.coroutines.R

class MainRecyclerViewAdapter(
    private val onClick: (Int) -> Unit
) : ListAdapter<String, MainViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val holder = MainViewHolder(parent)
        holder.itemView.setOnClickListener {
            onClick(holder.adapterPosition)
        }
        return holder
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val string = getItem(position)
        holder.textView.text = string
    }
}

class MainViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.item_main, parent, false)
) {
    val textView: TextView = itemView.findViewById(R.id.item_text)
}

private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<String>() {

    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}
