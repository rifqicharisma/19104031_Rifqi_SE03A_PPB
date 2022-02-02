package com.hasna_19104045.modul6

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        supportActionBar?.title = myData?.name.toString()
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    companion object {
        const val EXTRA_MYDATA = "extra_mydata"
    }
    val myData by getParcelableExtra<MyData>(DetailActivity.EXTRA_MYDATA)
    inline fun <reified T : Parcelable> Activity.getParcelableExtra(key: String) = lazy {
        intent.getParcelableExtra<T>(key)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        var lat:Double = myData!!.lat
        var lng:Double = myData!!.lang
        val location = LatLng(lat, lng)
        mMap.addMarker(MarkerOptions().position(location).title(myData!!.name.toString()))
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 16.0f))
    }
}