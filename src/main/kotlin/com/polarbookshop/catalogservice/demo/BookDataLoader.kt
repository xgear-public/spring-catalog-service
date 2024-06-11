package com.polarbookshop.catalogservice.demo

import com.polarbookshop.catalogservice.domain.Book
import com.polarbookshop.catalogservice.domain.BookRepository
import org.springframework.boot.context.event.ApplicationReadyEvent
import org.springframework.context.annotation.Profile
import org.springframework.context.event.EventListener
import org.springframework.stereotype.Component
import java.time.Instant

@Component
@Profile("testdata")
class BookDataLoader(private val bookRepository: BookRepository) {

    @EventListener(ApplicationReadyEvent::class)
    fun loadBooksTestData() {
        bookRepository.deleteAll()

        val book1 = Book(
            isbn = "1234567891",
            title = "Northern Lights",
            author = "Lyra Silverstar",
            price = 9.90,
            createdDate = Instant.now(),
            lastModifiedDate = Instant.now(),
        )
        val book2 = Book(
            isbn = "1234567892",
            title = "Polar Journey",
            author = "Iorek Polarson",
            price = 12.90,
            createdDate = Instant.now(),
            lastModifiedDate = Instant.now(),
        )
        bookRepository.saveAll(listOf(book1, book2))
    }
}