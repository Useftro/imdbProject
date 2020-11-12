package com.example.imdbproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import com.example.imdbproject.api.ApiRequests
import com.example.imdbproject.data.fromJsonToKotlin.Result
import com.example.imdbproject.data.fromJsonToKotlin.serverResponse
import com.example.imdbproject.fragment.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import retrofit2.Retrofit
import retrofit2.awaitResponse
import retrofit2.converter.gson.GsonConverterFactory
import java.util.ArrayList

const val BASE_URL = "https://rickandmortyapi.com/api/"

class MainActivity : AppCompatActivity() {

    private var TAG = "MainActivity"
    private val listResponse = arrayListOf<serverResponse>()
    private val chars = mutableListOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val all_data: ArrayList<serverResponse> = getCurrentData()

        val homeFragment = ListFragment()

        val bund = bundleOf("data" to all_data)

        homeFragment.arguments = bund

        val favoriteFragment = FavoriteFragment()
        val profileFragment = ProfileFragment()
        val settingsFragment = SettinsFragment()

        makeCurrentFragment(homeFragment)

        bottom_navigation.setOnNavigationItemSelectedListener {
            when (it.itemId){
                R.id.ic_list -> makeCurrentFragment(homeFragment)
                R.id.ic_favorite -> makeCurrentFragment(favoriteFragment)
                R.id.ic_profile -> makeCurrentFragment(profileFragment)
                R.id.ic_settings -> makeCurrentFragment(settingsFragment)
            }
            true
        }
    }


    private fun getCurrentData(): ArrayList<serverResponse>{
        val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiRequests::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            // Request takes only 20 chars per request, there are 671 chars
            val response = api.getCharacters(1).awaitResponse()
            if(response.isSuccessful){
                val data = response.body()!!
                Log.d(TAG, data.results.toString())
                Log.d("PAGE", data.info.next)
                listResponse.add(data)
                for (char in data.results){
                    chars.add(char)
                }
            }

            withContext(Dispatchers.Main) {
                progressBar.visibility = View.GONE
            }
        }
        return listResponse
    }

    private fun makeCurrentFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fl_wrapper, fragment)
            commit()
        }

}