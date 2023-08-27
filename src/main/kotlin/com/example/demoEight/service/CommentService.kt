package com.example.demoEight.service

import com.example.demoEight.repository.CommentRepository
import com.example.demoEight.resolver.Comment
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service

@Service
class CommentService(
    private val commentRepository: CommentRepository
) {
    fun getComments(page: Int, size: Int): List<Comment> {
        val pageRequest = PageRequest.of(page, size)
        val commentEntities=commentRepository.findAll(pageRequest)

        return commentEntities.map {
            Comment(
                id = it.id,
                text= it.text
            )
        }.toList()
    }
}