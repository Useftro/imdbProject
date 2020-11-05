package com.example.imdbproject.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import com.example.imdbproject.MainActivity
import com.example.imdbproject.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }

    fun login(view: View){

        // take all data from edits, check if account exists then start MainActivity with 4 bars if exists and
        // start MainActivity with 3 bars if not exists
        //

        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
        finish()
    }

    fun signUp(view: View){
        startActivity(Intent(this@LoginActivity, SignUpActivity::class.java))
        finish()
    }
}