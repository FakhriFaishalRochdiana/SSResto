package com.zaniva.restoapp.ui.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.zaniva.restoapp.R
import com.zaniva.restoapp.databinding.ActivityDetailBinding
import com.zaniva.restoapp.dataclass.Menu

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Menu>("Data")
        Log.d("Detail Data", data?.name.toString())

        detail()
        setBtn()
    }

    private fun detail() {
        val data = intent.getParcelableExtra<Menu>("Data")
        binding.apply {
            ivItem.setImageResource(data!!.photo)
            tvTitle.text = data!!.name
            tvDesc.text = data!!.desc
            tvPrice.text = data!!.price
        }
    }

    private fun setBtn() {
        var count = 0
        binding.apply {
            tvAmount.text = count.toString()
            ivAdd.setOnClickListener {
                count += 1
                tvAmount.text = count.toString()
            }
            ivReduce.setOnClickListener {
                if (count>0){
                    count -= 1
                } else {
                    Toast.makeText(this@DetailActivity, "Jumlah item sudah nol", Toast.LENGTH_SHORT).show()
                }
                tvAmount.text = count.toString()
            }
            btAdd.setOnClickListener {
                finish()
            }
        }
    }
}