package com.mapkin.gatest

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment : Fragment() {

    private var lat: String? = null
    private var lng: String? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lat = arguments?.getString("lat")
        lng = arguments?.getString("lng")
        val add = arguments?.getString("add")

        val latLng = LatLng(lat!!.toDouble(), lng!!.toDouble())


        val callback = OnMapReadyCallback { googleMap ->
            /**
             * Manipulates the map once available.
             * This callback is triggered when the map is ready to be used.
             * This is where we can add markers or lines, add listeners or move the camera.
             * In this case, we just add a marker near Sydney, Australia.
             * If Google Play services is not installed on the device, the user will be prompted to
             * install it inside the SupportMapFragment. This method will only be triggered once the
             * user has installed Google Play services and returned to the app.
             */


            val sydney = LatLng(25.3181, 82.96)
            //Toast.makeText(context, "$add", Toast.LENGTH_SHORT).show()
            googleMap.uiSettings.isZoomControlsEnabled = true

            googleMap.addMarker(MarkerOptions().position(latLng).title(add))

            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 12f))

            /*googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Location"))
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
            googleMap.animateCamera(CameraUpdateFactory.zoomIn())*/

        }
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)


    }
}