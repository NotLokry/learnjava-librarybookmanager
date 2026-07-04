package com.lokry.librarybookmanager;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class LibraryTest {

    private Library library;
    private Book existingBook;

    @BeforeEach
    public void setUp() throws InvalidBookException {
        existingBook = new Book("Some Sort of Book", "William Shakespeare", "1234567890123", false);
        ArrayList<Book> books = new ArrayList<>();
        books.add(existingBook);
        library = new Library(books);
    }

    @Test
    public void sanityCheck() {
        assertTrue(true);
    }

    @Test
    public void addBook_returnsFalse_whenAddingNewSimilarBookFails() throws InvalidBookException{
        assertFalse(library.addBook(new Book("b","c", "1234567890123", false)),"Added a duplicate book");
    }
    @Test
    public void addBook_returnsTrue_whenAddingNewBook() throws InvalidBookException{
        assertTrue(library.addBook(new Book("b","c", "1000000000001", false)),"Didn't add a new book");
    }

    @Test
    public void newBook_throwsInvalidBook_whenISBNIsNaN(){
        assertThrows(InvalidBookException.class, () -> new Book("a","b", "helloWorld", false));
    }
    @Test
    public void newBook_throwsInvalidBook_whenISBNIsNot13Digits(){
        assertThrows(InvalidBookException.class, () -> new Book("a","b", "100000000000", false));
    }
    @Test
    public void newBook_throwsInvalidBook_whenTitleIsEmpty(){
        assertThrows(InvalidBookException.class, () -> new Book("","b", "1000000000002", false));
    }
    @Test
    public void newBook_throwsInvalidBook_whenAuthorIsEmpty(){
        assertThrows(InvalidBookException.class, () -> new Book("a","", "1000000000002", false));
    }

    @Test
    public void checkOutBook_throwsBookNotFound_whenBookIsNotInTheList() throws InvalidBookException{
        Book book = new Book("null", "null", "1234567891234", false);
        assertThrows(BookNotFoundException.class,()->library.checkOutBook(book));
    }
    @Test
    public void checkOutBook_returnsTrue_whenBookChecksOutFromFalseToTrue() throws BookNotFoundException{
        assertTrue(library.checkOutBook(existingBook),"Didn't successfully check out the book");
    }

    @Test
    public void returnBook_returnsTrue_whenBookChecksOutFromTrueToFalse() throws BookNotFoundException{
        assertTrue(library.returnBook(existingBook),"Didn't successfully return the book");
    }

    @Test
    public void searchByTitle_returnsTrue_whenInsensitiveSearchQueryResultsWithData() throws BookNotFoundException{
        String title = "SOmE soRt Of bOOk";
        ArrayList<Book> results = library.searchByTitle(title);
        assertEquals(1, results.size());
        assertEquals(existingBook, results.get(0));
    }
    @Test
    public void searchByTitle_throwsBookNotFound_whenNoResultsComeUp(){
        String title = "Something something";
        assertThrows(BookNotFoundException.class, () -> library.searchByTitle(title));
    }

    @Test
    public void searchByAuthor_returnsTrue_whenInsensitiveSearchQueryResultsWithData() throws BookNotFoundException{
        String author = "WiLLiam ShaKESpeare";
        ArrayList<Book> results = library.searchByAuthor(author);
        assertEquals(1, results.size());
        assertEquals(existingBook, results.get(0));
    }
    @Test
    public void searchByAuthor_throwsBookNotFound_whenNoResultsComeUp(){
        String author = "Nonexistent Author";
        assertThrows(BookNotFoundException.class, () -> library.searchByAuthor(author));
    }
}