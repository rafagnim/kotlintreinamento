package com.eduardo.kotlinAPI.service

import com.eduardo.kotlinAPI.controller.request.BookRequest
import com.eduardo.kotlinAPI.entity.Book
import com.eduardo.kotlinAPI.enums.BookStatus.ATIVO
import com.eduardo.kotlinAPI.enums.BookStatus.INATIVO
import com.eduardo.kotlinAPI.mapper.BookMapper
import com.eduardo.kotlinAPI.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository,
    private val bookMapper: BookMapper
) {

    fun retrieveAllBooks(): List<Book> = bookRepository.findAll()

    fun insertBook(request: BookRequest) {
        bookRepository.save(bookMapper.toEntity(request))
    }

    fun activateBook(id: Int): Book {
        val book = bookRepository.findById(id).orElseThrow()
        book.let { content ->
            when (content.status) {
                ATIVO -> content.status = INATIVO
                INATIVO -> content.status = ATIVO
            }
        }
        return bookRepository.save(book)
    }
}