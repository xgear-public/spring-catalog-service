package com.polarbookshop.catalogservice.domain

import org.springframework.stereotype.Service

@Service
open class BookService(val bookRepository: BookRepository) {

    fun viewBookList() = bookRepository.findAll()

    fun viewBookDetails(isbn: String) = bookRepository.findByISBN(isbn) ?: throw BookNotFoundException(isbn)

    fun addBookToCatalog(book: Book): Book {
        bookRepository.findByISBN(book.isbn)?.let {
            throw BookAlreadyExistsException(book.isbn)
        }
        return bookRepository.save(book)
    }

    fun removeBookFromCatalog(isbn: String) = bookRepository.deleteByISBN(isbn)

    fun editBookDetails(isbn: String, book: Book): Book {
        return bookRepository.findByISBN(isbn)?.let { existingBook ->
            bookRepository.save(
                Book(existingBook.isbn, book.title, book.author, book.price)
            )
        } ?: addBookToCatalog(book)
    }
}

class BookAlreadyExistsException(isbn: String) : Exception("A book with ISBN $isbn already exists.")

class BookNotFoundException(isbn: String) : Exception("The book with ISBN $isbn was not found.")
