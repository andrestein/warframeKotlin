package com.granda.warframekotlin.rest

import com.granda.warframekotlin.util.Config
import com.loopj.android.http.AsyncHttpClient
import com.loopj.android.http.JsonHttpResponseHandler
import cz.msebera.android.httpclient.Header
import org.json.JSONArray

class DataRest {
    fun getWarframe(path: String): String {
        val client= AsyncHttpClient()
        var data = ""
        client.get(Config.warframe+path,object:JsonHttpResponseHandler(){
            override fun onStart() {
                // called before request is started
                println("Empieza")
            }

            override fun onSuccess(statusCode: Int, headers: Array<Header>, response: JSONArray) {
                // called when response HTTP status is "200 OK"
                data = response.toString()
            }
        })
        return data
    }

}