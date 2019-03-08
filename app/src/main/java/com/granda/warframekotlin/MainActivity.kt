package com.granda.warframekotlin

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.granda.warframekotlin.util.Config
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray
import org.json.JSONObject


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
            var client= AsyncHttpClient()
            /**
             * WarframeItem
             */
            client.get(Config.warframe+generatePaths(buscarArma.text.toString(),0),object: JsonHttpResponseHandler(){
                override fun onStart() {
                    // called before request is started
                    //println("Start Request")
                }
                override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONArray) {
                    // called when response HTTP status is "200 OK"
                    val itemData = response.toString()
                    if(itemData != "") {
                        var showData = JSONObject()
                        val arrayJson = JSONArray(itemData)
                        for(i in 0 until arrayJson.length() step 1){
                            if(arrayJson.getJSONObject(i).getString("name").toLowerCase() == buscarArma.text.toString().toLowerCase()){
                                showData = arrayJson.getJSONObject(i).
                            }
                        }
                        infoArma.text = showData.toString()
                    }
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?,
                    throwable: Throwable?
                ) {
                    val toast = Toast.makeText(
                        applicationContext,
                        "El item que buscas no se encuetra",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            })
            /**
             * MarketItem
             */
            client= AsyncHttpClient()
            client.get(Config.warframe+generatePaths(buscarArma.text.toString(),1),object: JsonHttpResponseHandler(){
                override fun onStart() {
                    // called before request is started
                    //println("Start Request")
                }
                override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONArray) {
                    // called when response HTTP status is "200 OK"
                    val itemData = response.toString()
                    /**
                    if(itemData != "") {
                        var showData = JSONObject()
                        val arrayJson = JSONArray(itemData)
                        for(i in 0 until arrayJson.length() step 1){
                            if(arrayJson.getJSONObject(i).getString("name").toLowerCase() == buscarArma.text.toString().toLowerCase()){
                                showData = arrayJson.getJSONObject(i)
                            }
                        }
                        infoArma.text = showData.toString()
                    }
                    **/
                }

                override fun onFailure(
                    statusCode: Int,
                    headers: Array<out Header>?,
                    responseString: String?,
                    throwable: Throwable?
                ) {
                    val toast = Toast.makeText(
                        applicationContext,
                        "El item que buscas no se encuetra",
                        Toast.LENGTH_SHORT
                    )
                    toast.show()
                }
            })

        }
    }
    private fun generatePaths(path:String,type: Int): String{
        if(type == 0){
            val starPath = "weapons/search/"
            val tem = path.toLowerCase()
            val fullPath = starPath+tem
            return fullPath.replace(" ","%20")
        }
        if(type == 1){
            val starPath = "items/"
            val tem = path.toLowerCase()
            var fullPath=""
            println()
            if(tem.split("prime").size > 1){
                fullPath = starPath+tem+"_set/orders"
            }else{
                fullPath= starPath+tem+"_prime_set/orders"
            }
            return fullPath.replace(" ","_")
        }
        return ""
    }
}
