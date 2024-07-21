package com.example.calculatehelper.presentation.splash

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.telecom.TelecomManager
import android.telephony.TelephonyManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.calculatehelper.databinding.ActivitySplashBinding
import com.example.calculatehelper.presentation.main.MainActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*


class Splash : AppCompatActivity() {
    private lateinit var binding: ActivitySplashBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySplashBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        intentToApp()

    }



    @SuppressLint("HardwareIds")
    fun intentToApp() {
        lifecycleScope.launch(Dispatchers.Main) {
            delay(1500)




                startActivity(Intent(applicationContext, MainActivity::class.java))
                this@Splash.finish()
             /*   Toast.makeText(
                    this@Splash,
                    "You are not allowed to use this program.",
                    Toast.LENGTH_SHORT
                ).show()*/


            }


        }



}