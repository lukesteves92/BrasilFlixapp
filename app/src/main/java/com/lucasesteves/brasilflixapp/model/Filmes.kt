package com.lucasesteves.brasilflixapp.model

import com.google.gson.annotations.SerializedName

data class Filmes(
    @SerializedName("release_date")
    var release_date : String,
    @SerializedName("title")
    var title : String
){}