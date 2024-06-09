package com.polarbookshop.catalogservice.persistance

import com.polarbookshop.catalogservice.domain.Book
import com.polarbookshop.catalogservice.domain.BookRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.ConcurrentHashMap

@Repository
class InMemoryBooksRepository : BookRepository  {

    override fun findAll(): Iterable<Book> = books.values

    override fun findByISBN(isbn: String): Book? = books[isbn]

    override fun existsByISBN(isbn: String): Boolean = books.contains(isbn)

    override fun save(book: Book): Book {
        books[book.isbn] = book
        return book
    }

    override fun deleteByISBN(isbn: String) {
        books.remove(isbn)
    }

    companion object {
        val books = ConcurrentHashMap<String, Book>()
    }
}