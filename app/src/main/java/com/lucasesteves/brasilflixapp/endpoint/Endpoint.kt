package com.lucasesteves.brasilflixapp.endpoint

import com.lucasesteves.brasilflixapp.model.Filmes
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {
    @GET("")
    fun getFilmes() : Call<List<Filmes>>
}