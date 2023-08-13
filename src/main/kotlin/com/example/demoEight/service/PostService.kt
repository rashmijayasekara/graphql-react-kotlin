package com.example.demoEight.service

import com.example.demoEight.model.PostEntity
import com.example.demoEight.repository.PostRepository
import com.example.demoEight.repository.UserRepository
import com.example.demoEight.resolver.AddPost
import com.example.demoEight.resolver.Post
import org.springframework.data.domain.PageRequest
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

    fun getPosts(page: Int, size: Int): List<Post>{
        val page=PageRequest.of(page,size)
        return  postRepository.findAll(page).map {
            Post(
                id=it.id,
                title = it.title,
                description = it.description
            )
        }.toList()
    }

    fun getPostByAuthor(userId: UUID): List<Post> {
        return postRepository.findAllByAuthorId(userId).map {
            Post(
                id = it.id, title = it.title, description = it.description
            )
        }

    }

    fun addPost(addPost: AddPost): Post {

        val findById = userRepository.findById(addPost.authorId).orElseThrow{RuntimeException("UserID is not valid")}
        val postEntity=PostEntity(
            title = addPost.title,
            description = addPost.description,
            author = findById
        )
        val createdPost=postRepository.save(postEntity)

        return Post(
            id=createdPost.id,
            title = createdPost.title,
            description = createdPost.description
        )
    }
}