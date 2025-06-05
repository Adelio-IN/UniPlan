import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    private static SystemClock clock = new SystemClock();
    private static Schedule schedule = new Schedule();
    private static LectureSort LectureSort = new LectureSort();
    private static Register r = new Register();
    private static UserInput u = new UserInput();
    public static void main(String[] args)
    {
        System.out.println("UniPlan 로딩중...");
        Scanner sc = new Scanner(System.in);
        System.out.println("UniPlan 로딩 완료!");

        System.out.print("강의 수를 입력하세요: ");
        int count = sc.nextInt();
        sc.nextLine();  // 개행 문자 제거

        String[] Lecture_Name = new String[count];
        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + "번째 강의명을 입력하세요: ");
            Lecture_Name[i] = sc.nextLine();
        }

        String[] Lecture_Day = new String[count];
        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + "강의 요일을 입력하세요. Ex: 월, 화: ");
            Lecture_Day[i] = sc.nextLine();
        }

        int[] Lecture_Time = new int[count];
        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + "강의 시간을 입력하세요. Ex: 10, 13: ");
            Lecture_Time[i] = sc.nextInt();
            sc.nextLine();
        }

        List<Sort> Lectures = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Lectures.add(new Sort(Lecture_Name[i], Lecture_Day[i], Lecture_Time[i]));
        }

        LectureSort.sort_Day_Time(Lectures);

        System.out.println("\n강의 목록");
        for (Sort lecture : Lectures) {
            System.out.println(lecture.name + " " + lecture.day + "요일 " + lecture.time + "시");
        }
    }
    private static void loadSampleData()
    {
        System.out.println("---Sample data loading...---");
        Student s1 = new Student("이인", 20241001, "컴퓨터공학과");
        Student s2 = new Student("김길동", 20231002, "생명공학과");
        Student s3 = new Student("박길동", 20211003, "컴퓨터보안학과");

        Professor p1 = new Professor("김수영", 10372, "생명공학과");
        Professor p2 = new Professor("박수영", 10373, "컴퓨터공학과");

        schedule.setLectureList(0, "자바프로그래밍", "월", "10:00");
        schedule.setLectureList(1, "자바프로그래밍", "월", "11:00");
        schedule.setLectureList(2, "자바프로그래밍", "월", "12:00");
    }
}