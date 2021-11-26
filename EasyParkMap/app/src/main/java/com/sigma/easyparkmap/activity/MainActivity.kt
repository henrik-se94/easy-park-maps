package com.sigma.easyparkmap.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sigma.easyparkmap.R
import com.sigma.easyparkmap.adapter.CitiesAdapter
import com.sigma.easyparkmap.module.Cities
import com.sigma.easyparkmap.module.GetCities
import com.sigma.easyparkmap.retrofit.ApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity(), LocationListener {

    lateinit var recyclerview: RecyclerView
    lateinit var recyclerAdapter: CitiesAdapter

    private lateinit var locationManager: LocationManager
    private val locationPermissionCode = 2

    private val TAG = MainActivity::class.qualifiedName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerview = findViewById(R.id.cities_list)
        recyclerview.layoutManager = LinearLayoutManager(this)
        getLocation()

    }

    private fun getLocation() {
        locationManager = getSystemService(Context.LOCATION_SERVICE) as LocationManager
        if ((ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                locationPermissionCode
            )
        }
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 5f, this)
    }


    private fun getCities(location: Location) {
        val apiInterface = ApiInterface.create().getCitiesData()
        apiInterface.enqueue(object : Callback<GetCities> {
            override fun onResponse(call: Call<GetCities>?, response: Response<GetCities>?) {
                if (response?.body() != null) {
                    Log.d(TAG, "the response" + response.body().cities?.get(0)?.name.toString())
                    val citiesList: List<Cities>? = response?.body()?.cities

                    recyclerAdapter =
                        CitiesAdapter(citiesList as ArrayList<Cities>, location, this@MainActivity)
                    recyclerview.adapter = recyclerAdapter
                }
            }

            override fun onFailure(call: Call<GetCities>?, t: Throwable?) {
                Log.e(TAG, t.toString())
            }
        })
    }

    override fun onLocationChanged(location: Location) {
        Log.d(TAG, "${location.longitude}");
        getCities(location);
    }
}