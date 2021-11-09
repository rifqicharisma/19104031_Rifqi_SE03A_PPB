package com.rifqi_19104031.modul5

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {

    // deklarasi atribut (id xml)
    private lateinit var inputProdi : EditText
    private lateinit var btnProdi : Button
    private lateinit var btnCallBrowser : Button
    private lateinit var btnCallCamera : Button
    private lateinit var inputPhoneNumber : EditText
    private lateinit var btnCallPhone : Button
    private lateinit var btnFragment : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        inputProdi = findViewById(R.id.inputProdi)
//        btnProdi = findViewById(R.id.btnProdi)
//
//        btnProdi.setOnClickListener {
//            /*
//            Move acitivity menggunakan bundle
//             */
//            val bundle = Bundle()
//            bundle.putString("data1", inputProdi.text.toString())
//
//            val intent = Intent(this, MainActivityReadData::class.java)
//            intent.putExtras(bundle)
//            startActivity(intent)

        // inisiasi find view
        inputProdi = findViewById(R.id.inputProdi)
        inputPhoneNumber = findViewById(R.id.inputPhoneNumber)

        btnProdi = findViewById(R.id.btnProdi)
        btnCallBrowser = findViewById(R.id.btnCallBrowser)
        btnCallCamera = findViewById(R.id.btnCallCamera)
        btnCallPhone = findViewById(R.id.btnCallPhone)
        btnFragment = findViewById(R.id.btnFragment)

        // menambahkan fungsi untuk button prodi
        btnProdi.setOnClickListener {
            val namaProdi = inputProdi.text.toString()
            if (namaProdi.isEmpty()) {
                inputProdi.error = "Prodi Tidak Boleh Kosong"
                return@setOnClickListener
            }
            val moveWithDataIntent = Intent(this@MainActivity, MainActivityReadData::class.java)
            moveWithDataIntent.putExtra(MainActivityReadData.EXTRA_PRODI, namaProdi)
            startActivity(moveWithDataIntent)
        }

        // membuka web ittp
        btnCallBrowser.setOnClickListener{
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://ittelkom-pwt.ac.id/")
            startActivity(intent)
        }

        // menjalankan kamera perangkat
        btnCallCamera.setOnClickListener{
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            startActivity(intent)
        }

        // menjalankan call phone perangkat
        btnCallPhone.setOnClickListener{
            val phoneNumber = inputPhoneNumber.getText()
            if (phoneNumber.isEmpty()) {
                inputPhoneNumber.error = "Nomor Telpon Tidak Boleh Kosong"
                return@setOnClickListener
            }
            val intent = Intent(Intent.ACTION_CALL)
            intent.data = Uri.parse("tel:" + phoneNumber)
            startActivity(intent)
        }

        // memanggil fungsi user permission
        setupPermissions()

        // menambahkan fungsi untuk tombol fragment
        btnFragment.setOnClickListener{
            val intent = Intent(this, Pratice5ForFragmentActivity::class.java)
            startActivity(intent)
        }
    }


    // menampilkan uer permission
    val CALL_REQUEST_CODE = 100
    @SuppressLint("MissingPermission")
    private fun setupPermissions() {
        val permission = ContextCompat.checkSelfPermission(this,
            Manifest.permission.CALL_PHONE)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this,
                arrayOf(Manifest.permission.CALL_PHONE),
                CALL_REQUEST_CODE)
        }
    }


}