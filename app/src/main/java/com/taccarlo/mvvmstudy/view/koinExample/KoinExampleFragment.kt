package com.taccarlo.mvvmstudy.view.koinExample

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.taccarlo.mvvmstudy.R
import com.taccarlo.mvvmstudy.databinding.FragmentKoinExampleBinding
import com.taccarlo.mvvmstudy.model.*
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
        val author = Author("Carlo", "Italy")
        val message = Message("Hello", "Hello".length)
        val comment = Comment(message, author).commentContent()
        val like = Vote(KindOfVote.LIKE, author).voteContent()
        binding.textView.text = getString(R.string.withoutKoin, comment, like)

        //with koin
        this.context?.let { startKoin(it, listOf(appModule)) }

        val student2: Student by inject()

        binding.textView2.text = getString(R.string.withKoin, comment, like)


        return binding.root
    }
}