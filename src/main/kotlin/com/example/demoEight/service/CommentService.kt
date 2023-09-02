package com.example.demoEight.service

import com.example.demoEight.model.CommentEntity
import com.example.demoEight.repository.CommentRepository
import com.example.demoEight.repository.PostRepository
import com.example.demoEight.repository.UserRepository
import com.example.demoEight.resolver.AddCommentDto
import com.example.demoEight.resolver.Comment
import com.example.demoEight.resolver.Post
import org.springframework.data.domain.PageRequest
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.MutationMapping
import org.springframework.stereotype.Service
import java.util.*

@Service
class CommentService(
    private val commentRepository: CommentRepository,
    private val userRepository: UserRepository,
    private val postRepository: PostRepository
) {
    fun getComments(page: Int, size: Int): List<Comment> {
        val pageRequest = PageRequest.of(page, size)
        val commentEntities = commentRepository.findAll(pageRequest)

        return commentEntities.map {
            Comment(
                id = it.id, text = it.text
            )
        }.toList()
    }

    fun getCommentsByPostId(postId: UUID?): List<Comment> {
        postId ?: throw RuntimeException("postId can't be null")
        return commentRepository.findAllByPostId(postId).map {
            Comment(
                id = it.id, text = it.text
            )
        }.toList()


    }

    fun getCommentsByUserId(userId: UUID?): List<Comment> {
        userId ?: throw RuntimeException("User Id can't be null")
        return commentRepository.findAllByAuthorId(userId).map {
            Comment(
                id = it.id, text = it.text
            )
        }.toList()
    }

    fun addComment(addComment: AddCommentDto):Comment {
        val user=userRepository.findById(addComment.authorId).orElseThrow{RuntimeException("User doesn't exist with id : ${addComment.authorId}")}
        val post=user.posts.first{it.id==addComment.postId}?:throw RuntimeException("Post does not exist with id : ${addComment.postId}")

        val comment=CommentEntity(
            text= addComment.text,
            author = user,
            post = post
        )

        val createdComment=commentRepository.save(comment)
        return Comment(
            id = createdComment.id,
            text=createdComment.text
        )
    }

    fun getCommentsByPosts(posts: List<Post>): Map<Post, List<Comment>> {
        val comments = commentRepository.findAllByPostIds(posts.mapNotNull { it.id }.toList())

        return posts.associateWith { post ->
            comments.filter { comment -> comment.post.id == post.id }
                .map { comment -> Comment(id = comment.id, text = comment.text) }.toList()

        }
    }


}

data class Comment(
    val id: UUID?,
    val text: String
)


