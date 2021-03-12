package com.hilmi.filmgratis.screen.Home


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager

import com.hilmi.filmgratis.R
import com.hilmi.filmgratis.model.Movie
import com.hilmi.filmgratis.network.APIClient
import com.hilmi.filmgratis.screen.PopularMovie.AdapterRVMovie
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_popularmovie.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class HomeFragment : Fragment() {

    var page : Int=1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

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
        rvPopularMovie.layoutManager = GridLayoutManager(context,3)
        rvPopularMovie.adapter = AdapterRVMovie(context, arrayList)
    }

}
