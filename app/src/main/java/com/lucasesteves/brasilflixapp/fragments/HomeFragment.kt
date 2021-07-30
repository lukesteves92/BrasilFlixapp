package com.lucasesteves.brasilflixapp.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucasesteves.brasilflixapp.adapter.filmesAdapter
import com.lucasesteves.brasilflixapp.databinding.FragmentHomeBinding
import com.lucasesteves.brasilflixapp.endpoint.Endpoint
import com.lucasesteves.brasilflixapp.model.Filmes
import com.lucasesteves.brasilflixapp.util.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null
    private val retrofitClient = RetrofitInstance
        .getRetrofitInstance("")
    private val endpoint = retrofitClient.create(Endpoint::class.java)
    private val callback = endpoint.getFilmes()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        callback.enqueue(object : Callback<List<Filmes>> {
            override fun onFailure(call: Call<List<Filmes>>, t: Throwable) {
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()

            }

            override fun onResponse(call: Call<List<Filmes>>, response: Response<List<Filmes>>) {
                showData(response.body()!!)
            }
        })

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }

    private fun showData(filmesList: List<Filmes>) {
        filmesList.forEach {
            val filmeAdapter = filmesAdapter(filmesList)
            binding?.let {
                with(it) {
                    filmesRecyclerView.layoutManager = LinearLayoutManager(context)
                    filmesRecyclerView.adapter = filmeAdapter
                }
            }

        }

    }


}