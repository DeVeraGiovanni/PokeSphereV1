package com.example.pokesphere.scr


import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokesphere.R
import com.example.pokesphere.model.Pokemon
import com.example.pokesphere.model.PokemonAdapter
import com.example.pokesphere.services.PokemonApiServices
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class HomePage : AppCompatActivity() {

    private lateinit var rv: RecyclerView
    private lateinit var adapter: PokemonAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_homepage)

        rv = findViewById(R.id.rv)
        rv.layoutManager = GridLayoutManager(this, 2)
        getPokemonData()
    }


    private fun getPokemonData() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val pokemonApiService = retrofit.create(PokemonApiServices::class.java)
        val job = CoroutineScope(Dispatchers.Main).launch {
            try {
                val pokemonList = mutableListOf<Pokemon>()
                for (pokemonId in 1..50) {
                    val pokemon = pokemonApiService.getPokemon(pokemonId)
                    pokemonList.add(pokemon)
                }

                adapter = PokemonAdapter(pokemonList) { selectedPokemon ->

                    val intent = Intent(this@HomePage, PokemonDetailsPage::class.java)
                    intent.putExtra("pokemon", selectedPokemon)
                    startActivity(intent)
                    }
                rv.adapter = adapter

            } catch (e: Exception) {
                Log.e("PokemonError", "There is a problem in fetching pokemon details", e)
            }}

        lifecycle.addObserver(object : DefaultLifecycleObserver {
            override fun onDestroy(owner: LifecycleOwner) {
                job.cancel()
            }
        })
    }
}

