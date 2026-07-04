# learnjava-librarybookmanager

> [!CAUTION]
> This is not recommended to be used as an actual app since this is me just learning how to use Java, Maven, JUnit5.

## A manager that acts like a digital library
It's primary mission is to help you store books inside a library to help keep in mind books that are of interest, already have checked them out or returned them
This was built using Java 26.0.1, Maven 3.9.16 and JUnit 5.10.2

## Prerequisites
- JDK 21 or higher
- Maven 3.9+
- JUnit 5

## How to execute it?
- Build and execute the Maven project with `mvn compile exec:java`

## How to run test?
- To run tests you will need to execute `mvn test`

## How to use it?
1. When you execute it, you will be prompted with 4 options
- Add new book
- Search for a book
- Full book list
- Quit
2. To add a book select the first option by typing 1 and right after you will be asked to type out the required data about the book
- Type out the books title
- Type out the books author
- Type out the ISBN
- Type out true/false, if you already have checked out the book
- After all of the data you will be prompted with either new book has been added or it already exists
- Returns back to main menu
3. To search for a book select the second option by typing 2 and right after you will be asked to select how you want to search
- If you want to search by the title you will need to pick option 1 and after write the title of the book
   - You will either have the ability to pick the wanted book by selecting its option or be prompted with no books found
- If you want to search by the author you will need to pick option 2 and after write the author of the book
  - You will either have the ability to pick the wanted book by selecting its option or be prompted with no books found
- If you want to return to the main menu you will need to select option 3 by typing 3
- If you have selected a book you will be prompted what would you like to do with the book
  - If you want to remove the book from the library select the first option by typing 1
    - You will be prompted with are you sure and will require a true/false input to either remove it or not
      - If remove you will be sent back to the main menu 
  -  If you want to return/check out the book select the second option by typing 2
  - If you want to return to the main menu select the third option by typing 3
4. To see the whole library you will need to the third option in main menu by typing 3
5. To quit the app you will need to select the fourth option by typing q/Q

## Project structure
- `Book` — represents a single book, validates its own data (title, author, 13-digit ISBN)
- `Library` — holds the collection of books, handles add/remove/checkout/search logic
- `FileManager` — saves and loads the library to `library.txt` using Java serialization
- `Main` — command-line interface, handles all user interaction

## How is data stored?
Serialized library is stored in the local directory inside `library.txt`

The library is saved each time the user quits the program 
and loads whenever the program is launched

## So what did I learn making this
I was primarily learning from w3schools.com and Bro Code's youtube channel, which helped me start from scratch and understand Java as a language

In this project I've got to learn and use data structure ArrayList, which is really an amazing thing to use

I've learned to make custom exceptions, use @Annotations, understand why static is different from a plain method (took like good 5 minutes to understand why can't I use a non static param), how to properly save data using Java's I/O streams, how to use classes in different classes (previously did all in one calculator), of course use the basic things like for,while,switch,if,try and catch, and so on.

I believe I've learned a lot in the past ~3days from almost 0 to the basics and used it well here. I also did this in like 6h maybe.
