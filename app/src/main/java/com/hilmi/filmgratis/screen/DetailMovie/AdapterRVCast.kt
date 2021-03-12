package com.hilmi.filmgratis.screen.DetailMovie

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hilmi.filmgratis.BuildConfig.POSTER_URL
import com.hilmi.filmgratis.R
import com.hilmi.filmgratis.model.Cast
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cast_item.view.*

class AdapterRVCast(val context: Context?, val arrayList: ArrayList<Cast>): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(LayoutInflater.from(context).inflate(R.layout.cast_item, parent,false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemValue=arrayList.get(position)
        holder.txtNameCast.text=itemValue.name
        Picasso.get().load("${POSTER_URL}${itemValue.profilePath}").into(holder.imgCast)
        }

    }





class ViewHolder(view: View):RecyclerView.ViewHolder(view){
    val txtNameCast = view.txtNameCast
    val imgCast = view.imgCast
}