package com.example.plantwebapp.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer


import com.example.plantwebapp.LOG_TAG
import com.example.plantwebapp.R
import com.example.plantwebapp.data.Plant
import java.lang.StringBuilder

import com.example.plantwebapp.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding : MainFragmentBinding

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MainFragmentBinding.inflate(inflater, container, false)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        viewModel.plantData.observe(this, Observer
        {
            val plantTitles = StringBuilder()
            for(plant in it){
                plantTitles.append(plant.plant)
                    .append("\n")
            }

            binding.message.text = plantTitles

        })


        // Testing Plant class - Hardcode a Plant object and output the object to the log
//        val plant = Plant ("plant01", "Figus Elasticia", "Really retro plant", 12.99 )
//        Log.i(LOG_TAG, plant.plantTitle);




       //return inflater.inflate(R.layout.main_fragment, container, false)
        return binding.root
    }



}