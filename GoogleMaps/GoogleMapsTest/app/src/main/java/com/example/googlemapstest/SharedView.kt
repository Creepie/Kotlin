package com.example.googlemapstest

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.maps.model.LatLng

class SharedView: ViewModel() {

    val location = MutableLiveData<Location>()

    fun addLocation(location: Location){
        this.location.value = location
    }
}

data class Location (val location: LatLng, val title: String)