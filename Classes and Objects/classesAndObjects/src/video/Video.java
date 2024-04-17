package video;

import java.math.BigDecimal;
import java.time.LocalDate;
//Plain old java object

public class Video {
    private final long id;
    private String name;
    private BigDecimal duration;
    private static BigDecimal maxDuration;
    private int likes;
    private Author author;
    private LocalDate uploadDate;

    public Video(long id, String name, BigDecimal duration, int likes, Author author, LocalDate uploadDate) {
        this.id = id;
        this.name = name;
        setDuration(duration);
        this.likes = likes;
        this.author = author;
        this.uploadDate = uploadDate;
    }

    //getters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        if(duration.compareTo(BigDecimal.ZERO) < 0) {
            System.out.println("Invalid duration!");
        } else {
            if (Video.maxDuration.compareTo(duration) < 0) {
                System.out.println("Duration is greater than Max");
            }
            else {
                this.duration = duration;
            }
        }
    }

    public static BigDecimal getMaxDuration() {
        return maxDuration;
    }

    public static void setMaxDuration(BigDecimal maxDuration) {
        Video.maxDuration = maxDuration;
    }
    //bigDecimal we cannot use the basic operations, we have methods instead


    @Override
    public String toString() {
        return "Video{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", duration=" + duration +
                ", likes=" + likes +
                ", author=" + author +
                ", uploadDate=" + uploadDate +
                '}';
    }
}
