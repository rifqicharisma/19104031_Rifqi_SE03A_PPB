package com.rifqi_19104031.modul5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // get reference to button
        val btnProdi = findViewById(R.id.btnProdi) as Button
        val inputProdi = findViewById(R.id.inputProdi) as EditText

        btnProdi.setOnClickListener {
            val namaProdi = inputProdi.text.toString()
            if (namaProdi.isEmpty()) {
                inputProdi.error = "Prodi Tidak Boleh Kosong"
                return@setOnClickListener
            }
            val moveWithDataIntent = Intent(this@Practice5Activity, Practice5ReadDataActivity::class.java)
            moveWithDataIntent.putExtra(Practice5ReadDataActivity.EXTRA_PRODI, namaProdi)
            startActivity(moveWithDataIntent)
        }

    }
}