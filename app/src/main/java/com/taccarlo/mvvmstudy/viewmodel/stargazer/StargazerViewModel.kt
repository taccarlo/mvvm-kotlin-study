package com.taccarlo.mvvmstudy.viewmodel.stargazer

import androidx.lifecycle.ViewModel
import com.taccarlo.mvvmstudy.model.*

class StargazerViewModel : ViewModel() {

    fun showKoinList(likeKoin: Vote, commentKoin: Comment): String {

        commentKoin.author = "Carlo"
        commentKoin.country = "ITA"
        commentKoin.message = "Hello"

        likeKoin.author = "Bob"
        likeKoin.country = "ENG"
        likeKoin.kind = KindOfVote.LIKE
        val listKoin: String =
            "Objects created with Koin:\n" + commentKoin.showContent() + " " + likeKoin.showContent() + "\n\n\n"
        val listBuilder: String = "Object created with build pattern:\n" + createList()
        return listKoin + listBuilder
    }

    private fun createList(): String {
        val feedbackComplete=FeedbackComplete.Builder()
            .comment("Mario", "ESP","salve")
            .vote(KindOfVote.LIKE, "Mario","ESP")
            .date("10/02/2021")
        return feedbackComplete.comment?.showContent()+"\n"+feedbackComplete.vote?.showContent()
    }
}
