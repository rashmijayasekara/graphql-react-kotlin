package com.example.demoEight

import com.example.demoEight.model.PostEntity
import com.example.demoEight.model.UserEntity
import com.example.demoEight.repository.CommentRepository
import com.example.demoEight.repository.PostRepository
import com.example.demoEight.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class DemoEightApplication{
	@Bean
	fun runner(
		userRepository: UserRepository,
		postRepository: PostRepository,
		commentRepository: CommentRepository
	): ApplicationRunner {
		return ApplicationRunner{
			val user = UserEntity(
				name = "Test user"
			)
			userRepository.save(user)
			val postEntity = PostEntity(
				title = "Test title",
				description = "Test description",
				author = user
			)
			val postEntity2 = PostEntity(
				title = "Test title 2",
				description = "Test description 2",
				author = user
			)
			val postEntity3 = PostEntity(
				title = "Test title 3",
				description = "Test description 3",
				author = user
			)

			postRepository.saveAll(listOf(postEntity,postEntity2,postEntity3))
		}
	}
}

fun main(args: Array<String>) {
	runApplication<DemoEightApplication>(*args)
}
