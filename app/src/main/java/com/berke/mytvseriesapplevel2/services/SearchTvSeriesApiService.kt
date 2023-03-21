package com.berke.mytvseriesapplevel2.services

import com.berke.mytvseriesapplevel2.models.searchModels.SearchTvSeriesModels
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class SearchTvSeriesApiService {

    private val BASE_URL ="https://api.tvmaze.com/search/"
    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(Api::class.java)

    fun getSearchData(q: String): Single<SearchTvSeriesModels>{
        return  api.getSearchTvSeries(q)
    }

}