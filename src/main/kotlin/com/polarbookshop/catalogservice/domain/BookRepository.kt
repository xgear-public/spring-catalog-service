package com.polarbookshop.catalogservice.domain

interface BookRepository {
    fun findAll(): Iterable<Book>
    fun findByISBN(isbn: String): Book?
    fun existsByISBN(isbn: String): Boolean
    fun save(book: Book): Book
    fun deleteByISBN(isbn: String)
}