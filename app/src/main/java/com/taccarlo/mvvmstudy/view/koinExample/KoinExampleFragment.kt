package com.taccarlo.mvvmstudy.view.koinExample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taccarlo.mvvmstudy.databinding.FragmentKoinExampleBinding
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin

class KoinExampleFragment : Fragment() {

    private var _binding: FragmentKoinExampleBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentKoinExampleBinding.inflate(inflater, container, false)


        //without koin
        val course = Course()
        val friend = Friend()
        val student = Student(course, friend)
        binding.textView.text = "without koin:\n"+student.doWork()

        //with koin
        this.context?.let { startKoin(it, listOf(appModule)) }

        val student2: Student by inject()

        binding.textView2.text = "with koin\n"+ student2.doWork()

        return binding.root
    }
}