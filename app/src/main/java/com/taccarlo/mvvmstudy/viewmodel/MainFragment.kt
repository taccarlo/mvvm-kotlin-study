package com.taccarlo.mvvmstudy.viewmodel

import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import com.taccarlo.mvvmstudy.api.RetrofitService
import com.taccarlo.mvvmstudy.view.MainAdapter
import androidx.lifecycle.ViewModelProvider
import com.taccarlo.mvvmstudy.R
import com.taccarlo.mvvmstudy.databinding.FragmentMainBinding
import com.taccarlo.mvvmstudy.model.MainRepository

class MainFragment : Fragment() {

    private val logTag = "MainFragment"
    private lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter()
    private var _binding: FragmentMainBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        val view = binding.root

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

        viewModel.linkedinRepoList.observe(viewLifecycleOwner, {
            Log.d(logTag, "onCreate: $it")
            if(it==null)
                Log.d(logTag, "No repo with these parameters")
            else
                adapter.setLinkedinRepoList(it)
        })
        viewModel.errorMessage.observe(viewLifecycleOwner, {

        })
        viewModel.getAllLinkedinRepos(getString(R.string.search_text), getString(R.string.search_text2))

        initSearchInputListener()

        return view
    }

    private fun initSearchInputListener(){

        binding.input.setOnEditorActionListener{ _: View, actionId: Int, _: KeyEvent? ->
            if(actionId == EditorInfo.IME_ACTION_DONE|| actionId == EditorInfo.IME_ACTION_SEARCH){
                val input = binding.input.text.toString()
                Log.d(logTag, "initSearchInputListener: $input")
                doSearch()
                true
            } else {
                false
            }
        }

        binding.input2.setOnEditorActionListener{ _: View, actionId: Int, _: KeyEvent? ->
            if(actionId == EditorInfo.IME_ACTION_DONE|| actionId == EditorInfo.IME_ACTION_SEARCH){
                val input2 = binding.input2.text.toString()
                Log.d(logTag, "initSearchInputListener: $input2")
                doSearch()
                true
            } else {
                false
            }
        }
    }

    private fun doSearch() {
        val input1 = binding.input.text.toString()
        val input2 = binding.input2.text.toString()
        //TODO: Dismiss keyboard
        Log.d(logTag, "doSearch: $input1 and $input2")
        viewModel.getAllLinkedinRepos(input1, input2)
    }

}