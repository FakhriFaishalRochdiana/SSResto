package com.zaniva.restoapp.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import androidx.recyclerview.widget.RecyclerView
import com.zaniva.restoapp.R
import com.zaniva.restoapp.databinding.FragmentHomeBinding
import com.zaniva.restoapp.dataclass.Menu
import com.zaniva.restoapp.ui.detail.DetailActivity
import com.zaniva.restoapp.ui.more.MoreActivity

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: Adapter
    private lateinit var adapter2: BevAdapter
    private lateinit var adapter3: PackAdapter
    private val listFood = ArrayList<Menu>()
    private val listBev = ArrayList<Menu>()
    private val listPack = ArrayList<Menu>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentHomeBinding.bind(view)

        if (listFood.isEmpty()){
            listFood.addAll(listFoodMenu)
            adapter = Adapter(listFood)
            adapter.setOnItemClickCallback(object : Adapter.OnItemClickCallback{
                override fun onItemClicked(data: Menu) {
                    Intent(requireContext(), DetailActivity::class.java).also {
                        it.putExtra("Data", data)
                        startActivity(it)
                    }
                }
            })
        }

        if (listBev.isEmpty()){
            listBev.addAll(listBeveragesMenu)
            adapter2 = BevAdapter(listBev)
            adapter2.setOnItemClickCallback(object : BevAdapter.OnItemClickCallback{
                override fun onItemClicked(data: Menu) {
                    Intent(requireContext(), DetailActivity::class.java).also {
                        it.putExtra("Data", data)
                        startActivity(it)
                    }
                }
            })
        }

        if (listPack.isEmpty()){
            listPack.addAll(listPackageMenu)
            adapter3 = PackAdapter(listPack)
            adapter3.setOnItemClickCallback(object : PackAdapter.OnItemClickCallback{
                override fun onItemClicked(data: Menu) {
                    Intent(requireContext(), DetailActivity::class.java).also {
                        it.putExtra("Data", data)
                        startActivity(it)
                    }
                }
            })
        }

        binding.apply {
            rvMakanan.setHasFixedSize(true)
            rvMakanan.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            rvMakanan.adapter = adapter
            rvMinuman.setHasFixedSize(true)
            rvMinuman.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            rvMinuman.adapter = adapter2
            rvPaket.setHasFixedSize(true)
            rvPaket.layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
            rvPaket.adapter = adapter3
            tvMakanan.setOnClickListener {
                Intent(requireContext(), MoreActivity::class.java).also {
                    it.putExtra("Type", "Makanan")
                    startActivity(it)
                }
            }
            tvMinuman.setOnClickListener {
                Intent(requireContext(), MoreActivity::class.java).also {
                    it.putExtra("Type", "Minuman")
                    startActivity(it)
                }
            }
            tvPaket.setOnClickListener {
                Intent(requireContext(), MoreActivity::class.java).also {
                    it.putExtra("Type", "Paket")
                    startActivity(it)
                }
            }
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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}