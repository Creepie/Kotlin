package com.example.googlemapstest

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions


class MapsFragment  : Fragment()  {
    private val model: SharedView by activityViewModels()

    private val callback = OnMapReadyCallback { googleMap ->
        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         */

        googleMap!!.clear()
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

        model.location.observe(viewLifecycleOwner, { o ->

            var data: Location = o as Location
            MapsSettings.location = data.location
            MapsSettings.title = data.title

            val callback = OnMapReadyCallback { googleMap ->

                googleMap!!.clear()

                googleMap!!.addMarker(MarkerOptions().position(MapsSettings.location).title(MapsSettings.title))
                googleMap!!.moveCamera(CameraUpdateFactory.newLatLng(MapsSettings.location))
                googleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(MapsSettings.location, MapsSettings.zoom))
            }
            mapFragment?.getMapAsync(callback)
        })

        model.locationList.observe(viewLifecycleOwner, { o ->
            var data: ArrayList<Location> = o as ArrayList<Location>
            val callback = OnMapReadyCallback { googleMap ->

                googleMap!!.clear()

                for (data in data) {
                    MapsSettings.location = data.location
                    MapsSettings.title = data.title
                    googleMap!!.addMarker(MarkerOptions().position(MapsSettings.location).title(MapsSettings.title))
                }


                googleMap!!.moveCamera(CameraUpdateFactory.newLatLng(MapsSettings.location))
                googleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(MapsSettings.location, 10f))
            }
            mapFragment?.getMapAsync(callback)
        })


    }


}
