package com.sigma.easyparkmap.module

import com.google.gson.annotations.SerializedName

class GetCities {
    @SerializedName("status")
    var status: String? = null

    @SerializedName("message")
    var message: String? = null

    @SerializedName("cities")
    var cities: List<Cities>? = null
}

class Cities() {
    @SerializedName("name")
    var name: String? = null

    @SerializedName("lat")
    var lat: Double? = null

    @SerializedName("lon")
    var lon: Double? = null

    @SerializedName("points")
    var points: String? = null

}