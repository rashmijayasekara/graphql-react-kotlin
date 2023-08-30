package com.example.demoEight.service

import com.example.demoEight.model.UserEntity
import com.example.demoEight.repository.UserRepository
import com.example.demoEight.resolver.AddUserInput
import com.example.demoEight.resolver.User
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {
    fun findByPostId(postId: UUID): User {
//        val postEntity = postRepository.findById(postId)
//            .orElseThrow { RuntimeException("Post does not exist for this user postId: $postId") }

        val userEntity = userRepository.findByPostsId(postId)

        return User(
            id = userEntity.id, name = userEntity.name
        )
    }

    fun addUser(userInput: AddUserInput): UUID {
        val userEntity = UserEntity(
            name = userInput.name
        )

        val user = userRepository.save(userEntity)

        user.id ?: throw RuntimeException("User id can't be null")
        return user.id
    }

    fun getUsers(page: Int, size: Int): List<User> {
        val users = userRepository.findAll(PageRequest.of(page, size))
        return users.map {
            User(
                id = it.id, name = it.name
            )
        }.toList()

    }

    fun findByCommentId(id: UUID?): User {
        id ?: throw RuntimeException("comment id can't be null")
        val userEntity = userRepository.findByCommentsId(id)

        return User(
            id = userEntity.id, name = userEntity.name

        )
    }
//    fun getUsers(page: Int, size: Int): List<User> {
//        val users=PageRequest.of(page,size);
//        return userRepository.findAll(users).map {
//            User(
//                id=it.id,
//                name = it.name
//            )
//        }.toList()
//
//    }


}