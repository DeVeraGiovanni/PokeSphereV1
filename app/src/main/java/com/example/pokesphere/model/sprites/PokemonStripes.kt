package com.example.pokesphere.model.sprites

import java.io.Serializable

data class PokemonSprites(
    val front_default: String,
    val back_default: String,
    val front_shiny: String
) : Serializable