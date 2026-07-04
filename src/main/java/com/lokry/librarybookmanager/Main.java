package com.lokry.librarybookmanager;

import java.util.ArrayList;
import java.util.Scanner;

public class Main{
    private Library library;
    private final Scanner scanner = new Scanner(System.in); 

    private String getInputStr() {
        if (scanner.hasNextLine()) {
            return scanner.nextLine();
        }
        return "";
    }

    private int getInputInt(){
        if(scanner.hasNextInt()){
            int inputText = scanner.nextInt();
            scanner.nextLine();
            return inputText;
        }
        scanner.nextLine();
        return -1;
    }

    private boolean getInputBool(){
        if (scanner.hasNextBoolean()) {
            boolean inputText = scanner.nextBoolean();
            scanner.nextLine();
            return inputText;
        }
        scanner.nextLine();
        return false;
    }

    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args){
        Main mainJ = new Main();
        FileManager fm = new FileManager();
        mainJ.library = fm.loadLibrary();

        while(true){
            System.out.println("Pick from the options (1,2,3,q)");
            System.out.println("1. Add new book");
            System.out.println("2. Search for a book");
            System.out.println("3. Full book list");
            System.out.println("q. Quit");
            String selectedOption = mainJ.getInputStr();

            switch (selectedOption) {
                case "1" -> {mainJ.addBook();break;}
                case "2" -> {mainJ.searchBook();break;}
                case "3" -> {mainJ.bookList();break;}
                case "q","Q" -> {
                    fm.saveLibrary(mainJ.library);
                    mainJ.scanner.close();
                    return;
                }

            }
        }
    }

    private void removeBook(Book book){
        System.out.print("Are you sure about this? (true/false): ");
        boolean validated = getInputBool();
        
        if(validated){
            boolean status = library.removeBook(book);
            if(status)System.out.println("Book has been removed from the library");
            else System.out.println("Book was already removed");
        } else {
            System.out.println("Book hasn't been removed");
            selectedBook(book);
        }
    }

    private void setIsBookCheckedOut(Book book){
        try {
            boolean newStatus = !book.getIsCheckedOut();
            if (newStatus) {
                library.checkOutBook(book);
            } else {
                library.returnBook(book);
            }
            String action = newStatus ? "Book is now checked out" : "Book is now returned";
            System.out.println(action);
        } catch (BookNotFoundException e) {
            System.err.println(e.getMessage());
        }
        selectedBook(book);
    }

    private void addBook(){
        String title = "";
        String author = "";
        String ISBN;
        boolean isCheckedOut;
        do{
            System.out.print("What is the title of the book?: "); title = getInputStr();
        }while(title.isEmpty()||title.isBlank());

        do{
            System.out.print("What is the author of the book?: "); author = getInputStr();
        }while(author.isEmpty()||author.isBlank());

        do{
            System.out.print("What is the ISBN of the book?: "); ISBN = getInputStr();
        }while(ISBN.length()!=13 || !ISBN.matches("\\d+"));

        System.out.print("Do you already have it checked out? (true/false): ");
        isCheckedOut = getInputBool();

        Book newBook;
        try {
            newBook = new Book(title, author, ISBN, isCheckedOut);
            boolean status = library.addBook(newBook);
            if(status)System.out.println("New book has been added");
            else System.out.println("This book already exists");
        } catch (InvalidBookException e) {
            System.err.println(e.getMessage());
        }
        
    }

    private void searchBook(){
        int optionType = -1;
        int selectedI = -1;
        String searchQuery = "";
        ArrayList<Book> bookList = new ArrayList<>();

        while(bookList.isEmpty()) { 
            do { 
                System.out.println("How would you like to search?");
                System.out.println("1. Search by title");
                System.out.println("2. Search by author");
                System.out.println("3. Return to main menu");
                optionType = getInputInt();
            } while (optionType<1||optionType>3);
            if(optionType==3)return;
            do { 
                switch (optionType) {
                    case 1 -> System.out.print("What is the title: ");
                    case 2 -> System.out.print("Who is the author: ");
                }
                searchQuery = getInputStr();
            } while (searchQuery.isEmpty());

            switch (optionType) {
                case 1 -> {
                    try  {
                        bookList = library.searchByTitle(searchQuery);
                    } catch (BookNotFoundException e){
                        System.err.println(e.getMessage());
                    }
                }
                case 2 -> {
                    try  {
                        bookList = library.searchByAuthor(searchQuery);
                    } catch (BookNotFoundException e){
                        System.err.println(e.getMessage());
                    }
                }
            }
            if(bookList.isEmpty())System.out.println("\nNo books found under that search.");
        }
        
        for(int i = 0; i<bookList.size(); i++){
            Book book = bookList.get(i);
            System.out.println((i+1)+". "+book.getTitle()+" by "+book.getAuthor());
        }
        do { 
            System.out.print("Select which book would you like: ");
            selectedI = getInputInt();
        } while (selectedI<1||selectedI>bookList.size());

        selectedBook(bookList.get(selectedI-1));
        
    }

    private void selectedBook(Book book){
        int optionType;
        do { 
            System.out.println("What would you like to do with the book?");
            System.out.println("1. Remove from library");
            String action = book.getIsCheckedOut() ? 
            "2. Return the book" : "2. Check out the book";
            System.out.println(action);
            System.out.println("3. Return to main menu");
            optionType = getInputInt();
        } while (optionType<1||optionType>3);

        switch (optionType) {
            case 1 -> removeBook(book);
            case 2 -> setIsBookCheckedOut(book);
            case 3 -> { break; }
        }
    }

    private void bookList(){
        for(int i = 0; i<library.listAll().size(); i++){
            Book book = library.listAll().get(i);
            System.out.println((i+1)+". "+book.getTitle()+" by "+book.getAuthor());
        }
        System.out.println("");
    }
}