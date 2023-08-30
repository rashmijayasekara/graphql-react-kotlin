package com.example.demoEight.repository

import com.example.demoEight.model.CommentEntity
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID


//In here we extend the JpaRepository
interface CommentRepository: JpaRepository<CommentEntity, UUID> {

}