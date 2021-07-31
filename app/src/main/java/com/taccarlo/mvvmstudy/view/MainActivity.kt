package com.taccarlo.mvvmstudy.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.taccarlo.mvvmstudy.*
import com.taccarlo.mvvmstudy.databinding.ActivityMainBinding
import com.taccarlo.mvvmstudy.model.MainRepository
import com.taccarlo.mvvmstudy.service.RetrofitService
import com.taccarlo.mvvmstudy.viewmodel.MainViewModel
import com.taccarlo.mvvmstudy.viewmodel.MyViewModelFactory


class MainActivity : AppCompatActivity() {
    private val TAG = "MainActivity"
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: MainViewModel

    private val retrofitService = RetrofitService.getInstance()
    private val adapter = MainAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService))).get(
            MainViewModel::class.java)

        binding.recyclerview.adapter = adapter

        viewModel.linkedinRepoList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
            adapter.setMovieList(it)
        })
        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getAllMovies()

        initSearchInputListener()
    }
    private fun initSearchInputListener(){

        binding.input.setOnEditorActionListener{ view: View, actionId: Int, _: KeyEvent? ->
            if(actionId == EditorInfo.IME_ACTION_DONE|| actionId == EditorInfo.IME_ACTION_SEARCH){
                val input = binding.input.text.toString()
                Log.d(TAG, "initSearchInputListener: $input")
                true
            } else {
                false
            }
        }
    }
}