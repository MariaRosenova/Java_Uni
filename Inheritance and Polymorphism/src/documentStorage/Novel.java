package src.documentStorage;

public class Novel extends Document {
    private String novelAbstract;

    public Novel(Author author, String novelAbstract, double sizeFile, String format, String title, String ISBN) {
        super(author, sizeFile, format, title, ISBN);
        this.novelAbstract = novelAbstract;
    }

    public String getNovelAbstract() {
        return novelAbstract;
    }

    @Override
    public String toString() {
        return "Novel{" +
                "novelAbstract='" + novelAbstract + '\'' +
                '}';
    }
}
