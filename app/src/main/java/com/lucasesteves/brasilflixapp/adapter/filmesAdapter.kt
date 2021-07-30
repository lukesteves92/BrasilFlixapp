package com.lucasesteves.brasilflixapp.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.lucasesteves.brasilflixapp.R
import com.lucasesteves.brasilflixapp.databinding.FilmesBinding
import com.lucasesteves.brasilflixapp.model.Filmes

class filmesAdapter (
    private val filmesList: List<Filmes>,
) : RecyclerView.Adapter<filmesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = FilmesBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(filmesList[position])
    }

    override fun getItemCount() = filmesList.size

    class ViewHolder(
        val binding: FilmesBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(
            Filmes: Filmes,
        ) {
            binding.fotoFilme.setImageResource(R.drawable.films)
            binding.filmeName.text = Filmes.title
            binding.dataLancamento.text = Filmes.release_date


        }
    }
}
