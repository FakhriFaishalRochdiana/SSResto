package com.zaniva.restoapp.ui.chef

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.zaniva.restoapp.R
import com.zaniva.restoapp.dataclass.Menu
import com.zaniva.restoapp.dataclass.TrayItem

class ChefAdapter(private val listItem: ArrayList<TrayItem>) : RecyclerView.Adapter<ChefAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_cheftable, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, price, description, amount, photo) = listItem[position]
        holder.tvName.text = name
        holder.tvDone.setOnClickListener {
            holder.tvDone.visibility = View.GONE
            holder.ivDone.visibility = View.VISIBLE
        }
        holder.ivDone.setOnClickListener {
            holder.ivDone.visibility = View.GONE
            holder.tvDone.visibility = View.VISIBLE
        }

    }

    override fun getItemCount(): Int = listItem.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tvName: TextView = itemView.findViewById(R.id.tv_foodname)
        var tvDone: TextView = itemView.findViewById(R.id.tv_done)
        var ivDone: ImageView = itemView.findViewById(R.id.iv_done)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: Menu)
    }
}