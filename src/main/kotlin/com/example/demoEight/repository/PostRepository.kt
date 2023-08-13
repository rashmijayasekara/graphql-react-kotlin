package com.example.demoEight.repository

import com.example.demoEight.model.PostEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface PostRepository : JpaRepository<PostEntity, UUID> {
    fun findAllByAuthorId(authorId: UUID): List<PostEntity>
}