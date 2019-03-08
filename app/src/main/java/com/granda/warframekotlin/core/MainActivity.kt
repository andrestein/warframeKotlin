package com.granda.warframekotlin.core

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.granda.warframekotlin.R
import com.granda.warframekotlin.rest.DataRest

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // este componenete va a consumir  https://api.warframestat.us/weapons/search/
        var buscarArma = findViewById<View>(R.id.buscarArma) as EditText
        var btnTest = findViewById<View>(R.id.btnTest) as Button
        btnTest.setOnClickListener{
            val dt= DataRest()
            val data = dt.getWarframe("weapons/search/ack%20&%20brunt")
            println(data)
        }
    }



}
