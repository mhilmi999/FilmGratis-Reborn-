package com.hilmi.filmgratis.network

import com.hilmi.filmgratis.BuildConfig.API_KEY
import com.hilmi.filmgratis.model.*
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface APIEndPoint {

    @GET("movie/popular?api_key=${API_KEY}")
    fun getPopularMovie(@Query("page")page:Int):Observable<PopularMovieResponse>

    @GET("movie/now_playing?api_key=${API_KEY}")
    fun getMovieNowPlaying(@Query("page")page:Int,
                           @Query("region")region: String):Observable<ListMovieResponse>

    @GET("movie/upcoming?api_key=${API_KEY}")
    fun getUpComingMovie(@Query("page")page:Int):Observable<UpComingMovieResponse>

    @GET("movie/{id}?api_key=${API_KEY}")
    fun getDetailMoive(@Path("id")id:Int): Single<DetailMovieResponse>

    @GET("movie/{id}/credits?api_key=${API_KEY}")
    fun getCastMovie(@Path("id")id:Int):Single<CastMovieResponse>

    @GET("movie/{id}/similar?api_key=${API_KEY}")
    fun getSimilarMovie(@Path("id")id:Int):Observable<ListMovieResponse>

    @GET("movie/{id}/videos?api_key=${API_KEY}")
    fun getTrailerMovie(@Path("id")id:Int,
                        @Query("video")video:Boolean):Observable<TrailerResponse>

}