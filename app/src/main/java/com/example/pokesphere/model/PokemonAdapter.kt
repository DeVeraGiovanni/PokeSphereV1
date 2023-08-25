package com.example.pokesphere.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokesphere.R
import com.squareup.picasso.Picasso

class PokemonAdapter(private val pokemonList: List<Pokemon>,
                     private val onItemClick: (Pokemon) -> Unit) :
    RecyclerView.Adapter<PokemonAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.nameTextView)
        val tvID: TextView = itemView.findViewById(R.id.idTextView)
        val ivPokemon: ImageView = itemView.findViewById(R.id.pokemonImageView)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pokemon, parent, false)
        return ViewHolder(itemView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pokemon = pokemonList[position]

        holder.tvName.text = pokemon.name.replaceFirstChar { it.uppercase() }

        if (pokemon.id.toString().length < 2 ) {
            holder.tvID.text = "000${pokemon.id}"
        } else if (pokemon.id.toString().length < 3) {
            holder.tvID.text = "00${pokemon.id}"
        }
            Picasso.get().load(pokemon.sprites.front_default).into(holder.ivPokemon)
        holder.itemView.setOnClickListener {
            onItemClick(pokemon)
        }
    }

    override fun getItemCount(): Int {
        return pokemonList.size
    }
}