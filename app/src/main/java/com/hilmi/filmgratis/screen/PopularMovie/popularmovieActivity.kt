package com.hilmi.filmgratis.screen.PopularMovie

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.hilmi.filmgratis.R
import com.hilmi.filmgratis.model.Movie
import com.hilmi.filmgratis.network.APIClient
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_popularmovie.*

class popularmovieActivity : AppCompatActivity() {

    var page : Int=1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popularmovie)

        getPopularMovie(page)
    }


    fun getPopularMovie(page:Int){
        APIClient.getMainService().getPopularMovie(page)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                response ->
                setAdapter(response.movies as ArrayList<Movie>)
            },
                {
                    error ->
                }
            )
    }

    fun setAdapter(arrayList : ArrayList<Movie>){
        rvPopularMovie.layoutManager = GridLayoutManager(this,3)
        rvPopularMovie.adapter = AdapterRVMovie(this, arrayList)
    }
}
