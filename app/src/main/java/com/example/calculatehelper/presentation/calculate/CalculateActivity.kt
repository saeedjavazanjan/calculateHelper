package com.example.calculatehelper.presentation.calculate

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import com.example.calculatehelper.R
import com.example.calculatehelper.databinding.ActivityCalculateBinding
import com.example.calculatehelper.presentation.lengthening.Lengthening
import com.example.calculatehelper.presentation.list_freagment.ListOfPipes
import dagger.hilt.android.AndroidEntryPoint
import java.nio.channels.Pipe

@AndroidEntryPoint
class CalculateActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCalculateBinding
    private lateinit var type: String
    lateinit var viewModel: CalculateViewModel

    private lateinit var pipeCode: String
    private lateinit var degreeResult: String
    private lateinit var hypotenuseResult: String
    private lateinit var totalLengthResult: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[CalculateViewModel::class.java]

        binding = ActivityCalculateBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        if (viewModel.list.isEmpty()){

            binding.showList.isEnabled=false
        }

        val intent = intent
        type = intent.extras!!.getString("type").toString()

        if (type.equals("Item")) {
            binding.b5.setText("0")
            binding.b5.isEnabled = false
        }





        binding.b1.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty()) {
                binding.b1Layout.isErrorEnabled = false;

            }
        }
        binding.b2.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty()) {
                binding.b2Layout.isErrorEnabled = false;

            }
        }
        binding.b3.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty()) {
                binding.b3Layout.isErrorEnabled = false;

            }
        }
        binding.b4.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty()) {
                binding.b4Layout.isErrorEnabled = false;

            }
        }
        binding.b5.doOnTextChanged { text, start, before, count ->
            if (text!!.isNotEmpty()) {
                binding.b5Layout.isErrorEnabled = false;

            }
        }

        binding.calculateButton.setOnClickListener {


            if (!checkIsEmpty()) {
                pipeCode = binding.pipeCode.text.toString()
                val b1 = binding.b1.text.toString().toInt()
                val b2 = binding.b2.text.toString().toInt()
                val b3 = binding.b3.text.toString().toInt()
                val b4 = binding.b4.text.toString().toInt()
                val b5 = binding.b5.text.toString().toInt()


                viewModel.calculate(b1, b2, b3, b4, b5)
                degreeResult = String.format("%.1f", viewModel.degree)
                hypotenuseResult = viewModel.hypotenuse.toString()
                totalLengthResult = viewModel.totalLength.toString()
                binding.degreeResult.text = degreeResult+"\u00B0"
                binding.hypotenuseResult.text = hypotenuseResult
                binding.totalLengthResult.text = totalLengthResult


            }
        }

        binding.addToList.setOnClickListener {
            if (!::pipeCode.isInitialized || !::totalLengthResult.isInitialized || !::degreeResult.isInitialized || !::hypotenuseResult.isInitialized) {
                Toast.makeText(this, getString(R.string.firstCalculate), Toast.LENGTH_SHORT)
                    .show()

            } else {
                if (viewModel.checkExist(pipeCode)) {
                    if (viewModel.update(
                            pipeCode,
                            totalLengthResult,
                            degreeResult,
                            hypotenuseResult,
                            binding.b5.text.toString(),
                            binding.b4.text.toString(),
                            binding.b1.text.toString()
                        )==1
                    ) {
                        Toast.makeText(this,getString(R.string.update), Toast.LENGTH_SHORT).show()
                    }else {
                        Toast.makeText(this, getString(R.string.error), Toast.LENGTH_SHORT).show()

                    }
                } else {

                    if (viewModel.insertInDatabase(
                            com.example.calculatehelper.database.Pipe(
                                pipeCode,
                                totalLengthResult,
                                degreeResult,
                                hypotenuseResult,
                                binding.b5.text.toString(),
                                binding.b4.text.toString(),
                                binding.b1.text.toString()

                            )

                        )
                    ) {
                        Toast.makeText(this,  getString(R.string.save_in_list), Toast.LENGTH_SHORT).show()

                        binding.showList.isEnabled=true

                    } else {
                        Toast.makeText(this,  getString(R.string.error), Toast.LENGTH_SHORT).show()
                    }
                }


            }
        }

        binding.showList.setOnClickListener {

            val listFragment = ListOfPipes(type)

            listFragment.show(supportFragmentManager, "set amount of lengthening")

        }

    }


    fun checkIsEmpty(): Boolean {
        var result = false
        if (binding.pipeCode.text.toString().equals("")) {
            binding.pipeCodeLayout.setErrorEnabled(true);
            binding.pipeCodeLayout.setError("This field cannot be empty.");
            result = true
        }
        if (binding.b1.text.toString().equals("")) {
            binding.b1Layout.setErrorEnabled(true);
            binding.b1Layout.setError("This field cannot be empty.");
            result = true
        }
        if (binding.b2.text.toString().equals("")) {
            binding.b2Layout.setErrorEnabled(true);
            binding.b2Layout.setError("This field cannot be empty.");
            result = true

        }
        if (binding.b3.text.toString().equals("")) {
            binding.b3Layout.setErrorEnabled(true);
            binding.b3Layout.setError("This field cannot be empty.");
            result = true

        }
        if (binding.b4.text.toString().equals("")) {
            binding.b4Layout.setErrorEnabled(true);
            binding.b4Layout.setError("This field cannot be empty.");
            result = true
        }
        if (binding.b5.text.toString().equals("")) {
            binding.b5Layout.setErrorEnabled(true);
            binding.b5Layout.setError("This field cannot be empty.");
            result = true

        }
        return result
    }
}