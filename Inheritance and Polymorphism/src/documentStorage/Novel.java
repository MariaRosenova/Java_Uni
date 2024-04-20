package src.documentStorage;

public class Novel extends Document {
    private String resume;
    private Author author;


    public Novel(Author author, String resume, float sizeFile, String format, String title) {
        super(sizeFile, format, title);
        this.author = author;
        this.resume = resume;
    }

    public String getResume() {
        return resume;
    }

    public Author getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "resume='" + resume + '\'' +
                ", author=" + author +
                '}';
    }
}
