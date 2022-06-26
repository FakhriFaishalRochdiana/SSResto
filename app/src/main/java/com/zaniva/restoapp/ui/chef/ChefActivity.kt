package com.zaniva.restoapp.ui.chef

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.zaniva.restoapp.R
import com.zaniva.restoapp.databinding.ActivityChefBinding
import com.zaniva.restoapp.dataclass.Menu
import com.zaniva.restoapp.dataclass.TrayItem
import com.zaniva.restoapp.ui.detail.DetailActivity
import com.zaniva.restoapp.ui.home.Adapter
import com.zaniva.restoapp.ui.home.BevAdapter
import com.zaniva.restoapp.ui.home.PackAdapter

class ChefActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChefBinding
    private lateinit var adapter: ChefAdapter
    private val order = ArrayList<TrayItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChefBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Chef's Table"

        if (order.isEmpty()){
            order.addAll(trayFoodMenu)
            order.addAll(trayBeveragesMenu)
            order.addAll(trayPackageMenu)
            adapter = ChefAdapter(order)
        }

        binding.apply {
            rvTb2.setHasFixedSize(true)
            rvTb2.layoutManager = LinearLayoutManager(this@ChefActivity)
            rvTb2.adapter = adapter
            expandedMenu1.setOnClickListener {
                compactMenu1.visibility = View.VISIBLE
                expandedMenu1.visibility = View.GONE
                rvTb2.visibility = View.VISIBLE
            }
            compactMenu1.setOnClickListener {
                compactMenu1.visibility = View.GONE
                expandedMenu1.visibility = View.VISIBLE
                rvTb2.visibility = View.GONE
            }
        }
    }

    private val trayFoodMenu: ArrayList<TrayItem>
        get() {
            val dataName = resources.getStringArray(R.array.data_food_name)
            val dataPrice = resources.getStringArray(R.array.data_food_price)
            val dataDescription = resources.getStringArray(R.array.data_food_desc)
            val dataAmount = 2
            val dataPhoto = resources.obtainTypedArray(R.array.data_food_photo)
            val listFoodMenu = ArrayList<TrayItem>()
            for (i in 0..dataAmount-1) {
                val food = TrayItem(dataName[i],dataPrice[i], dataDescription[i], dataAmount.toString(),dataPhoto.getResourceId(i, -1))
                listFoodMenu.add(food)
            }
            return listFoodMenu
        }

    private val trayBeveragesMenu: ArrayList<TrayItem>
        get() {
            val dataName = resources.getStringArray(R.array.data_beverage_name)
            val dataPrice = resources.getStringArray(R.array.data_beverage_price)
            val dataDescription = resources.getStringArray(R.array.data_beverage_desc)
            val dataAmount = 2
            val dataPhoto = resources.obtainTypedArray(R.array.data_beverage_photo)
            val listBevMenu = ArrayList<TrayItem>()
            for (i in 0..dataAmount-1) {
                val bev = TrayItem(dataName[i],dataPrice[i], dataDescription[i], dataAmount.toString(),dataPhoto.getResourceId(i, -1))
                listBevMenu.add(bev)
            }
            return listBevMenu
        }

    private val trayPackageMenu: ArrayList<TrayItem>
        get() {
            val dataName = resources.getStringArray(R.array.data_pack_name)
            val dataPrice = resources.getStringArray(R.array.data_pack_price)
            val dataDescription = resources.getStringArray(R.array.data_pack_desc)
            val dataAmount = 2
            val dataPhoto = resources.obtainTypedArray(R.array.data_pack_photo)
            val listPackMenu = ArrayList<TrayItem>()
            for (i in 0..dataAmount-1) {
                val pack = TrayItem(dataName[i],dataPrice[i], dataDescription[i], dataAmount.toString(),dataPhoto.getResourceId(i, -1))
                listPackMenu.add(pack)
            }
            return listPackMenu
        }
}