package com.example.demoEight.model


import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id @GeneratedValue(strategy = GenerationType.AUTO, generator = "UUID") val id: UUID? = null,

    @Column val name: String,

    @OneToMany(mappedBy = "") val posts: Set<PostEntity> = setOf()

)