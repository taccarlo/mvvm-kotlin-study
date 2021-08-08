package com.taccarlo.mvvmstudy.viewmodel.stargazer

import androidx.lifecycle.ViewModel
import com.taccarlo.mvvmstudy.model.Comment
import com.taccarlo.mvvmstudy.model.KindOfVote
import com.taccarlo.mvvmstudy.model.Vote

class StargazerViewModel : ViewModel() {

    fun showKoinList(likeKoin: Vote, commentKoin: Comment): String {

        commentKoin.author="Carlo"
        commentKoin.country="ITA"
        commentKoin.message="Hello"

        likeKoin.author="Bob"
        likeKoin.country="ENG"
        likeKoin.kind= KindOfVote.LIKE
        val listKoin:String = "Objects created with Koin:\n"+commentKoin.showContent()+" "+likeKoin.showContent()+"\n"
        val listBuilder:String = "List of objects created with build pattern:\n"+createList()
        return listKoin+listBuilder
    }

    private fun createList(): String {


        return "ciao"
    }
}

class FeedbackBuilder private constructor(builder: FeedbackBuilder.Builder){
    val message:String?
    val author:String?
    val country:String?
    val kind:KindOfVote?
    val comment:Comment?
        get() {
            return this.comment
        }
    val vote:Comment?
        get() {
            return this.vote
        }
    init{
        this.message=builder.message
        this.author=builder.author
        this.country=builder.country
        this.kind=builder.kind
    }
    class Builder {

        var message: String? = null
            private set
        var author: String? = null
            private set
        var country: String? = null
            private set
        var kind: KindOfVote? = null
            private set

        fun message(message: String) = apply { this.message = message }
        fun author(author: String) = apply { this.author = author }
        fun country(country: String) = apply { this.country = country }
        fun kind(kind:KindOfVote)=apply{ this.kind=kind }

        fun build() = FeedbackBuilder(this)
    }
}
