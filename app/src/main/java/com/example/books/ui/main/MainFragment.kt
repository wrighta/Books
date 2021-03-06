package com.example.books.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer


import com.example.books.databinding.MainFragmentBinding


class MainFragment : Fragment() {

    private lateinit var viewModel: MainViewModel
    private lateinit var binding : MainFragmentBinding
    private lateinit var adapter : BookListAdapter

    @SuppressLint("FragmentLiveDataObserve")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = MainFragmentBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        with(binding.recyclerView)
        {
            setHasFixedSize(true)
        }

        // this Fragment observes the data in the viewModel, it does not need to know where this data comes from.
        viewModel.booksResponse?.observe(viewLifecycleOwner, Observer {
//            // when 'it' changes it means the user object has an authentication token. I hard code the user name and password in the Repository for the login.
//              binding.message.text = "Number of items returned " + it.totalItems + " Books" + it.items.get(1).volumeInfo.title
            adapter = BookListAdapter(requireContext(), it.items)
            binding.recyclerView.adapter = adapter

        })


        // Testing Plant class - Hardcode a Plant object and output the object to the log
//        val plant = Plant ("plant01", "Figus Elasticia", "Really retro plant", 12.99 )
//        Log.i(LOG_TAG, plant.plantTitle);




       //return inflater.inflate(R.layout.main_fragment, container, false)
        return binding.root
    }



}