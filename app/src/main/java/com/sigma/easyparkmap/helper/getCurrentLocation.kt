package com.sigma.easyparkmap.helper

import android.util.Log


class LocationHelper {

    fun distanceInKm(startLat: Double, startLong: Double, endLat: Double, endLong: Double): Double {
        val theta = startLong - endLong
        var dist =
            Math.sin(deg2rad(startLat)) * Math.sin(deg2rad(endLat)) + Math.cos(deg2rad(startLat)) * Math.cos(
                deg2rad(endLat)
            ) * Math.cos(deg2rad(theta))
        dist = Math.acos(dist)
        dist = rad2deg(dist)
        dist = dist * 60 * 1.1515
        dist = dist * 1.609344
        return dist
    }

    private fun deg2rad(deg: Double): Double {
        return deg * Math.PI / 180.0
    }

    private fun rad2deg(rad: Double): Double {
        return rad * 180.0 / Math.PI
    }


    fun getArraylist(pointer: String): List<String> {
        val lstValues: List<String> = pointer.split(",").map { it -> it.trim() }
        lstValues.forEach { it ->
            Log.i("Values", "value=$it")
        }

        return lstValues
    }
}