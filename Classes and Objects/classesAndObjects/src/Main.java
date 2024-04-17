import boxes.Box;
import video.Author;
import video.Video;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
       /** Box box1 = new Box(BigDecimal.valueOf(10), BigDecimal.valueOf(20), BigDecimal.valueOf(30));
        System.out.println(box1); **/

        Author author = new Author("Maria", 27);

        Video video1 = new Video(1,"First Video", BigDecimal.valueOf(3.6), 10, author, LocalDate.of(2024, 3, 17) );
        Video video2 = new Video(2,"Second Video", BigDecimal.valueOf(3.3), 10, author, LocalDate.of(2024, 3, 16));

        System.out.println(video1);
        System.out.println(video2);


        Video.setMaxDuration(BigDecimal.valueOf(10));
        System.out.println("Max Duration:" + Video.getMaxDuration());
        video1.setDuration(BigDecimal.valueOf(12));

        System.out.println(video1);
        System.out.println(video2);

    }
}