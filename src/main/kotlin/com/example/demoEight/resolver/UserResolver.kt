package com.example.demoEight.resolver

import com.example.demoEight.service.UserService
import com.example.demoEight.util.JwtUtil
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.util.*
import javax.crypto.SecretKey

@Controller
class UserResolver(
    private val userService: UserService, @Value("\${key}") private val secretKey: String
) {
    @MutationMapping
    fun addUserInput(@Argument("addUserInput") addUserInput: AddUserInput): UUID {
        return userService.addUser(addUserInput)
    }

    //Field Resolver
    @SchemaMapping(typeName = "Post")
//    when we write this field resovler, graphql
//    call this field resolver and when the graphql
//    calls this field resolver it fetch data from the database or the thrid party or whatever
    fun author(post: Post): User {
//        return User(
//            id = UUID.randomUUID(),
//            name = "title =${post.title} id=${post.id}"
//        )
        LOGGER.info("Fetching comments for POST ${post.id}")
        val postId = post.id ?: throw RuntimeException("postId can't be null")
        return userService.findByPostId(postId)
    }

    @QueryMapping
    fun getUsers(@Argument page: Int, @Argument size: Int): List<User> {
        return userService.getUsers(page, size)

    }

    @SchemaMapping(typeName = "Comment")

    fun author(comment: Comment): User {
        return userService.findByCommentId(comment.id)
    }

    @QueryMapping
    fun login(@Argument username: String, @Argument password: String) = JwtUtil.generateJwtToken(
        username=username,
        signedSecret = secretKey,
        roles = listOf(
            "ADMIN"
        )
    )

    companion object {
        val LOGGER: Logger = LoggerFactory.getLogger(this::class.java)
    }

}

data class User(
    val id: UUID?, val name: String
)

data class AddUserInput(
    val name: String
)
