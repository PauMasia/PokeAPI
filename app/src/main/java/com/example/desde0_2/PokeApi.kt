package com.example.desde0_2

import android.net.Uri
import android.util.Log
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import kotlin.math.log
import kotlin.reflect.typeOf


class PokeApi {
    var BASE_URL: String = "https://pokeapi.co/api/v2/pokemon" // URL comú a totes les crides.

    fun getPokemon(): ArrayList<Pokemon>? {// que s'ha d'agregar a les crides.
        var builtUri: Uri = Uri.parse(BASE_URL) // Creem l'objecte Uri
            .buildUpon() // agreguem ?api_key=<API_KEY>
            .build() // construïm l'objecte}

        var url: String = builtUri.toString()
        var response= doCall(url)
        return response
    }
    // que s'ha d'agregar a les crides.
    private fun doCall(url: String): ArrayList<Pokemon>? {
        try {
            val JsonResponse:String = HttpUtils.get(url).toString()
            return processJson(JsonResponse)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
    fun processJson(jsonResponse: String): ArrayList<Pokemon> {
        var lista : ArrayList<Pokemon> = ArrayList<Pokemon>()
        try {
            var data =JSONObject(jsonResponse)
            var res= data.getJSONArray("results")
            for (i in 0 .. res.length()){


                val pokemonActJson= res.getJSONObject(i)

                val urlSingle: String = pokemonActJson.getString("url")
                val datosPokemon: ArrayList<String>? = getPokemonUnico(urlSingle, pokemonActJson)

                Log.d("kuklux", datosPokemon.toString())

                val pokemonActObj=Pokemon(
                    pokemonActJson.getString("name"),
                    "Si",
                    "tiposPokemon[1]","Skill"

                )
                lista.add(pokemonActObj)
            }
            Log.e("kkk", lista.toString())

        }catch (a : Exception){
            a.printStackTrace()
        }
        return lista
    }

    fun getPokemonUnico(url: String, pokemonActJson: JSONObject) :ArrayList<String> {
        var builtUri: Uri = Uri.parse(url) // Creem l'objecte Uri
            .buildUpon() // agreguem ?api_key=<API_KEY>
            .build()

        val urlF: String= builtUri.toString()
        try {
            val jsonResponse:String = HttpUtils.get(urlF).toString()

            val data=JSONObject(jsonResponse)


        val qEE=pokemonActJson.getJSONArray("types")

        val tiposPokemon= ArrayList<String>()
        for (i in 0 until qEE.length()){
            val tipoObjeto=qEE.getJSONObject(i)
            val tipoInfo=tipoObjeto.getJSONObject("type")
            val tipoFin= tipoInfo.getString("name")

            tiposPokemon.add(tipoFin)

        }
            return tiposPokemon

        } catch (e: IOException) {
            e.printStackTrace()
        }
        return ArrayList()
    }
}