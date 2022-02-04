package com.rifqi_19104031.anterin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById(R.id.iv_produk1)
        button.setOnClickListener{
            val intent = Intent(this, DetailProduk::class.java)
            startActivity(intent)
        }

        val search_barBtn = findViewById(R.id.search_bar)
        search_barBtn.setOnClickListener{
            val intent = Intent(this, cari_toko::class.java)
            startActivity(intent)
        }
    }
}