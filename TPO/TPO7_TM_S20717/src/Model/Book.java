package Model;

public class Book {
    private int IdBook;
    private String BookName;
    private String Author;
    private double Price;

    public Book(int idBook, String bookName, String author, double price) {
        IdBook = idBook;
        BookName = bookName;
        Author = author;
        Price = price;
    }

    public int getIdBook() {
        return IdBook;
    }

    public String getBookName() {
        return BookName;
    }

    public String getAuthor() {
        return Author;
    }

    public double getPrice() {
        return Price;
    }
}
