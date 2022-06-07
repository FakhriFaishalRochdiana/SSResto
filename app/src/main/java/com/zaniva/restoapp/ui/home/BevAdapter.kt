package com.zaniva.restoapp.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zaniva.restoapp.R
import com.zaniva.restoapp.dataclass.Menu

class BevAdapter(private val listItem: ArrayList<Menu>) : RecyclerView.Adapter<BevAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_item, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, price, description, photo) = listItem[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvPrice.text = price
        holder.itemView.setOnClickListener { onItemClickCallback.onItemClicked(listItem[holder.adapterPosition]) }

    }

    override fun getItemCount(): Int = listItem.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.iv_item)
        var tvName: TextView = itemView.findViewById(R.id.tv_label_item)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_harga_item)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Menu)
    }
}