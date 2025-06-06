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
            lecture[i] = new ArrayList<>().toArray(new String[0]);
        }
        System.out.println("Schedule Lecture List 초기화 완료" + maxLectureList);
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
            System.out.printf("일정 설정 완료.", index, name, day, time);
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
            return lecture[index][0] + " " + lecture[index][1] + " " + lecture[index][2];
        }
        return "등록되지 않은 강의입니다";
    }
    public static void addLecture(int index, String lectureName)
    {
        if(index >= 0 && index < maxLectureList)
        {
            lectureList[index].add(lectureName);
        }
        else
        {
            System.out.println("유효하지 않습니다.");
        }
    }
    public static int getLectureCount(int index)
    {
        if (index >= 0 && index < maxLectureList)
        {
            return lectureList[index].size();
        }
        return 0;
    }

    public static void loadSampleData()
    {
        System.out.println("\n샘플 데이터를 로드합니다...");
        setLectureList(0, "자바 프로그래밍", "월", "10:00");
        setLectureList(1, "웹 프로그래밍 기초", "화", "13:30");
        setLectureList(3, "데이터베이스", "목", "15:00");
        setLectureList(9, "컴퓨터 구조", "금", "09:00");
        System.out.println("샘플 데이터 로드 완료.");
    }

    public static void printAllLectures()
    {
        List<Sort> sortLectureList = new ArrayList<>();
        for (int i = 0; i < maxLectureList; i++)
        {
            String name = lecture[i][0];
            try
            {
                String day = lecture[i][1];
                int time = Integer.parseInt(lecture[i][2]);
                sortLectureList.add(new Sort(name, day, time));
            } catch (NumberFormatException |
                     NullPointerException e) // 올바른 숫자 양식이 아닐 경우, null 값을 가지는 참조 변수에 접근 -> 메서드 호출 or 필드 사용을 동시에 함
            {
                System.out.println(name + " 강의의 시간 정보가 올바르지 않아 목록에서 제외됩니다.");
            }
            if (sortLectureList.isEmpty()) {
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
