package com.granda.warframekotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ScrollView
import android.widget.TextView
import com.granda.warframekotlin.rest.DataRest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // este componenete va a consumir  https://api.warframestat.us/weapons/search/ act
        // se va a encargar de recibir un dato en el cual lo va  aconsulta en la api consumida
        var buscarArma = findViewById<View>(R.id.buscarArma) as EditText
        // boton que permitirá buscar el dato escrito en el campo de texto
        var btnTest = findViewById<View>(R.id.btnTest) as Button
        //un campo donde se visualizará toda la información acerca del arma escrita
        var infoArma = findViewById<View>(R.id.infoArma) as TextView
        btnTest.setOnClickListener{
            val dt= DataRest()
            val data = dt.getWarframe(buscarArma.text.toString())
            println(data)

        }
    }



}
