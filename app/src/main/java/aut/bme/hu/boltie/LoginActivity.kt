package aut.bme.hu.boltie

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        // TODO: Add user on successful login
    }

    override fun onDestroy() {
        super.onDestroy()
        // TODO: Remove current user
    }

    fun loginOnClickListener(v: android.view.View) {

    }


}