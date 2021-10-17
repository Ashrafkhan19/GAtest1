package com.mapkin.gatest

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    lateinit var imgLogo: ImageView
    lateinit var etPhoneEmail: EditText
    lateinit var etPassword: EditText
    lateinit var btnLogin: Button
    lateinit var sharedPreferences: SharedPreferences

    val email = "12345"
    val validpass = "5426"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedPreferences =
            getSharedPreferences(getString(R.string.save_prefrence_name), Context.MODE_PRIVATE)
        val isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false)

        setContentView(R.layout.activity_login)

        if (isLoggedIn) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        imgLogo = findViewById(R.id.imgLogo)
        etPhoneEmail = findViewById(R.id.etPhoneEmail)
        etPassword = findViewById(R.id.etPassword)
        btnLogin = findViewById(R.id.btnLogin)

        btnLogin.setOnClickListener {
            val phone = etPhoneEmail.text.toString()
            val pass = etPassword.text.toString()

            val intent = Intent(this, MainActivity::class.java)

            if (email == phone && pass == validpass) {

                intent.putExtra("phone", phone)
                intent.putExtra("pass", pass)
                sharedPreferences.edit().putBoolean("isLoggedIn", true).apply()
                startActivity(intent)
                finish()
            } else {
                Toast.makeText(this, "Galat phone or password", Toast.LENGTH_SHORT).show()
            }
        }


    }
}