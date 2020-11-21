package com.example.googlemapstest

import com.google.android.gms.maps.model.LatLng

object MapsSettings {
    val home_stefan = LatLng(48.292470, 14.307867)
    val home_stefan_title = "Moes home"
    val home_mario = LatLng(48.213979, 14.200908)
    val home_mario_title = "Captain Kotlin"
    val home_selin = LatLng(48.205517, 14.145105)
    val home_selin_title = "Selins home"
    var location = LatLng(0.0,0.0)
    var title = ""
    var zoom = 15f
    val zoom_all = 5f
    val zoom_single = 15f


    // list of all possible locations
    val home_list =  listOf(home_stefan, home_mario, home_selin)
}