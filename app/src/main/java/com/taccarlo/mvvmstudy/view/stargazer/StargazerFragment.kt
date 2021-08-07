package com.taccarlo.mvvmstudy.view.stargazer

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.taccarlo.mvvmstudy.R
import com.taccarlo.mvvmstudy.databinding.FragmentStargazerBinding
import com.taccarlo.mvvmstudy.model.LinkedinRepository


/**
 * <i>FragmentListElement</i> class that show the details of a item of the list shown in <i>FragmentMain</i>.
 * @author Carlo Tacchella
 * @version 0.0.1
 * @since 2021-07-06
 */
class StargazerFragment : Fragment() {

    //TODO: separate fragment from view model
    private lateinit var itemId: String
    private lateinit var linkedinRepository: LinkedinRepository
    private var _binding: FragmentStargazerBinding ?= null
    private val binding get() = _binding!!

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val message= "ID: "+linkedinRepository.id
        binding.itemTitle.text = linkedinRepository.login
        binding.itemDate.text = message
        binding.itemUrl.text = getString(R.string.link_to_profile)

        this.context?.let { Glide.with(it).load(linkedinRepository.avatar_url).into(binding.profilePic) }

        binding.itemUrl.setOnClickListener {
            val openURL = Intent(Intent.ACTION_VIEW)
            openURL.data = Uri.parse(linkedinRepository.html_url)
            startActivity(openURL)
        }

    }
}