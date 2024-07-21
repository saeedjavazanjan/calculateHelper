package com.example.calculatehelper.presentation.lengthening

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.DialogFragment
import com.example.calculatehelper.databinding.LengtheningBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class Lengthening:
    DialogFragment() {


    @Inject
    lateinit var sharedPreferences: SharedPreferences

    @Inject
    lateinit var shredEditor: SharedPreferences.Editor

    private lateinit var binding: LengtheningBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = LengtheningBinding.inflate(inflater, container, false)


        binding.save.setOnClickListener {
            shredEditor.putString("lengthening",binding.amountOfLengthening.text.toString())
            shredEditor.apply()
            shredEditor.commit()
            dismiss()

        }
        binding.cancel.setOnClickListener {

            dismiss()
        }

     binding.amountOfLengthening.setText(sharedPreferences.getString("lengthening","0"))









        return binding.root





    }


    override fun onStart() {
        super.onStart()
        dialog!!.window
            ?.setLayout(
                WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.WRAP_CONTENT
            )
    }


}