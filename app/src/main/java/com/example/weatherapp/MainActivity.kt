package com.example.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonArrayRequest
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener{
            val name = countryName.text.toString()
            fetchData(name)
        }
    }


    private fun fetchData(name: String) {
        val url="https://api.openweathermap.org/data/2.5/weather?q=$name&appid=c77d90cc142f6bb5e1e8acad3b187636&&units=metric"
        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            Response.Listener {
                val tempJSONObject=it.getJSONObject("main").getString("temp")

                temp.text = tempJSONObject.toString()
            },
            Response.ErrorListener {
                Toast.makeText(this, "error", Toast.LENGTH_LONG).show()
            }
        )
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }
}