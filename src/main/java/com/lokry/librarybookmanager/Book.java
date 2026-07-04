package com.lokry.librarybookmanager;

import java.io.Serializable;

public class Book implements Serializable{
    private String title = "";
    private String author = "";
    private String ISBN = "0000000000000";
    private boolean isCheckedOut = false;

    public Book(String title,String author,String ISBN,boolean isCheckedOut) throws InvalidBookException{
        try { Long.parseLong(ISBN); } catch (NumberFormatException e) { throw new InvalidBookException("ERROR: ISBN isn't a 13 digit number");}
        if(ISBN.length() != 13)throw new InvalidBookException("ERROR: ISBN isn't 13 digits");
        else if(title.isEmpty())throw new InvalidBookException("ERROR: Title is empty");
        else if(author.isEmpty())throw new InvalidBookException("ERROR: Author is empty");
        this.title = title.trim();
        this.author = author.trim();
        this.ISBN = ISBN;
        this.isCheckedOut = isCheckedOut;
    }

    public String getTitle(){ return this.title; }
    public String getAuthor(){ return this.author; }
    public long getISBN(){ return Long.parseLong(this.ISBN); }
    public boolean getIsCheckedOut(){ return this.isCheckedOut; }
    public boolean setIsCheckedOut(boolean checkedOut){ 
        this.isCheckedOut = checkedOut; return this.isCheckedOut;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        return this.ISBN.equals(((Book) o).ISBN);
    }

    @Override
    public int hashCode() {
        return this.ISBN.hashCode();
    }
}
class InvalidBookException extends Exception {
    public InvalidBookException(String m) {
        super(m);  
    }
}