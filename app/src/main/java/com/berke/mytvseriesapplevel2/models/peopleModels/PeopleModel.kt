package com.berke.mytvseriesapplevel2.models.peopleModels

data class PeopleModel(
    val _links: Links,
    val birthday: String,
    val country: Country,
    val deathday: Any,
    val gender: String,
    val id: Int,
    val image: İmage,
    val name: String,
    val updated: Int,
    val url: String
)