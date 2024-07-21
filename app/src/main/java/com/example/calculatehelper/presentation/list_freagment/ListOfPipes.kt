package com.example.calculatehelper.presentation.list_freagment

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.DialogFragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatehelper.databinding.ListOfPipesFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListOfPipes(var type:String) :DialogFragment() {

    lateinit var viewModel: ListOfPipesViewModel
    private lateinit var binding: ListOfPipesFragmentBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel=ViewModelProvider(this)[ListOfPipesViewModel::class.java]
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = ListOfPipesFragmentBinding.inflate(inflater, container, false)
        val adapter = Adapter(viewModel.list,type)

        binding.listRecycler.adapter=adapter
        binding.listRecycler.layoutManager=LinearLayoutManager(requireContext(),
            RecyclerView.HORIZONTAL,false)


        return binding.root

    }
}