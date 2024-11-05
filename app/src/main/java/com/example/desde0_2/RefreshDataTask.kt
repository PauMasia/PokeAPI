package com.example.desde0_2.com.example.desde0_2

import android.os.AsyncTask
import android.util.Log
import com.example.desde0_2.PokeApi
import com.example.desde0_2.Pokemon

class RefreshDataTask : AsyncTask<Unit, Unit, Unit>() {

    override fun doInBackground(vararg p0: Unit?) {
        var api: PokeApi = PokeApi()
        var pokemons: ArrayList<Pokemon>? = api.getPokemon()

        Log.d("DataRefresh", pokemons.toString())
    }
}