package com.taccarlo.mvvmstudy.model


class FeedbackComplete private constructor(
    val comment: Comment?,
    val vote: Vote?,
    val date:String?
){
    class Builder {
        var comment: Comment? = null
        var vote: Vote? = null
        var date:String? = null
        fun date(date:String)= apply{this.date=date}
        fun comment( author:String, country:String, message:String)=apply{
            //TODO: why i need !!, i just set it to comment that can't be null
            val authorDisplay = AuthorDisplay()
            var messageDisplay= MessageDisplay()
            this.comment=Comment(messageDisplay, authorDisplay)
            this.comment!!.author=author
            this.comment!!.country=country
            this.comment!!.message=message
        }
        fun vote(kind:KindOfVote, author:String, country:String)=apply{
            val authorDisplay = AuthorDisplay()
            this.vote=Vote(authorDisplay)
            this.vote!!.author=author
            this.vote!!.country=country
            this.vote!!.kind=kind
        }
        fun build() = FeedbackComplete(comment,vote,date)
    }
}
