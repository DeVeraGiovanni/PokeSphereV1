package com.example.pokesphere.scr

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.pokesphere.R
import com.example.pokesphere.model.Pokemon
import com.squareup.picasso.Picasso

class PokemonDetailsPage : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pokemon_details)

        val specificPokemon = intent.getSerializableExtra("pokemon") as? Pokemon
        val ivPokemon: ImageView = findViewById(R.id.ivPokemon)
        val tvPokemonName: TextView = findViewById(R.id.tvPokemonName)
        val tvPokemonWeight: TextView = findViewById(R.id.tvPokemonWeight)
        val tvPokemonHeight: TextView = findViewById(R.id.tvPokemonHeight)
        val tvPokemonType: TextView = findViewById(R.id.tvPokemonType)
        val tvPokemonType1: TextView = findViewById(R.id.tvPokemonType1)

        if (specificPokemon != null) {
            tvPokemonWeight.text = "${specificPokemon.weight} Hectograms"
            tvPokemonHeight.text = "${specificPokemon.height} Decimetres"
            tvPokemonName.text = specificPokemon.name.replaceFirstChar { it.uppercase() }
            Picasso.get()
                .load(specificPokemon.sprites.front_default)
                .into(ivPokemon)

            if (specificPokemon.types.isNotEmpty()) {
                val type1 = specificPokemon.types[0].type.name
                tvPokemonType.text = type1.replaceFirstChar { it.uppercase() }

                if (specificPokemon.types.size > 1) {
                    val type2 = specificPokemon.types[1].type.name
                    tvPokemonType1.text = type2.replaceFirstChar { it.uppercase() }
                } else {
                    tvPokemonType1.text = ""
                }
            }
        }
        }

    }
