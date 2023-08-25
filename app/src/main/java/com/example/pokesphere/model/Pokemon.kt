package com.example.pokesphere.model

import com.example.pokesphere.model.pokemon_type.PokemonType
import com.example.pokesphere.model.sprites.PokemonSprites
import java.io.Serializable

data class Pokemon(
    val id: Int,
    val name: String,
    val weight: String,
    val height: String,
    val sprites: PokemonSprites,
    val types: List<PokemonType>
) : Serializable




