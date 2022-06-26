package com.zaniva.restoapp.ui.payment

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zaniva.restoapp.R
import com.zaniva.restoapp.databinding.ActivityPaymentBinding
import com.zaniva.restoapp.dataclass.TrayItem
import com.zaniva.restoapp.ui.chef.ChefActivity
import com.zaniva.restoapp.ui.dashboard.BevTrayAdapter
import com.zaniva.restoapp.ui.dashboard.FoodTrayAdapter
import com.zaniva.restoapp.ui.dashboard.PackTrayAdapter

class PaymentActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPaymentBinding
    private lateinit var adapter: FoodTrayAdapter
    private lateinit var adapter2: BevTrayAdapter
    private lateinit var adapter3: PackTrayAdapter
    private val trayFood = ArrayList<TrayItem>()
    private val trayBev = ArrayList<TrayItem>()
    private val trayPack = ArrayList<TrayItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Confirm Payment"

        val price = intent.getStringExtra("Price")
        Log.d("Harga", price.toString())

        if (trayFood.isEmpty()){
            trayFood.addAll(trayFoodMenu)
            adapter = FoodTrayAdapter(trayFood)
            adapter.hideBtn(true)
        }

        if (trayBev.isEmpty()){
            trayBev.addAll(trayBeveragesMenu)
            adapter2 = BevTrayAdapter(trayBev)
            adapter2.hideBtn(true)
        }

        if (trayPack.isEmpty()){
            trayPack.addAll(trayPackageMenu)
            adapter3 = PackTrayAdapter(trayPack)
            adapter3.hideBtn(true)
        }

        binding.apply {

            rvMakanan.setHasFixedSize(true)
            rvMakanan.layoutManager = LinearLayoutManager(this@PaymentActivity, RecyclerView.VERTICAL, false)
            rvMakanan.adapter = adapter
            rvMinuman.setHasFixedSize(true)
            rvMinuman.layoutManager = LinearLayoutManager(this@PaymentActivity, RecyclerView.VERTICAL, false)
            rvMinuman.adapter = adapter2
            rvPaket.setHasFixedSize(true)
            rvPaket.layoutManager = LinearLayoutManager(this@PaymentActivity, RecyclerView.VERTICAL, false)
            rvPaket.adapter = adapter3
            tvBill.text = price
            tvEmoney.setOnClickListener {
                showCustomDialog("E-money")
            }
            tvCash.setOnClickListener {
                showCustomDialog("Cash")
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
    private fun showCustomDialog(state: String){
        if (state == "E-money" || state == "Cash"){
            val dialog = Dialog(this)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
            dialog.setCancelable(true)
            dialog.setContentView(R.layout.pop_up_pay)
            val btnOk: View = dialog.findViewById(R.id.container_ok)
            btnOk.setOnClickListener {
                dialog.dismiss()
            }
            dialog.setOnDismissListener {
                dialog.dismiss()
                Intent(this, ChefActivity::class.java).also {
                    startActivity(it)
                }
            }
            dialog.show()
        }
    }

}