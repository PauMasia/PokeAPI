package com.example.desde0_2

import android.net.Uri
import android.util.Log
import org.json.JSONObject
import java.io.IOException
import java.net.URL
import kotlin.math.log
import kotlin.reflect.typeOf


class PokeApi {

    fun getPokemon(): ArrayList<Pokemon> {
        var arrayPokemon: ArrayList<Pokemon> = ArrayList()
        for (i in 1 .. 134){
            val BASE_URL: String = "https://pokeapi.co/api/v2/pokemon/"+i +"/"// URL comú a totes les crides.
                 // que s'ha d'agregar a les crides.
            val builtUri: Uri = Uri.parse(BASE_URL) // Creem l'objecte Uri
                .buildUpon() // agreguem ?api_key=<API_KEY>
                .build() // construïm l'objecte}

            val item: String = builtUri.toString()
            var response= doCall(item)
            if (response != null) {
                arrayPokemon.add(response)
            }
        }
        return arrayPokemon
    }
    // que s'ha d'agregar a les crides.
    private fun doCall(url: String): Pokemon? {
        try {
            val jsonFin:String = HttpUtils.get(url).toString()
            return processJson(jsonFin)
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }
    fun processJson(jsonResponse: String): Pokemon? {
        try {
            val data =JSONObject(jsonResponse)

                val pokemonActObj=Pokemon(
                    data.getString("name"),
                    "a",
                    "tiposPokemon[1]","Skill"
                )
            return pokemonActObj
        }catch (a : Exception){
            a.printStackTrace()
        }
        return null
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