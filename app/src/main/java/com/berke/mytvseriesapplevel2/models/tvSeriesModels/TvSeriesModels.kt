package com.berke.mytvseriesapplevel2.models.tvSeriesModels


data class TvSeriesModels(

    val id: Int,
    val name: String,
    val type: String,
    val language: String,
    val genres: List<String>,
    val status: String,
    val runtime: Int,
    val premiered: String,
    val ended: String,
    val schedule: Schedule,
    val rating: Rating,
    val image: İmageXX,
    val summary: String,
    val _embedded: Embedded,


)