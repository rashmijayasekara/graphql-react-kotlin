package com.example.demoEight.service

import com.example.demoEight.repository.CommentRepository
import com.example.demoEight.resolver.Comment
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun getComments(page: Int, size: Int): List<Comment> {
        val pageRequest = PageRequest.of(page, size)
        val commentEntities = commentRepository.findAll(pageRequest)

        return commentEntities.map {
            Comment(
                id = it.id, text = it.text
            )
        }.toList()
    }

    fun getCommentsByPostId(postId: UUID?): List<Comment> {
        postId ?: throw RuntimeException("postId can't be null")
        return commentRepository.findAllByPostId(postId).map {
            Comment(
                id = it.id, text = it.text
            )
        }.toList()


    }

    fun getCommentsByUserId(userId: UUID?): List<Comment> {
        userId ?: throw RuntimeException("User Id can't be null")
        return commentRepository.findAllByAuthorId(userId).map {
            Comment(
                id = it.id, text = it.text
            )
        }.toList()
    }
}