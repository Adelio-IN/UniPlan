import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main
{
    private static Register.User currentUser = null;
    private static final Login login = new Login();
    private static SystemClock clock = new SystemClock();
    private static Schedule schedule = new Schedule();
    private static LectureSort LectureSort = new LectureSort();
    private static Register r = new Register();
    private static UserInput u = new UserInput();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        System.out.println("UniPlan 로딩중...");
        System.out.println("\nUniPlan 로딩 완료!");

        while(true)
        {
            if(currentUser == null)
            {
                showLoginMenu();
            }
            else
            {
                showLoginMenu();
            }
        }
    }
    private static void showLoginMenu()
    {
        System.out.println("\n-----UniPlan Main-----");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("3. exit");

        String choice = sc.nextLine();
        switch(choice)
        {
            case "1":
                handleLogin();
                break;
            case "2":
                Register.processRegister(sc);
                break;
            case "3":
                System.out.println("사용자의 exit 입력으로 프로그램이 종료됩니다.");
                sc.close();
                System.exit(0);
            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
        }
    }

    private static void handleLogin()
    {
        currentUser = login.tryLogin(sc);
    }
    private static void showSelectMenu()
    {
        System.out.println("-----UniPlan 메뉴-----");
        System.out.println("1. 시간표 관리");
        System.out.println("2. 알람");
        System.out.println("3. 이벤트/과제 관리");

        String choice = sc.nextLine();
        switch (choice)
        {
            case "1":
                handleLogin();
                break;
            case "2":
                Register.processRegister(sc);
                break;

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