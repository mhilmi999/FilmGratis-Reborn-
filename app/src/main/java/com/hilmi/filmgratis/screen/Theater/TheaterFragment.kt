package com.hilmi.filmgratis.screen.Theater


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.hilmi.filmgratis.R
import com.hilmi.filmgratis.model.Movie
import com.hilmi.filmgratis.network.APIClient
import com.hilmi.filmgratis.screen.Home.AdapterRVMovie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_popularmovie.*
import kotlinx.android.synthetic.main.fragment_theater.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class TheaterFragment : Fragment() {

    var page : Int=1
    var region:String="id"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_theater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getTheaterMovie(page,region)
    }


    fun getTheaterMovie(page:Int,region:String){
        APIClient.getMainService().getMovieNowPlaying(page,region)
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
        rvTheaterMovie.layoutManager = GridLayoutManager(context,3)
        rvTheaterMovie.adapter = AdapterRVMovie(context, arrayList)
    }
}
