package com.taccarlo.mvvmstudy.view.koinExample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taccarlo.mvvmstudy.R
import com.taccarlo.mvvmstudy.databinding.FragmentKoinExampleBinding

class KoinExampleFragment : Fragment() {

    private var _binding: FragmentKoinExampleBinding ?= null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentKoinExampleBinding.inflate(inflater, container, false)
        val course = Course()
        val friend = Friend()
        val student = Student(course, friend)
        binding.textView.text = student.doWork()
        return binding.root
    }
}