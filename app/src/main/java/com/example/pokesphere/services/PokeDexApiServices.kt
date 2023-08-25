package com.example.pokesphere.services


import com.example.pokesphere.model.Pokemon
import retrofit2.http.GET
import retrofit2.http.Path


interface PokemonApiServices {

    @GET("pokemon/{id}")
    suspend fun getPokemon(@Path("id") id: Int): Pokemon
}