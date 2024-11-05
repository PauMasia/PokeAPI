package com.example.desde0_2

import android.view.ViewDebug.IntToString

data class Pokemon (
    var name:String,
    var tipo1:String,
    var tipo2:String?,
    var habilidad:String,
) {
    override fun toString(): String {
        var res:String=""
        if (tipo2!=null){
            res= "El pokemon {$name}, es de tipo {$tipo1} y {$tipo2}"
        }else if (tipo2==null){
            res= "El pokemon {$name}, es de tipo {$tipo1}"
        }
        return res
    }
}