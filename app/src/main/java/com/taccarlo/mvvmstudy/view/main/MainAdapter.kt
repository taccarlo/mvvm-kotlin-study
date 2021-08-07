package com.taccarlo.mvvmstudy.view.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taccarlo.mvvmstudy.databinding.AdapterLinkedinRepoBinding
import com.taccarlo.mvvmstudy.model.LinkedinRepository

class MainAdapter(private var listener: (pos:Int, linRepo: LinkedinRepository) -> Unit) : RecyclerView.Adapter<MainViewHolder>() {

    var linkedinRepos = mutableListOf<LinkedinRepository>()

    fun setLinkedinRepoList(linkedinRepos: List<LinkedinRepository>) {
        this.linkedinRepos = linkedinRepos.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterLinkedinRepoBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val linkedinRepo = linkedinRepos[position]
        holder.binding.name.text = linkedinRepo.login
        Glide.with(holder.itemView.context).load(linkedinRepo.avatar_url).into(holder.binding.imageview)
        holder.itemView.setOnClickListener {
            listener.invoke(position, linkedinRepo)
        }
    }

    override fun getItemCount(): Int {
        return linkedinRepos.size
    }
}

class MainViewHolder(val binding: AdapterLinkedinRepoBinding) : RecyclerView.ViewHolder(binding.root) {

}