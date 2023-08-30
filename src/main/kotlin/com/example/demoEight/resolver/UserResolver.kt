package com.example.demoEight.resolver

import com.example.demoEight.service.UserService
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class UserResolver (private val userService: UserService){
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
        val postId = post.id ?: throw RuntimeException("postId can't be null")
        return userService.findByPostId(postId)
    }

    @QueryMapping
    fun getUsers(@Argument page:Int,@Argument size: Int):List<User>{
        return userService.getUsers(page,size)

    }

    @SchemaMapping(typeName = "Comment")

    fun author(comment: Comment):User{
        return userService.findByCommentId(comment.id)
    }

}
data class User(
    val id: UUID?, val name: String
)

data class AddUserInput(
    val name: String
)
