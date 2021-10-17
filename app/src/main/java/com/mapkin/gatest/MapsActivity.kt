package com.mapkin.gatest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class MapsActivity : AppCompatActivity() {
    private var lat: String? = null
    private var lng: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)


        if(intent != null){
            lat = intent.getStringExtra("Lat")
            lng = intent.getStringExtra("Lng")

            val add = intent.getStringExtra("add")

            val bundle = Bundle()
            bundle.putString("lat", lat)
            bundle.putString("lng", lng)
            bundle.putString("add", add)

            /*Toast.makeText(this, lat, Toast.LENGTH_SHORT).show()
            Toast.makeText(this, lng, Toast.LENGTH_SHORT).show()*/

            val fragment = MapsFragment()

            fragment.arguments = bundle


            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, fragment)
                .commit()

        }


    }
}