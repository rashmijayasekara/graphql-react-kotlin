package com.example.demoEight.resolver

import com.example.demoEight.service.PostService
import com.example.demoEight.service.UserService
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class PostResolver(
    private val userService: UserService, private val postService: PostService
) {
    @QueryMapping
    fun getPosts(): List<Post> {
        return postService.getPosts()
        //       return listOf(
//            Post(
//                id=UUID.randomUUID(),
//                title="some title",
//                description = "some description"
//            ),
//            Post(
//                id=UUID.randomUUID(),
//                title="some title2",
//                description = "some description2"
//            )


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
        val postId = post.id ?: throw RuntimeException("postId can't be null")
        return userService.findByPostId(postId)
    }

    // Field resolver
    @SchemaMapping(typeName = "User") // Field resolver is always going to be have parent as an argument
    fun posts(user: User): List<Post> {
//        return listOf(
//            Post(
//                id = UUID.randomUUID(),
//                title = "test",
//                description = "des"
//
//            )
//        )

        val userId = user.id ?: throw RuntimeException("UserId can't be null")
        return postService.getPostByAuthor(userId)
    }
}

data class Post(
    val id: UUID?, val title: String, val description: String?
)

data class User(
    val id: UUID?, val name: String
)