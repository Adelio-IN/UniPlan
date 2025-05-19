import java.util.*;

public class LectureSort
{
    private static final List<String> Day = Arrays.asList("월", "화", "수", "목", "금", "토", "일");
    
    public static void sort_Day_Time(List<Sort> lecture)
    {
        lecture.sort(Comparator.comparingInt((Sort s) -> Day.indexOf(s.day)).thenComparingInt(s -> s.time));
    }

}
