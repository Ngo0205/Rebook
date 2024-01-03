package com.example.rebook.model

import androidx.lifecycle.ViewModel
import com.example.rebook.repositories.PostRepository

class PostViewModel(private val postRepository: PostRepository):ViewModel() {
    fun insertPost(adminId:Int, bookId:Int, body:String):Long{
        return postRepository.insertPost(adminId, bookId, body)
    }
    fun updatePost(postId:Int, adminId:Int, bookId:Int, body:String):Int{
        return postRepository.updatePost(postId, adminId, bookId, body)
    }
}