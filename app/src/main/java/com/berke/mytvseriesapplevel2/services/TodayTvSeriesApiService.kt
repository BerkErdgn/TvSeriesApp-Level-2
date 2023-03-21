package com.berke.mytvseriesapplevel2.services

import com.berke.mytvseriesapplevel2.models.todayModels.TodaysTvSeriesModels
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TodayTvSeriesApiService {

    private val BASE_URL = "https://api.tvmaze.com/schedule/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(Api::class.java)

    fun getTodayData(date : String): Single<TodaysTvSeriesModels>{
        return api.getTodayTvShows(date)
    }

}