package com.hilmi.filmgratis.screen.DetailMovie

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hilmi.filmgratis.BuildConfig.POSTER_URL
import com.hilmi.filmgratis.R
import com.hilmi.filmgratis.model.Movie
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.similarmovie_item.view.*

class AdapterRVSimillar(val context: Context?, val arrayList: ArrayList<Movie>): RecyclerView.Adapter<ViewHolderSimillar>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):ViewHolderSimillar {
        return ViewHolderSimillar(LayoutInflater.from(context).inflate(R.layout.similarmovie_item, parent, false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: ViewHolderSimillar, position: Int) {
        val itemValue=arrayList.get(position)
        Picasso.get().load("${POSTER_URL}${itemValue.posterPath}").into(holder.imgSimilarMovie)
        holder.fullItem.setOnClickListener {
            val intent: Intent = Intent(context,detailmovieActivity::class.java)
            intent.putExtra("id",itemValue.id)
            context?.startActivity(intent)
        }
    }

}

class ViewHolderSimillar(view: View): RecyclerView.ViewHolder(view){
    val imgSimilarMovie = view.imgSimilarMovie
    val fullItem = view.fullitem
}