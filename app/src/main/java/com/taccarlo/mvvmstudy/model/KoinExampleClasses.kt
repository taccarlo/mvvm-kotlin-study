package com.taccarlo.mvvmstudy.model

class AuthorDisplay {
    fun nameComplete(name: String, country: String) = "User $name from: $country"
}

class MessageDisplay {
    fun messageComplete(message: String) = "Message $message long ${message.length} characters"
}

class Comment(val messageDisplay: MessageDisplay, val authorDisplay: AuthorDisplay) : Feedback {
    lateinit var message: String
    lateinit var author: String
    lateinit var country: String
    override fun showContent(): String =
        authorDisplay.nameComplete(author, country)+" wrote "+messageDisplay.messageComplete(message) + "\n"
}

interface Feedback {
    fun showContent(): String
}


enum class KindOfVote {
    LIKE, DISLIKE
}

class Vote(private val authorDisplay: AuthorDisplay) : Feedback {
    lateinit var kind: KindOfVote
    lateinit var author: String
    lateinit var country: String

    override fun showContent(): String {
        val kOfVote = when (kind) {
            KindOfVote.LIKE -> "like"
            KindOfVote.DISLIKE -> "dislike"
        }
        return authorDisplay.nameComplete(author, country) + " put a " + kOfVote+"\n"
    }

}