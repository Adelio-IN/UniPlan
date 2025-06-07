import java.util.ArrayList;
import java.util.List;

public class Schedule
{
    static final int maxLectureList = 10;

    static String[][] lecture = new String[maxLectureList][3];

    static ArrayList<String>[] lectureList = new ArrayList[maxLectureList];

    static
    {
        for (int i = 0; i < maxLectureList; i++)
        {
            lecture[i][0] = "";
            lecture[i][1] = "";
            lecture[i][2] = "";
        }
    }
    public static void main(String[] args)
    {
        for (int i = 0; i < maxLectureList; i++)
        {

        }
        System.out.println("강의 목록");
        for (int i = 0; i < maxLectureList; i++)
        {
            System.out.println(lecture[i][0] + " " + lecture[i][1] + " " + lecture[i][2]);
        }
        printAllLectures();
    }
    public static void setLectureList(int index, String name, String day, String time)
    {
        if (index >= 0 || index < maxLectureList)
        {
            lecture[index][0] = name;
            lecture[index][1] = day;
            lecture[index][2] = time;
            System.out.println("일정 설정 완료.");
        }
        else
        {
            System.out.println("잘못된 인덱스입니다.");
        }
    }
    public static String getLectureInfo(int index)
    {
        if (index >= 0)
        {
            return String.format("%s (%s %s)", lecture[index][0], lecture[index][1], lecture[index][2]);
        }
        return "등록되지 않은 강의입니다";
    }

    public static void printAllLectures()
    {
        System.out.println("-----전체 강의 목록-----");
        List<Sort> sortLectureList = new ArrayList<>();
        for (int i = 0; i < maxLectureList; i++)
        {
            String name = lecture[i][0];
            String day = lecture[i][1];
            String time = (lecture[i][2]);

            try
            {
                if (name != null && !name.isEmpty() && day != null && !day.isEmpty() && time != null && !time.isEmpty())
                {
                    sortLectureList.add(new Sort(name, day, time));
                }
            } catch (Exception e)
            {
                System.out.println(name + " 강의의 시간 정보가 올바르지 않아 목록에서 제외됩니다.");
            }
            if (sortLectureList.isEmpty())
            {
                System.out.println("\n--- [전체 강의 목록] ---");
                System.out.println("등록된 강의가 존재하지 않습니다.");
                System.out.println("-------------------------");
                return;
            }
            LectureSort.sort_Day_Time(sortLectureList);

            System.out.println("---강의 목록---");
            System.out.printf("%-25s | %-5s | %s%n", "강의명", "요일", "강의 시간");
            System.out.println("-------------------------------------");
            for (Sort lectureSort : sortLectureList)
            {
                System.out.printf("%-25s | %-5s | %d%n", lectureSort.name, lectureSort.day, lectureSort.time);
            }
        }
        System.out.println();
    }
    public static void printLectureInfo(int index)
    {
        System.out.println("=== " + lectureList[index] + " ===");
        for (String lectureName : lectureList[index])
        {
            System.out.println(lectureName);
        }
        System.out.println("\n등록된 강의 목록 " + getLectureCount(index) + "개 입니다.");

        if (lectureList[index].isEmpty())
        {
            System.out.println("등록된 강의가 없습니다.");
        }
        else
        {
            for (String lectureName : lectureList[index])
            {
                System.out.println(lectureName);
            }
        }
    }
}
