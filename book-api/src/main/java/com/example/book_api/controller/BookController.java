package com.example.book_api.controller;

import com.example.book_api.model.Book;  // Corrige l'import si nécessaire
import com.example.book_api.service.BookService;  // Corrige l'import si nécessaire
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    // Créer un nouveau livre
    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        Book createdBook = bookService.saveBook(book);  // Utilise la méthode saveBook si nécessaire
        return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
    }

    // Récupérer tous les livres
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // Récupérer un livre par son ISBN
    @GetMapping("/{isbn}")
    public ResponseEntity<Book> getBookByIsbn(@PathVariable String isbn) {
        Book book = bookService.getBookByIsbn(isbn);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Mettre à jour un livre existant
    @PutMapping("/{isbn}")
    public ResponseEntity<Book> updateBook(@PathVariable String isbn, @RequestBody Book book) {
        Book updatedBook = bookService.updateBook(isbn, book);
        if (updatedBook != null) {
            return new ResponseEntity<>(updatedBook, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // Supprimer un livre par son ISBN
    @DeleteMapping("/{isbn}")
    public ResponseEntity<Void> deleteBook(@PathVariable String isbn) {
        bookService.deleteBook(isbn);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
