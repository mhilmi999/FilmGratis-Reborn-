package com.hilmi.filmgratis.screen.PopularMovie

import com.hilmi.filmgratis.BuildConfig.POSTER_URL
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.hilmi.filmgratis.R
import com.hilmi.filmgratis.model.Movie
import com.hilmi.filmgratis.screen.DetailMovie.detailmovieActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.movie_item.view.*

class AdapterRVMovie: RecyclerView.Adapter<ViewHolder>{

    var arrayList : ArrayList<Movie> = ArrayList()
    var context : Context? = null

    constructor(context: Context, arrayList: ArrayList<Movie>){
        this.arrayList=arrayList
        this.context=context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.movie_item, parent, false))
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val itemValue = arrayList.get(position)
        holder.txtTitle.text= itemValue.title
        Picasso.get().load("$POSTER_URL${itemValue.posterPath}").into(holder.imgMovie)

        holder.fullitem.setOnClickListener {
        val intent: Intent=Intent(context,detailmovieActivity::class.java)
        intent.putExtra("id",itemValue.id)
        context?.startActivity(intent)
        }
    }
}



class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
    val txtTitle = view.txtTitleMovie
    val imgMovie = view.imgMovie
    val fullitem=view.fullitem

}