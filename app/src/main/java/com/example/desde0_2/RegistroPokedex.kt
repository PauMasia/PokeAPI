package com.example.desde0_2

import android.graphics.Movie
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.Html
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.example.desde0_2.databinding.RegistroPokedexBinding


class RegistroPokedex: Fragment() {

        private var _binding: RegistroPokedexBinding? = null

        // This property is only valid between onCreateView and
        // onDestroyView.
        private val binding get() = _binding!!

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View {

            _binding = RegistroPokedexBinding.inflate(inflater, container, false)
            return binding.root

        }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = arguments

        if (args != null) {
            val pokemon = args.getParcelable("item") as Pokemon?

            if (pokemon != null) {
                updateUi(pokemon)
            }
        }
    }

    private fun updateUi(pokemon: Pokemon) {
        Log.d("PokeGrowr", pokemon.toString())

        binding.pName.setText(pokemon.name)
        binding.pDescrip.setText("Pokemon description")
        Glide.with(requireContext()).load(
             pokemon.imagen
        ).into(binding.pokeFoto)
    }

        override fun onDestroyView() {
            super.onDestroyView()
            _binding = null
        }
    }