import java.util.*;

public class LectureSort
{
    private static final List<String> Day = Arrays.asList("월", "화", "수", "목", "금", "토", "일");
    private static final int Invalid_Time_Sort = Integer.MAX_VALUE;

    private static int timeConversion(String time)
    {
        try
        {
            return Integer.parseInt(time.replaceAll(":", ""));

        }
        catch(NumberFormatException e)
        {
            return Invalid_Time_Sort;
        }
    }
    public static void sortByDayAndTime(List<Sort> lecture)
    {
        lecture.sort(Comparator.comparingInt((Sort s) -> Day.indexOf(s.day)).thenComparingInt(s ->timeConversion(s.time)));
    }
}
