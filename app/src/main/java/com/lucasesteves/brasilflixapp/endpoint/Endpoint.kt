package com.lucasesteves.brasilflixapp.endpoint

import com.lucasesteves.brasilflixapp.model.Filmes
import retrofit2.Call
import retrofit2.http.GET

interface Endpoint {
    @GET("top_rated?api_key=50486dab40ae7909623d0d610e5e2bd6&language=pt-BR&page=1")
    fun getFilmes() : Call<List<Filmes>>
}