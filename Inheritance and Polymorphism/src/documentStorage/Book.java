package src.documentStorage;

public class Book extends Document {
    private Author author;
    private String ISBN;

    public Book (float fileSize, String format, String title, Author author, String ISBN) {
        super(fileSize, format, title);
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
