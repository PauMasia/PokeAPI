package com.example.desde0_2

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.findFragment
import androidx.navigation.NavHostController
import androidx.navigation.Navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.bumptech.glide.Glide

class LayoutPokemonAdapter(private val context: Context, var dataSource: ArrayList<Pokemon>) :
    BaseAdapter() {
    // cojo el layout par añadir las cosas luego
    private val inflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return dataSource.size
    }

    override fun getItem(p0: Int): Any {
        return dataSource[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        //Es el layou que he creado para luego sustituirlo
        val view: View = convertView ?: inflater.inflate(R.layout.pokemon_lista, parent, false)
        //El item que he clickado
        val pokemonItem: Pokemon = getItem(position) as Pokemon

        //Te coge como TextView el objeto del tro de view con el id pokemonDescripcion y el de abajo lo mismo pero con ImageView
        val pkName = view.findViewById<TextView>(R.id.pokemonDescripcion)
        val pkFoto = view.findViewById<ImageView>(R.id.imagePokemon)

        //el item pokemonName, es un textview recuerda
        pkName.text = pokemonItem.toString()

        //el glide carga en el imageview la imagen del item que has creado
        Glide.with(context).load(
            pokemonItem.imagen
        ).into(pkFoto)


        view.setOnClickListener{
            var pokemonPokedex= pokemonItem
            var bundle= Bundle()
            bundle.putParcelable("item", pokemonPokedex)
            NavHostFragment.findNavController(view.findFragment())
                .navigate(R.id.action_FirstFragment_to_registroPokedex, bundle)            };

        // Retornem la View replena per a mostrarla
        return view
    }

//
fun updateData(newData: ArrayList<Pokemon>) {
    //como que vacia y rellena la arrayList
    dataSource.clear()
    dataSource.addAll(newData)
    //hace aparecer los cmabios en la arraylist, exclusivamente del LayoutPokemonAdapter
    notifyDataSetChanged()
}

}