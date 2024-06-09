package com.polarbookshop.catalogservice.web

import com.polarbookshop.catalogservice.domain.Book
import com.polarbookshop.catalogservice.domain.BookService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("books")
class BookController(val booService: BookService) {

    @GetMapping
    fun get() = booService.viewBookList()

    @GetMapping("{isbn}")
    fun getByIsbn(@PathVariable isbn: String): Book = booService.viewBookDetails(isbn)

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun post(@Valid @RequestBody book: Book) = booService.addBookToCatalog(book)

    @DeleteMapping("{isbn}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun delete(@PathVariable isbn: String) = booService.removeBookFromCatalog(isbn)

    @PutMapping("{isbn}")
    fun put(@PathVariable isbn: String, @Valid @RequestBody book: Book): Book = booService.editBookDetails(isbn, book)

}