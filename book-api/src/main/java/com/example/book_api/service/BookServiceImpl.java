package com.example.book_api.service;

import com.example.book_api.model.Book;
import com.example.book_api.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;


import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    @Cacheable(value = "books")
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    @Cacheable(value = "book", key = "#isbn")
    public Book getBookByIsbn(String isbn) {
        return bookRepository.findById(isbn).orElse(null);
    }

    @Override
    @CachePut(value = "book", key = "#book.isbn")
    @CacheEvict(value = "books", allEntries = true)
    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    @Override
    @CachePut(value = "book", key = "#isbn")
    @CacheEvict(value = "books", allEntries = true)
    public Book updateBook(String isbn, Book updatedBook) {
        updatedBook.setIsbn(isbn);
        return bookRepository.save(updatedBook);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "book", key = "#isbn"),
            @CacheEvict(value = "books", allEntries = true)
    })
    public void deleteBook(String isbn) {
        bookRepository.deleteById(isbn);
    }
}
