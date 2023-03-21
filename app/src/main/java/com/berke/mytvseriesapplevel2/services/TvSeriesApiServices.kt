package com.berke.mytvseriesapplevel2.services

import com.berke.mytvseriesapplevel2.models.tvSeriesModels.TvSeriesModels
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TvSeriesApiServices {

    private val BASE_URL = "https://api.tvmaze.com/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(Api::class.java)

    fun getTvSeriesData(idTvSeries: Int) : Single<TvSeriesModels>{
        return api.getTvSeries(idTvSeries)
    }

}