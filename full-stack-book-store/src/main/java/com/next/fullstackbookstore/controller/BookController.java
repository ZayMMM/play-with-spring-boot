package com.next.fullstackbookstore.controller;

import com.next.fullstackbookstore.Exception.ResourceNotFoundException;
import com.next.fullstackbookstore.model.Book;
import com.next.fullstackbookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @CrossOrigin
    @GetMapping(value = "/books", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Book> getBook(){
        return bookRepository.findAll();
    }

    @PostMapping("/books")
    public Book createBook(@RequestBody Book book){
        return bookRepository.save( book );
    }

    @CrossOrigin
    @GetMapping(value = "/books/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Book getBookById(@PathVariable(value = "id") Long bookId){
        return bookRepository.getOne( bookId );
        /*return  bookRepository.findById( bookId )
                    .orElseThrow(() -> new ResourceNotFoundException( "Book", "id", bookId ) );*/
    }

    @PutMapping("/book/{id}")
    public Book updateBook(@PathVariable(value = "id") long bookId, @RequestBody Book bookDetail){
        Book book = bookRepository.findById( bookId )
                    .orElseThrow( () -> new ResourceNotFoundException( "Book", "id", bookId ) );

        book.setSurname( bookDetail.getSurname() );
        book.setFirst_name( bookDetail.getFirst_name() );
        book.setTitle( bookDetail.getTitle() );
        book.setPrice( bookDetail.getPrice() );
        book.setOn_sale( bookDetail.getOn_sale() );
        book.setCalendar_year( bookDetail.getCalendar_year() );
        book.setDescription( bookDetail.getDescription() );
        book.setInventory( bookDetail.getInventory() );

        Book updateBook = bookRepository.save( book );

        return updateBook;
    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook( @PathVariable(value = "id") long bookId){
        Book book = bookRepository.findById( bookId )
                        .orElseThrow( () -> new ResourceNotFoundException( "Book", "id", bookId ) );

        bookRepository.delete( book );
        return ResponseEntity.ok(  ).build();
    }
}

