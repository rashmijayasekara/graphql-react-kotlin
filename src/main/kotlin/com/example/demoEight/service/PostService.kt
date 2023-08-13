package com.example.demoEight.service

import com.example.demoEight.repository.PostRepository
import com.example.demoEight.repository.UserRepository
import com.example.demoEight.resolver.Post
import org.springframework.stereotype.Service
import java.util.*

@Service
class PostService(
    private val postRepository: PostRepository, private val userRepository: UserRepository
) {

    fun getPosts(): List<Post> {
        return postRepository.findAll().map {
            Post(
                id = it.id, title = it.title, description = it.description
            )
        }
    }

    fun getPostByAuthor(userId: UUID): List<Post> {
        return postRepository.findAllByAuthorId(userId).map {
            Post(
                id = it.id, title = it.title, description = it.description
            )
        }

    }
}