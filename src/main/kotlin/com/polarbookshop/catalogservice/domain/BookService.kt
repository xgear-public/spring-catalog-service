package com.polarbookshop.catalogservice.domain

import org.springframework.stereotype.Service
import java.time.Instant

@Service
class BookService(val bookRepository: BookRepository) {

    fun viewBookList(): Iterable<Book> = bookRepository.findAll()

    fun viewBookDetails(isbn: String) = bookRepository.findByIsbn(isbn) ?: throw BookNotFoundException(isbn)

    fun addBookToCatalog(book: Book): Book {
        bookRepository.findByIsbn(book.isbn)?.let {
            throw BookAlreadyExistsException(book.isbn)
        }
        return bookRepository.save(book.copy(createdDate = Instant.now(), lastModifiedDate = Instant.now()))
    }

    fun removeBookFromCatalog(isbn: String) = bookRepository.deleteByISBN(isbn)

    fun editBookDetails(isbn: String, book: Book): Book {
        return bookRepository.findByIsbn(isbn)?.let { existingBook ->
            bookRepository.save(
                Book(
                    id = existingBook.id,
                    isbn = existingBook.isbn,
                    title = book.title,
                    author = book.author,
                    price = book.price,
                    createdDate = existingBook.createdDate,
                    lastModifiedDate = existingBook.lastModifiedDate,
                    version = existingBook.version
                )
            )
        } ?: addBookToCatalog(book)
    }
}

class BookAlreadyExistsException(isbn: String) : Exception("A book with ISBN $isbn already exists.")

class BookNotFoundException(isbn: String) : Exception("The book with ISBN $isbn was not found.")
