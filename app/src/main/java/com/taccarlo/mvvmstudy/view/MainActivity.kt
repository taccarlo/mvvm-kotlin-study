package com.taccarlo.mvvmstudy.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.taccarlo.mvvmstudy.*
import com.taccarlo.mvvmstudy.databinding.ActivityMainBinding
import com.taccarlo.mvvmstudy.model.MainRepository
import com.taccarlo.mvvmstudy.api.RetrofitService
import com.taccarlo.mvvmstudy.model.LinkedinRepository
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
            if(it==null)
                Log.d(TAG, "No repo with these parameters")
            else
                adapter.setLinkedinRepoList(it)
        })
        viewModel.errorMessage.observe(this, Observer {

        })
        viewModel.getAllLinkedinRepos(getString(R.string.search_text), getString(R.string.search_text2))

        initSearchInputListener()
    }
    private fun initSearchInputListener(){

        binding.input.setOnEditorActionListener{ view: View, actionId: Int, _: KeyEvent? ->
            if(actionId == EditorInfo.IME_ACTION_DONE|| actionId == EditorInfo.IME_ACTION_SEARCH){
                val input = binding.input.text.toString()
                Log.d(TAG, "initSearchInputListener: $input")
                doSearch(view)
                true
            } else {
                false
            }
        }

        binding.input2.setOnEditorActionListener{ view: View, actionId: Int, _: KeyEvent? ->
            if(actionId == EditorInfo.IME_ACTION_DONE|| actionId == EditorInfo.IME_ACTION_SEARCH){
                val input2 = binding.input2.text.toString()
                Log.d(TAG, "initSearchInputListener: $input2")
                doSearch(view)
                true
            } else {
                false
            }
        }
    }

    private fun doSearch(v: View) {
        val input1 = binding.input.text.toString()
        val input2 = binding.input2.text.toString()
        // Dismiss keyboard
        dismissKeyboard(v.windowToken)
        Log.d(TAG, "doSearch: $input1 and $input2")
        viewModel.getAllLinkedinRepos(input1, input2)
    }

    private fun dismissKeyboard(windowToken: IBinder) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
        imm?.hideSoftInputFromWindow(windowToken, 0)
    }
}