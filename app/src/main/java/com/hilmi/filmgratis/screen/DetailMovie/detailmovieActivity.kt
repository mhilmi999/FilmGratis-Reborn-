package com.hilmi.filmgratis.screen.DetailMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.hilmi.filmgratis.BuildConfig.POSTER_URL
import com.hilmi.filmgratis.R
import com.hilmi.filmgratis.model.*
import com.hilmi.filmgratis.network.APIClient
import com.squareup.picasso.Picasso
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detailmovie.*

class detailmovieActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detailmovie)

        val id=intent.getIntExtra("id",0)


        getDetailMovie(id)
        getCastMovie(id)
        getSimillarMovie(id)
        getTrailerMovie(id)
    }


    fun getDetailMovie(id:Int){
        APIClient.getMainService().getDetailMoive(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                updateUIDetailMovie(response)

            },
                {
                    error ->

            })
    }

    fun updateUIDetailMovie(movie: DetailMovieResponse){
        txtTitleMovie.text= movie.title
        txtLanguageMovie.text=movie.originalLanguage
        txtRating.text= movie.voteAverage.toString()
        txtOverviewMovie.text=movie.overview
        Picasso.get().load("$POSTER_URL${movie.posterPath}").into(imgDetailMovie)


    }

    fun getCastMovie(id:Int){
        APIClient.getMainService().getCastMovie(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                updateUICastMovie(response.cast as ArrayList<Cast>)
            },{error ->


            })
    }

    fun updateUICastMovie(arrayList: ArrayList<Cast>){
        rv_cast.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL,false)
        rv_cast.adapter = AdapterRVCast(this, arrayList)

    }


    fun getSimillarMovie(id: Int){
        APIClient.getMainService().getSimilarMovie(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({response ->
            updateUISimilarMovie(response.movies as ArrayList<Movie>)
            },{ error ->

            }
            )
    }

    fun updateUISimilarMovie(arrayList: ArrayList<Movie>){
        rv_simillar.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rv_simillar.adapter = AdapterRVSimillar(this, arrayList)
    }



    fun getTrailerMovie(id:Int){
        APIClient.getMainService().getDetailMoive(id)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ response ->
                updateUITrailerMovie(response.video as ArrayList<Trailer>)

            },
                {
                        error ->

                })
    }

    fun updateUITrailerMovie(arrayList: ArrayList<Trailer>){
        rv_trailer.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        rv_trailer.adapter = AdapterRVTrailer(this,  arrayList )
    }
}
