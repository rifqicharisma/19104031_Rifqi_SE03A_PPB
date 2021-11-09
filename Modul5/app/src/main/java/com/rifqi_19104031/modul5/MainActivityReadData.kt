package com.rifqi_19104031.modul5

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivityReadData : AppCompatActivity(){

    private lateinit var lblProdiSaya : TextView

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_read_data)

        // inisiasi view
        lblProdiSaya = findViewById(R.id.lblProdiSaya)

//        if(intent.extras != null){
//            val bundle = intent.extras
//            lblProdiSaya.setText(bundle.getString("data1"))
//        }else{
//            lblProdiSaya.setText(intent.getStringExtra("data1"))
//        }
        val prodi = intent.getStringExtra(EXTRA_PRODI)
        lblProdiSaya.text = "Prodi Saya Adalah $prodi"
    }

    companion object {
        const val EXTRA_PRODI = "extra_prodi"
    }

}
