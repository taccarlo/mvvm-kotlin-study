package com.taccarlo.mvvmstudy.view.stargazer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.taccarlo.mvvmstudy.R
import com.taccarlo.mvvmstudy.databinding.FragmentStargazerBinding
import com.taccarlo.mvvmstudy.model.Comment
import com.taccarlo.mvvmstudy.model.LinkedinRepository
import com.taccarlo.mvvmstudy.model.Vote
import com.taccarlo.mvvmstudy.model.appModule
import com.taccarlo.mvvmstudy.viewmodel.stargazer.StargazerViewModel
import org.koin.android.ext.android.inject
import org.koin.android.ext.android.startKoin


/**
 * <i>FragmentListElement</i> class that show the details of a item of the list shown in <i>FragmentMain</i>.
 * @author Carlo Tacchella
 * @version 0.0.1
 * @since 2021-07-06
 */
class StargazerFragment : Fragment() {

    private lateinit var itemId: String
    private lateinit var linkedinRepository: LinkedinRepository
    private var _binding: FragmentStargazerBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: StargazerViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        itemId = requireArguments().getString("itemId").toString()
        linkedinRepository = requireArguments().getParcelable("itemPassed")!!

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStargazerBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(StargazerViewModel::class.java)


        //with koin
        this.context?.let { startKoin(it, listOf(appModule)) }
        val commentKoin: Comment by inject()
        val likeKoin: Vote by inject()
        binding.textView.text = this.context?.let { viewModel.showKoinList(likeKoin, commentKoin) }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message = "ID: " + linkedinRepository.id
        binding.itemTitle.text = linkedinRepository.login
        binding.itemDate.text = message
        binding.itemUrl.text = getString(R.string.link_to_profile)

        this.context?.let {
            Glide.with(it).load(linkedinRepository.avatar_url).into(binding.profilePic)
        }

        binding.itemUrl.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(linkedinRepository.html_url)
            startActivity(openURL)
        }

    }
}