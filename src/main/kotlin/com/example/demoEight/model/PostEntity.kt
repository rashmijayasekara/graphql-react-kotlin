package com.example.demoEight.model

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "posts")
class PostEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID") val id: UUID? = null,
    @Column val title: String,
    @Column val description: String? = null,
    @ManyToOne @JoinColumn(name = "author_id") val author: UserEntity
)