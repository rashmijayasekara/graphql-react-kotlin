package com.example.demoEight.resolver

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.stereotype.Controller
import java.util.*

@Controller
class CommentResolver {

    @QueryMapping
    fun getComments(@Argument("page") page: Int, @Argument size: Int): List<Comment> {
        return listOf(
            Comment(
                id=UUID.randomUUID(),
                text="comment text"
            )
        )
    }



}

data class Comment(
    val id: UUID, val text: String
)