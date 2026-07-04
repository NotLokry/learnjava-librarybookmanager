package com.lokry.librarybookmanager;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.stream.Collectors;

public class Library implements Serializable{
    final private ArrayList<Book> bookList;

    public Library(ArrayList<Book> bookList){ 
        if(bookList==null) this.bookList = new ArrayList<>(); 
        else this.bookList = bookList; 
    }

    public boolean addBook(Book book){ 
        if(bookList.contains(book))return false;
        bookList.add(book); return true; 
    }

    public boolean removeBook(Book book){
        if(!bookList.contains(book))return false;
        bookList.remove(bookList.indexOf(book)); return true;
    }
    
    public boolean checkOutBook(Book book) throws BookNotFoundException{
        if(!bookList.contains(book))throw new BookNotFoundException();
        bookList.get(bookList.indexOf(book)).setIsCheckedOut(true); 
        return true;
    }

    public boolean returnBook(Book book) throws BookNotFoundException{
        if(!bookList.contains(book))throw new BookNotFoundException();
        bookList.get(bookList.indexOf(book)).setIsCheckedOut(false); 
        return true;
    }

    public ArrayList<Book> searchByTitle(String title) throws BookNotFoundException {
        ArrayList<Book> filteredBooks = (ArrayList<Book>)bookList.stream()
        .filter(book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
        .collect(Collectors.toCollection(ArrayList::new));
        if(filteredBooks.size() <= 0)throw new BookNotFoundException();
        return filteredBooks;
    }

    public ArrayList<Book> searchByAuthor(String author) throws BookNotFoundException{
        ArrayList<Book> filteredBooks = (ArrayList<Book>)bookList.stream()
        .filter(book -> book.getAuthor().toLowerCase().contains(author.toLowerCase()))
        .collect(Collectors.toCollection(ArrayList::new));
        if(filteredBooks.size() <= 0)throw new BookNotFoundException();
        return filteredBooks;
    }

    public ArrayList<Book> listAll(){ return new ArrayList<>(bookList); }
}

class BookNotFoundException extends Exception {
    public BookNotFoundException() {
        super("This book is not found.");  
    }
}