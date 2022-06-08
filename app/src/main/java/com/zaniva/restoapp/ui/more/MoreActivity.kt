package com.zaniva.restoapp.ui.more

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import com.zaniva.restoapp.R
import com.zaniva.restoapp.databinding.ActivityMoreBinding
import com.zaniva.restoapp.dataclass.Menu
import com.zaniva.restoapp.ui.detail.DetailActivity
import com.zaniva.restoapp.ui.home.Adapter

class MoreActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMoreBinding
    private lateinit var adapter: Adapter
    private val list = ArrayList<Menu>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMoreBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getStringExtra("Type")
        setData(type.toString())
        binding.apply {
            rvMore.setHasFixedSize(true)
            rvMore.layoutManager = GridLayoutManager(this@MoreActivity, 2)
            rvMore.adapter = adapter
        }

    }

    fun setData(type: String) {
        if (list.isEmpty()) {
            when (type) {
                "Makanan" -> list.addAll(listFoodMenu)
                "Minuman" -> list.addAll(listBeveragesMenu)
                "Paket" -> list.addAll(listPackageMenu)
            }
            adapter = Adapter(list)
            adapter.setOnItemClickCallback(object : Adapter.OnItemClickCallback{
                override fun onItemClicked(data: Menu) {
                    Intent(this@MoreActivity, DetailActivity::class.java).also {
                        finish()
                        it.putExtra("Data", data)
                        startActivity(it)
                    }
                }
            })
        }
    }

    private val listFoodMenu: ArrayList<Menu>
        get() {
            val dataName = resources.getStringArray(R.array.data_food_name)
            val dataPrice = resources.getStringArray(R.array.data_food_price)
            val dataDescription = resources.getStringArray(R.array.data_food_desc)
            val dataPhoto = resources.obtainTypedArray(R.array.data_food_photo)
            val listFoodMenu = ArrayList<Menu>()
            for (i in dataName.indices) {
                val food = Menu(dataName[i],dataPrice[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
                listFoodMenu.add(food)
            }
            return listFoodMenu
        }

    private val listBeveragesMenu: ArrayList<Menu>
        get() {
            val dataName = resources.getStringArray(R.array.data_beverage_name)
            val dataPrice = resources.getStringArray(R.array.data_beverage_price)
            val dataDescription = resources.getStringArray(R.array.data_beverage_desc)
            val dataPhoto = resources.obtainTypedArray(R.array.data_beverage_photo)
            val listBevMenu = ArrayList<Menu>()
            for (i in dataName.indices) {
                val bev = Menu(dataName[i],dataPrice[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
                listBevMenu.add(bev)
            }
            return listBevMenu
        }

    private val listPackageMenu: ArrayList<Menu>
        get() {
            val dataName = resources.getStringArray(R.array.data_pack_name)
            val dataPrice = resources.getStringArray(R.array.data_pack_price)
            val dataDescription = resources.getStringArray(R.array.data_pack_desc)
            val dataPhoto = resources.obtainTypedArray(R.array.data_pack_photo)
            val listPackMenu = ArrayList<Menu>()
            for (i in dataName.indices) {
                val food = Menu(dataName[i],dataPrice[i], dataDescription[i], dataPhoto.getResourceId(i, -1))
                listPackMenu.add(food)
            }
            return listPackMenu
        }
}