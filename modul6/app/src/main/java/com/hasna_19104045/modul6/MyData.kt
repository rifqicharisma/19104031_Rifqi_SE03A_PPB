package com.hasna_19104045.modul6

import android.os.Parcelable

class MyData {
    @Parcelize
    data class MyData(
        var name: String,
        var description: String,
        var photo: String,
        val lat: Double,
        val lang: Double
    ) : Parcelable
}