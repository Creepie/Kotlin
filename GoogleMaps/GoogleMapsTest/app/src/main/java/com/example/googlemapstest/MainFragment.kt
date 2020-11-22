package com.example.googlemapstest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.model.LatLng
import java.nio.channels.Selector

@Suppress("DEPRECATION")
class MainFragment : Fragment(){

    private val model: SharedView by activityViewModels()
    private lateinit var locationList: ArrayList<Location>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        view.findViewById<Button>(R.id.bT_stefan).setOnClickListener{
            model.addLocation(Location((MapsSettings.home_stefan), MapsSettings.home_stefan_title))
        }
        view.findViewById<Button>(R.id.bT_mario).setOnClickListener{
            model.addLocation(Location(MapsSettings.home_mario, MapsSettings.home_mario_title))
        }
        view.findViewById<Button>(R.id.bT_selin).setOnClickListener{
            model.addLocation(Location(MapsSettings.home_selin, MapsSettings.home_selin_title))
        }

        view.findViewById<Button>(R.id.bT_all).setOnClickListener {
            locationList = ArrayList()
            locationList.add(Location(MapsSettings.home_stefan, MapsSettings.home_stefan_title))
            locationList.add(Location(MapsSettings.home_mario, MapsSettings.home_mario_title))
            locationList.add(Location(MapsSettings.home_selin, MapsSettings.home_selin_title))

            model.addLocationList(locationList)

        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_main, container, false)

        // Inflate the layout for this fragment
        return view
    }






}


