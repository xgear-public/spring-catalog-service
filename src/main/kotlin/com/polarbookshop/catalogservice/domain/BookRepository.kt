package com.polarbookshop.catalogservice.domain

import org.springframework.data.jdbc.repository.query.Modifying
import org.springframework.data.jdbc.repository.query.Query
import org.springframework.data.repository.CrudRepository
import org.springframework.transaction.annotation.Transactional

interface BookRepository: CrudRepository<Book, Long> {
    @Query("select b from Book b where b.isbn = :isbn")
    fun findByISBN(isbn: String): Book?

//    fun existsByISBN(isbn: String): Boolean

    @Modifying
    @Transactional
    @Query("delete from Book where isbn = :isbn")
    fun deleteByISBN(isbn: String)
}