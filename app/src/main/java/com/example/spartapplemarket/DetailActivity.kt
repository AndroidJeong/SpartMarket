package com.example.spartapplemarket

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.spartapplemarket.databinding.ActivityDetailBinding
import com.example.spartapplemarket.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var dataList = intent?.getParcelableExtra<Product>("Product")

        if (dataList != null) {
            binding.detailIvProduct.setImageResource(dataList.picture)
        }

    }
}

//data class Product(var picture: Int, var title: String, var location: String,  var price: Int) :