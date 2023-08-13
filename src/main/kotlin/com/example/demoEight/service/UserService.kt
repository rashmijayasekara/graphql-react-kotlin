package com.example.demoEight.service

import com.example.demoEight.model.UserEntity
import com.example.demoEight.repository.PostRepository
import com.example.demoEight.repository.UserRepository
import com.example.demoEight.resolver.AddUserInput
import com.example.demoEight.resolver.User
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository, private val postRepository: PostRepository
) {
    fun findByPostId(postId: UUID): User {
        val postEntity = postRepository.findById(postId)
            .orElseThrow { RuntimeException("Post does not exist for this user postId: $postId") }
        return User(
            id = postEntity.author.id, name = postEntity.author.name
        )
    }

    fun addUser(userInput: AddUserInput): UUID {
        val userEntity= UserEntity(
            name = userInput.name
        )

        val user=userRepository.save(userEntity)

        user.id?: throw RuntimeException("User id can't be null")
        return user.id
    }


}