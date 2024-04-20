package src.documentStorage;

public class Newspaper extends Document {
    private int periodicity;
    private int countOfArticles;
    //float sizeFile, String format, String title
   public Newspaper(float sizeFile, String format, String title, int periodicity, int countOfArticles) {
       super(sizeFile, format, title);
       this.periodicity = periodicity;
       this.countOfArticles = countOfArticles;
   }

    public int getPeriodicity() {
        return periodicity;
    }

    public int getCountOfArticles() {
        return countOfArticles;
    }

    @Override
    public String toString() {
        return "Newspaper{" +
                "periodicity=" + periodicity +
                ", countOfArticles=" + countOfArticles +
                '}';
    }
}
