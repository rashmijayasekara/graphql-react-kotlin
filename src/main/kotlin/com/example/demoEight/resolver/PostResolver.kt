package com.example.demoEight.resolver

import com.example.demoEight.service.PostService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class PostResolver(
    private val postService: PostService
) {
    @QueryMapping
    fun getPosts(): List<Post> {
        LOGGER.info("Fetching posts from the database")
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

    @QueryMapping
    fun recentPosts(@Argument page: Int, @Argument size: Int): List<Post> {
        return postService.getPosts(page, size)
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

    @SchemaMapping(typeName = "User")
    fun totalPost(user: User): Int {
        val userId = user.id ?: throw RuntimeException("User can't be null")
        return postService.getPostByAuthor(userId).size
    }

    @MutationMapping
    fun addPost(@Argument("addPostInput") addPost: AddPost): Post {

        return postService.addPost(addPost)
//       PostEntity(
//            title = addPost.title,
//            description = addPost.description
//        )
    }

    @SchemaMapping(typeName = "Comment")
    fun post(comment: Comment): Post {
        return postService.getPostByCommentId(comment.id)
    }

    companion object{
        val LOGGER: Logger = LoggerFactory.getLogger(this::class.java)
    }

}

data class Post(
    val id: UUID?, val title: String, val description: String?
)

data class AddPost(
    val title: String, val description: String?, val authorId: UUID
)
