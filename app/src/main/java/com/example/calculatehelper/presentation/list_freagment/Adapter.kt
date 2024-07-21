package com.example.calculatehelper.presentation.list_freagment

import android.provider.Settings.Global.getString
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.calculatehelper.R
import com.example.calculatehelper.database.Pipe
import com.example.calculatehelper.databinding.ItemOfPipesListBinding

class Adapter(var list: List<Pipe>,var type:String): RecyclerView.Adapter<Adapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ItemOfPipesListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)

    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       holder.bind(list[position],type)

    }




class ViewHolder(val binding: ItemOfPipesListBinding):RecyclerView.ViewHolder(binding.root){

    fun bind(pipe:Pipe,type:String){
        if(type.equals("Item")){
            binding.mainLayout.setBackgroundResource(R.mipmap.item_pipe)
        }else{
            binding.mainLayout.setBackgroundResource(R.drawable.normal_pipe)

        }

        val bottomOfPipe= (pipe.bottomOfThePipe.toString().toInt())+(pipe.topOfThePillar.toString().toInt())
        binding.pipeCode.text="pipe code: "+pipe.code
        binding.bottomOfPipe.text =bottomOfPipe.toString()
        binding.topOfPipe.text=pipe.lengthOfPipeTop
        binding.hypotenuse.text=pipe.hypotenuseLength
        binding.totalLength.text= "total length: "+ (pipe.totalLength)
        binding.degree.text=pipe.angleDegree+"\u00B0"



    }

}


}