package com.example.demoEight.repository

import com.example.demoEight.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface CommentRepository: JpaRepository<CommentEntity, UUID> {

}