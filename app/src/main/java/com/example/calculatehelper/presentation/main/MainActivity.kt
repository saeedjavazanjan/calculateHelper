package com.example.calculatehelper.presentation.main

import android.app.LocaleManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.LocaleList
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.PopupMenu
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import androidx.lifecycle.ViewModelProvider
import com.example.calculatehelper.R
import com.example.calculatehelper.databinding.ActivityMainBinding
import com.example.calculatehelper.presentation.calculate.CalculateActivity
import com.example.calculatehelper.presentation.lengthening.Lengthening
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this)[MainViewModel::class.java]
        binding= ActivityMainBinding.inflate(layoutInflater)
        val view=binding.root
        setContentView(view)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val currentAppLocales =
                this.getSystemService(LocaleManager::class.java)
                    .applicationLocales
        } else {
            val currentAppLocales =
                AppCompatDelegate.getApplicationLocales()
        }

        binding.language.setOnClickListener {
            showPopup(it);

        }


        binding.itemCard.setOnClickListener {
            intentToCalculate("Item")
        }
        binding.normalCard.setOnClickListener {
            intentToCalculate("normal")
        }
        binding.setting.setOnClickListener {

            val lengthening = Lengthening()

            lengthening.show(supportFragmentManager, "set amount of lengthening")

        }




        binding.restart.setOnClickListener {

           if ( viewModel.deleteDatabase()){
               Toast.makeText(this, getString(R.string.restart_successfully),Toast.LENGTH_SHORT).show()
           }else{
               Toast.makeText(this, getString(R.string.error),Toast.LENGTH_SHORT).show()

           }
        }
    }

    private fun showPopup(v: View) {
        val popup = PopupMenu(this, v)
        val inflater: MenuInflater = popup.getMenuInflater()
        inflater.inflate(R.menu.lang_menu, popup.getMenu())
        popup.apply {
            setOnMenuItemClickListener {
                when (it.itemId){
                    R.id.en -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            this@MainActivity.getSystemService(LocaleManager::class.java)
                                .applicationLocales = LocaleList.forLanguageTags("en")
                        } else {
                            AppCompatDelegate.setApplicationLocales(
                                LocaleListCompat.forLanguageTags(
                                    "en"
                                )
                            )
                        }
                        true
                    }

                    R.id.fa -> {
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            this@MainActivity.getSystemService(LocaleManager::class.java)
                                .applicationLocales = LocaleList.forLanguageTags("fa")
                        } else {
                            AppCompatDelegate.setApplicationLocales(
                                LocaleListCompat.forLanguageTags(
                                    "fa"
                                )
                            )
                        }
                        true
                    }

                    else -> {
                        false
                    }
                }
            }
        }
        popup.show()
    }


    fun intentToCalculate(type:String){
        var intent=Intent(this@MainActivity,CalculateActivity::class.java)
        intent.putExtra("type",type)
        startActivity(intent)

    }




}