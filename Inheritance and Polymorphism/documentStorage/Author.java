package documentStorage;

public class Author {
    private String title;

    public Author(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "Author{" +
                "title='" + title + '\'' +
                '}';
    }
}
