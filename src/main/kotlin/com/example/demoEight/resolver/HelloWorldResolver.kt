package com.example.demoEight.resolver

import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.stereotype.Controller
import java.time.LocalDate
import java.time.OffsetDateTime
import java.util.*


@Controller
class HelloWorldResolver {

    @QueryMapping
    fun test(
             @Argument date: LocalDate,
             @Argument bornAt: OffsetDateTime,
             @Argument phoneNumber: String
    // This regex is going to provide the validation after that
    // it's going to be mapped into a String
    ): String {
        return "date : $date, bornAt: $bornAt"
    }

    @QueryMapping // name of this function should match a query. Whenever we are going to fire the helloworld query the corresponding function will return data
    fun helloworld(): String {
        return "Hello World"
    }

    @QueryMapping
    fun greet(@Argument name: String): String {
        return "Hello $name"
    }

    @QueryMapping
    fun getRandomNumbers(): List<Int> {
        return listOf(1, 2, 3)
    }

    @QueryMapping
    fun getEvent(): Event {
        return Event(
            id = UUID.randomUUID(), eventType = "Testing"
        )

    }

}

data class Event(
    val id: UUID, val eventType: String
)