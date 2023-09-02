package com.example.demoEight.repository

import com.example.demoEight.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import java.util.*


//In here we extend the JpaRepository
interface CommentRepository : JpaRepository<CommentEntity, UUID> {
    fun findAllByPostId(postId: UUID): List<CommentEntity>
    fun findAllByAuthorId(userId: UUID): List<CommentEntity>
    @Query("select c from CommentEntity  c where c.post.id in ?1")
    fun findAllByPostIds(postId: List<UUID>): List<CommentEntity>
}