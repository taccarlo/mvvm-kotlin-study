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
    ): View {

        _binding = FragmentKoinExampleBinding.inflate(inflater, container, false)


        //without koin
        val author = AuthorDisplay()
        val message = MessageDisplay()
        val comment = Comment(message, author)
            comment.author="Carlo"
            comment.country="ITA"
            comment.message="Ciao"
        val like = Vote(author)
        like.author="Bob"
        like.country="ENG"
        like.kind=KindOfVote.LIKE
        binding.textView.text = getString(R.string.withoutKoin, comment.showContent(), like.showContent())

        //with koin
        this.context?.let { startKoin(it, listOf(appModule)) }

        val commentKoin: Comment by inject()
        commentKoin.author="Carlo"
        commentKoin.country="ITA"
        commentKoin.message="Hello"
        val likeKoin: Vote by inject()
        likeKoin.author="Bob"
        likeKoin.country="ENG"
        likeKoin.kind=KindOfVote.LIKE

        binding.textView2.text = getString(R.string.withKoin, commentKoin.showContent(), likeKoin.showContent())


        return binding.root
    }
}