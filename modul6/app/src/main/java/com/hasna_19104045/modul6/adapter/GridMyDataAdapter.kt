package com.hasna_19104045.modul6.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.fido.fido2.api.common.RequestOptions
import com.hasna_19104045.modul6.MyData
import com.hasna_19104045.modul6.R

class GridMyDataAdapter(val listMyDatas: ArrayList<MyData>) :
    RecyclerView.Adapter<GridMyDataAdapter.GridViewHolder>() {
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): GridViewHolder {
        val view: View = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_grid, viewGroup, false)
        return GridViewHolder(view)
    }
    override fun onBindViewHolder(holder: GridViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(listMyDatas[position].photo)
            .apply(RequestOptions().override(350, 550))
            .into(holder.imgPhoto)
    }
    override fun getItemCount(): Int {
        return listMyDatas.size
    }
    inner class GridViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var imgPhoto: ImageView = itemView.findViewById(R.id.img_item_photo)
    }
}