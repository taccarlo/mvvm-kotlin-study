package com.taccarlo.mvvmstudy.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.taccarlo.mvvmstudy.databinding.AdapterMovieBinding
import com.taccarlo.mvvmstudy.model.LinkedinRepository

class MainAdapter: RecyclerView.Adapter<MainViewHolder>() {

    var linkedinRepos = mutableListOf<LinkedinRepository>()

    fun setMovieList(movies: List<LinkedinRepository>) {
        this.linkedinRepos = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = AdapterMovieBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val linkedinRepo = linkedinRepos[position]
        holder.binding.name.text = linkedinRepo.login
        Glide.with(holder.itemView.context).load(linkedinRepo.avatar_url).into(holder.binding.imageview)

    }

    override fun getItemCount(): Int {
        return linkedinRepos.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

}