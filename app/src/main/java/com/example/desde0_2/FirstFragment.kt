package com.example.desde0_2

import android.R
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.navigation.fragment.findNavController
import com.example.desde0_2.databinding.FragmentFirstBinding
import java.util.concurrent.Executor
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    lateinit var layView: ListView
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        layView=binding.listViewTodo
        var testTxt: ArrayList<Pokemon> = ArrayList()

        try {
            var adapter2: ArrayAdapter<Pokemon> = ArrayAdapter<Pokemon>(
                requireContext(),
                R.layout.simple_list_item_1,
                R.id.text1,
                testTxt
            )
            layView.adapter=adapter2
               refresh(adapter2);
        }catch(e:Exception){
            e.printStackTrace()
        }

    }
    fun refresh(arrAdapter:ArrayAdapter<Pokemon>){
        var executor:ExecutorService= Executors.newSingleThreadExecutor();
        var handler:Handler=Handler(Looper.getMainLooper())
        executor.execute(){
            var api: PokeApi= PokeApi()
            var aList: ArrayList<Pokemon>? = api.getPokemon()
            handler.post {
                aList?.forEach {
                    arrAdapter.add(it)
                }
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

