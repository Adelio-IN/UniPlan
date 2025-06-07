import java.util.ArrayList;
import java.util.List;
import java.util.Timer;

public class Schedule {
    static final int maxLectureList = 10;

    static String[][] lecture = new String[maxLectureList][3];

    static
    {
        for (int i = 0; i < maxLectureList; i++)
        {
            lecture[i][0] = "";
            lecture[i][1] = "";
            lecture[i][2] = "";
        }
    }
    public static final List<Alarm.AlarmData> alarmList = new ArrayList<>();

    public static void main(String[] args) {
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

    public static void setLectureList(int index, String name, String day, String time) {
        if (index >= 0 && index < maxLectureList) {
            lecture[index][0] = name;
            lecture[index][1] = day;
            lecture[index][2] = time;
            System.out.println("일정 설정 완료.");
        } else {
            System.out.println("잘못된 인덱스입니다.");
        }
    }

    public static String getLectureInfo(int index) {
        if (index >= 0) {
            return String.format("%s (%s %s)", lecture[index][0], lecture[index][1], lecture[index][2]);
        }
        return "등록되지 않은 강의입니다";
    }
    public static int getLectureCount() {
        int count = 0;
        for (int i = 0; i < maxLectureList; i++)
        {
            if (lecture[i][0] != null && !lecture[i][0].isEmpty())
            {
                count++;
            }
        }
        return count;
    }

    public static void printAllLectures() {
        System.out.println("-----전체 강의 목록-----");
        List<Sort> sortLectureList = new ArrayList<>();
        for (int i = 0; i < maxLectureList; i++)
        {
            String name = lecture[i][0];

            if (name != null && !name.isEmpty())
            {
                sortLectureList.add(new Sort(name, lecture[i][1], lecture[i][2]));
            }
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
        System.out.println("-------------------------------------");
        System.out.println(getLectureCount() + "개의 강의가 등록되어 있습니다.");
    }
}
