package com.example.imdbproject.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.bumptech.glide.Glide
import com.example.imdbproject.R
import com.example.imdbproject.data.fromJsonToKotlin.Result
import kotlinx.android.synthetic.main.activity_chart_screen.*
import kotlinx.android.synthetic.main.activity_chart_screen.characterLocationTextView
import kotlinx.android.synthetic.main.activity_chart_screen.characterStatusTextView
import kotlinx.android.synthetic.main.recyclerview_charactercard.*

class ChartScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_chart_screen)

        val intent = intent
        val character: Result = intent.getParcelableExtra("data")!!

        characterNameTextView.text = character.name
        characterStatusTextView.text = character.status
        characterGenderTextView.text = character.gender
        characterSpecieTextView.text = character.species
        characterLocationTextView.text = character.location.name
        characterOriginTextView.text = character.origin.name
        Glide.with(this).load(character.image).into(imageView)
    }
}