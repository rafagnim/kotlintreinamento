package com.eduardo.kotlinAPI.component

import com.eduardo.kotlinAPI.controller.request.BookRequest
import com.eduardo.kotlinAPI.entity.Book
import com.eduardo.kotlinAPI.enums.BookStatus
import java.util.Random

class BookComponent {
    companion object {
        fun createActiveBookEntity(
            id: Int = Random().nextInt(1000),
            name: String = "Livro ${Random().nextInt(1000)}",
            author: String = "Autor do Livro ${Random().nextInt(1000)}",
            publishYear: Int = Random().nextInt(1000)
        ): Book {
            return Book(id, name, author, publishYear, BookStatus.ATIVO)
        }

        fun createInactiveBookEntity(
            id: Int = Random().nextInt(1000),
            name: String = "Livro ${Random().nextInt(1000)}",
            author: String = "Autor do Livro ${Random().nextInt(1000)}",
            publishYear: Int = Random().nextInt(1000)
        ): Book {
            return Book(id, name, author, publishYear, BookStatus.INATIVO)
        }

        fun createBookRequest(
            name: String = "Livro ${Random().nextInt(1000)}",
            author: String = "Autor do Livro ${Random().nextInt(1000)}",
            publishYear: Int = Random().nextInt(1000)
        ): BookRequest {
            return BookRequest(name, author, publishYear)
        }
    }
}