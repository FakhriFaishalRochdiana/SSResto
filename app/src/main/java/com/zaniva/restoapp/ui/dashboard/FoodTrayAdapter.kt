package com.zaniva.restoapp.ui.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.zaniva.restoapp.R
import com.zaniva.restoapp.dataclass.TrayItem

class FoodTrayAdapter(private val listItem: ArrayList<TrayItem>) : RecyclerView.Adapter<FoodTrayAdapter.ListViewHolder>() {
    private lateinit var onItemClickCallback: OnItemClickCallback
    private var activate: Boolean = false

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.rv_tray, parent, false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (name, price, description, amount, photo) = listItem[position]
        var count = amount.toInt()
        holder.imgPhoto.setImageResource(photo)
        holder.tvName.text = name
        holder.tvPrice.text = price
        holder.tvAmount.text = count.toString()
        holder.btMin.setOnClickListener {
            if (count == 0){
                count = count
            } else {
                count -= 1
            }
            holder.tvAmount.text = count.toString()
        }
        holder.btPlus.setOnClickListener {
            count += 1
            holder.tvAmount.text = count.toString()
        }
        if (activate){
            holder.btMin.visibility = View.GONE
            holder.btPlus.visibility = View.GONE
        } else {
            holder.btMin.visibility = View.VISIBLE
            holder.btPlus.visibility = View.VISIBLE
        }

    }

    override fun getItemCount(): Int = listItem.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.iv_item)
        var tvName: TextView = itemView.findViewById(R.id.tv_label_item)
        var tvPrice: TextView = itemView.findViewById(R.id.tv_harga_item)
        var tvAmount: TextView = itemView.findViewById(R.id.tv_amount_item)
        var btPlus: Button = itemView.findViewById(R.id.bt_plus)
        var btMin: Button = itemView.findViewById(R.id.bt_min)
    }

    fun hideBtn(active: Boolean){
        activate = active
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: TrayItem)
    }
}