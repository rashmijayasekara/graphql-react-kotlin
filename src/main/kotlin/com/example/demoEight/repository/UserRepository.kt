package com.example.demoEight.repository

import com.example.demoEight.model.UserEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface UserRepository : JpaRepository<UserEntity, UUID> {
    fun findByPostsId(postId: UUID): UserEntity
    fun findByCommentsId(id: UUID): UserEntity
}