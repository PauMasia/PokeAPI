package com.example.desde0_2

import android.R
import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import org.json.JSONArray
import org.json.JSONObject
import java.io.IOException
import java.security.AccessController.getContext


class PokeApi {
    fun getPokemon(): ArrayList<Pokemon> {
        var arrayPokemon: ArrayList<Pokemon> = ArrayList()
        for (i in 1..13) {
            val BASE_URL: String =
                "https://pokeapi.co/api/v2/pokemon/" + i + "/"// URL comú a totes les crides.
            // que s'ha d'agregar a les crides.
            val builtUri: Uri = Uri.parse(BASE_URL) // Creem l'objecte Uri
                .buildUpon() // agreguem ?api_key=<API_KEY>
                .build() // construïm l'objecte}

            val item: String = builtUri.toString()
            var response = doCall(item)
            if (response != null) {
                arrayPokemon.add(response)
            }
        }
        return arrayPokemon
    }

    // que s'ha d'agregar a les crides.
    private fun doCall(url: String): Pokemon? {
        try {
            val jsonFin: String = HttpUtils.get(url).toString()
            return processJson(jsonFin)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    fun processJson(jsonResponse: String): Pokemon? {
        try {
            val data = JSONObject(jsonResponse)

            val arrayTipo = ArrayList<String>()
            val arrayTipos: JSONArray = data.getJSONArray("types")

            val urlImg = data.getJSONObject("sprites")
            val imgAct = urlImg.getString("front_default")

            for (i in 0..arrayTipos.length() - 1) {
                val tipoObj = arrayTipos.getJSONObject(i)
                val tipo = tipoObj.getJSONObject("type")

                arrayTipo.add(tipo.getString("name"))


            }
            val pokemonActObj: Pokemon = if (arrayTipo.size == 1) {
                Pokemon(
                    data.getString("name"),
                    arrayTipo[0],
                    null,
                    imgAct
                )
            } else {
                Pokemon(
                    data.getString("name"),
                    arrayTipo[0],
                    arrayTipo[1],
                    imgAct
                )
            }
            return pokemonActObj

        } catch (a: Exception) {

            Log.d("ttnomerayes", a.message.toString())
        }
        return null
    }
}