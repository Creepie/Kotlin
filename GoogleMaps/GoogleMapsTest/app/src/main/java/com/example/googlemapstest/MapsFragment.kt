package com.example.googlemapstest

import androidx.fragment.app.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsFragment  : Fragment()  {
    private val model: SharedView by activityViewModels()

    private lateinit var location: LatLng
    private lateinit var title: String

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         */

        googleMap!!.addMarker(MarkerOptions().position(MapsSettings.location).title(MapsSettings.title))
        googleMap!!.moveCamera(CameraUpdateFactory.newLatLng(MapsSettings.location))
        googleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(MapsSettings.location, MapsSettings.zoom))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_maps, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        //Daten abfragen falls neu geklickt wurde und abändern
        model.location.observe(viewLifecycleOwner,{o->
            var data : Location = o as Location
                MapsSettings.location = data.location
                MapsSettings.title = data.title
                mapFragment?.getMapAsync(callback)
        })


}
}
