package src.documentStorage;

public class Book extends Document {
    private Author author;
    private String ISBN;

    public Book() {

    }
    public Book (double fileSize, String format, String title, Author author, String ISBN) {
        super(author, fileSize, format, title, ISBN);
        this.author = author;
        this.ISBN = ISBN;
    }

    public String getISBN() { return ISBN; }

    public Author getAuthor() { return author; }

    @Override
    public String toString() {
        return "Book{" +
                "author=" + author +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
