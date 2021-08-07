package com.taccarlo.mvvmstudy.model

import com.taccarlo.mvvmstudy.model.KindOfVote.*

class Author(private val name:String, private val country:String){
    fun nameComplete() = "User $name from: $country"
}

class Message(private val message:String, private val messageLength:Int){
    fun messageComplete() = "Message $message long $messageLength characters"
}

enum class KindOfVote {
    LIKE, DISLIKE
}

interface Feedback {
    val author: Author
}

class Comment(private val message: Message, override val author: Author):Feedback {
    fun commentContent(): String = message.messageComplete()+"\n"+author.nameComplete()
}

class Vote(private val kind: KindOfVote, override val author: Author):Feedback {
    fun voteContent(): String {
        val kOfVote = when(kind) {
            LIKE -> "like"
            DISLIKE -> "dislike"
        }
        return author.nameComplete()+" put "+kOfVote
    }

}

/** EXAMPLE **/

class Student(private val course: Course, private val friend: Friend) {

    fun doWork(): String =
        course.study() + "\n" + friend.play()
}

class Friend {
    fun play(): String = "I am playing with my friend"
}

class Course {
    fun study(): String = "I am studying"
}