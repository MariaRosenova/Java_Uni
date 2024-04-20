package src.documentStorage;

public class Document {
    private double sizeFile;
    private String format;
    private String title;

    public Document(Author author, String novelAbstract, double fileSize, String format, String title, String ISBN) {
        this.sizeFile = sizeFile;
        this.format = format;
        this.title = title;
    }

    public Document(Author author, double fileSize, String format, String title, String ISBN) {
    }

    public Document() {
    }

    public double getFileSize() {
        return sizeFile;
    }

    public String getFormat() {
        return format;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return "Document{" +
                "sizeOfFile=" + sizeFile +
                ", format='" + format + '\'' +
                ", title='" + title + '\'' +
                '}';
    }
}
