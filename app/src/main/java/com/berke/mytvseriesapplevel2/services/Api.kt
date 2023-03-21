package com.berke.mytvseriesapplevel2.services

import com.berke.mytvseriesapplevel2.models.allTvModels.AllTvSeriesModels
import com.berke.mytvseriesapplevel2.models.crewModels.CrewModel
import com.berke.mytvseriesapplevel2.models.episodesModels.EpisodesModel
import com.berke.mytvseriesapplevel2.models.oneEpisodesModels.OneEpisodesModel
import com.berke.mytvseriesapplevel2.models.peopleModels.PeopleModel
import com.berke.mytvseriesapplevel2.models.searchModels.SearchTvSeriesModels
import com.berke.mytvseriesapplevel2.models.todayModels.TodaysTvSeriesModels
import com.berke.mytvseriesapplevel2.models.tvSeriesModels.TvSeriesModels
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {

    //https://api.tvmaze.com/schedule/web?date=2023-01-30
    //https://api.tvmaze.com/shows
    //https://api.tvmaze.com/search/shows?q=girls
    //https://api.tvmaze.com/shows/1?embed=cast
    //https://api.tvmaze.com/seasons/1/episodes
    //https://api.tvmaze.com/shows/1/crew
    //https://api.tvmaze.com/people/1
    //https://api.tvmaze.com/episodes/51

    @GET ("web")
    fun getTodayTvShows(@Query("date") date : String): Single<TodaysTvSeriesModels>

    @GET("shows")
    fun  getAllTvSeries(): Single<AllTvSeriesModels>

    @GET("shows")
    fun getSearchTvSeries(@Query("q") q : String): Single<SearchTvSeriesModels>

    @GET("shows/{idTvSeries}?embed=cast")
    fun getTvSeries(@Path("idTvSeries")idTvSeries: Int ): Single<TvSeriesModels>

    @GET("{idTvSeries}/episodes")
    fun getEpisodes(@Path("idTvSeries")idTvSeries: Int): Single<EpisodesModel>

    @GET("{idTvSeries}/crew")
    fun getCrew(@Path("idTvSeries")idTvSeries: Int): Single<CrewModel>

    @GET("people/{idPeople}")
    fun getPeople(@Path("idPeople")idPeople: Int): Single<PeopleModel>

    @GET("episodes/{idEpisodes}")
    fun getOneEpisodes(@Path("idEpisodes")idEpisodes: Int): Single<OneEpisodesModel>

}