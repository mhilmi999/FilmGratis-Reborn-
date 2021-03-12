package com.hilmi.filmgratis.screen.DetailMovie

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hilmi.filmgratis.R
import com.hilmi.filmgratis.model.Trailer
import com.hilmi.filmgratis.screen.DetailMovie.AdapterRVTrailer.MyViewHolder

class AdapterRVTrailer(mcontect: Context?, trailerList: List<Trailer>) :
    RecyclerView.Adapter<MyViewHolder>() {
    private val mContext: Context
    private val trailerList: List<Trailer>
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.trailer_card, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: MyViewHolder, i: Int) {
        viewHolder.title.text = trailerList[i].name
    }

    override fun getItemCount(): Int {
        return trailerList.size
    }

    inner class MyViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var title: TextView
        var thumbnail: ImageView

        init {
            title = view.findViewById<View>(R.id.title) as TextView
            thumbnail = view.findViewById<View>(R.id.thumbnail) as ImageView
            view.setOnClickListener { v ->
                val pos = adapterPosition
                if (pos != RecyclerView.NO_POSITION) {
                    val clickedDataItem = trailerList[pos]
                    val videoId = trailerList[pos].key
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:$videoId"))
                    intent.putExtra("VIDEO_ID", videoId)
                    mContext.startActivity(intent)
                    Toast.makeText(
                        v.context,
                        "You clicked" + clickedDataItem.name,
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    init {
        mContext = this.mContext
        this.trailerList = trailerList
    }
}