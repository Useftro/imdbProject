package com.example.imdbproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.imdbproject.api.ApiHelper
import com.example.imdbproject.api.RetrofitBuilder
import com.example.imdbproject.data.model.CharacterModel
import com.example.imdbproject.ui.ViewModelFactory
import com.example.imdbproject.ui.adapter.MainAdapter
import com.example.imdbproject.ui.viewModel.MainViewModel
import com.example.imdbproject.utils.Status
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var adapter: MainAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupViewModel()
        setupUI()
        setupObservers()

//        val homeFragment = ListFragment()
//        val favoriteFragment = FavoriteFragment()
//        val profileFragment = ProfileFragment()
//        val settingsFragment = SettinsFragment()
//
//        makeCurrentFragment(homeFragment)
//
//        bottom_navigation.setOnNavigationItemSelectedListener {
//            when (it.itemId){
//                R.id.ic_list -> makeCurrentFragment(homeFragment)
//                R.id.ic_favorite -> makeCurrentFragment(favoriteFragment)
//                R.id.ic_profile -> makeCurrentFragment(profileFragment)
//                R.id.ic_settings -> makeCurrentFragment(settingsFragment)
//            }
//            true
//        }
    }



    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(MainViewModel::class.java)
    }

    private fun setupUI() {
        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MainAdapter(arrayListOf())
        recyclerView.addItemDecoration(
            DividerItemDecoration(
                recyclerView.context,
                (recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        recyclerView.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.getCharacters().observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        resource.data?.let { characters -> retrieveList(characters) }
                    }
                    Status.ERROR -> {
                        recyclerView.visibility = View.VISIBLE
                        progressBar.visibility = View.GONE
                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        progressBar.visibility = View.VISIBLE
                        recyclerView.visibility = View.GONE
                    }
                }
            }
        })
    }

    private fun retrieveList(characters: List<CharacterModel>) {
        adapter.apply {
            addCharacters(characters)
            notifyDataSetChanged()
            Log.d("CHARS", characters.toString())
        }
    }



//    private fun makeCurrentFragment(fragment: Fragment) =
//        supportFragmentManager.beginTransaction().apply {
//            replace(R.id.fl_wrapper, fragment)
//            commit()
//        }

}