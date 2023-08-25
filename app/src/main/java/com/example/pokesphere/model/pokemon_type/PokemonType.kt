package com.example.pokesphere.model.pokemon_type


import com.example.pokesphere.model.pokemon_type.type.Type
import java.io.Serializable

data class PokemonType(
    val slot: Int,
    val type: Type
) : Serializable
