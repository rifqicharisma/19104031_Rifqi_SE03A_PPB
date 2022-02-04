package com.rifqi_19104031.anterin

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class cari_toko : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cari_toko)

        val button = findViewById(R.id.iv_fruit)
        button.setOnClickListener{
            val intent = Intent(this, promosi::class.java)
            startActivity(intent)
        }
    }
}