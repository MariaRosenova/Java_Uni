package src.documentStorage;

public class Document {
    private float sizeFile;
    private String format;
    private String title;

    public Document(float sizeFile, String format, String title) {
        this.sizeFile = sizeFile;
        this.format = format;
        this.title = title;
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
