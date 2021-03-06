package com.zaniva.restoapp.ui.dashboard

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.zaniva.restoapp.R
import com.zaniva.restoapp.databinding.FragmentDashboardBinding
import com.zaniva.restoapp.dataclass.TrayItem
import com.zaniva.restoapp.ui.payment.PaymentActivity

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null
    private lateinit var adapter: FoodTrayAdapter
    private lateinit var adapter2: BevTrayAdapter
    private lateinit var adapter3: PackTrayAdapter
    private val trayFood = ArrayList<TrayItem>()
    private val trayBev = ArrayList<TrayItem>()
    private val trayPack = ArrayList<TrayItem>()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        _binding = FragmentDashboardBinding.bind(view)

        if (trayFood.isEmpty()){
            trayFood.addAll(trayFoodMenu)
            adapter = FoodTrayAdapter(trayFood)
            adapter.hideBtn(false)
        }

        if (trayBev.isEmpty()){
            trayBev.addAll(trayBeveragesMenu)
            adapter2 = BevTrayAdapter(trayBev)
            adapter2.hideBtn(false)
        }

        if (trayPack.isEmpty()){
            trayPack.addAll(trayPackageMenu)
            adapter3 = PackTrayAdapter(trayPack)
            adapter3.hideBtn(false)
        }

        binding.apply {
            rvMakanan.setHasFixedSize(true)
            rvMakanan.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            rvMakanan.adapter = adapter
            rvMinuman.setHasFixedSize(true)
            rvMinuman.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            rvMinuman.adapter = adapter2
            rvPaket.setHasFixedSize(true)
            rvPaket.layoutManager = LinearLayoutManager(activity, RecyclerView.VERTICAL, false)
            rvPaket.adapter = adapter3
            tvTotal.text = "Rp. 280.000"
            tvCheckout.setOnClickListener {
                Intent(requireContext(), PaymentActivity::class.java).also {
                    it.putExtra("Price", tvTotal.text)
                    startActivity(it)
                }
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


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}