import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static Register.User currentUser = null;
    private static final Login login = new Login();
    private static SystemClock clock = new SystemClock();
    private static Schedule schedule = new Schedule();
    private static LectureSort LectureSort = new LectureSort();
    private static Register r = new Register();
    private static UserInput u = new UserInput();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("UniPlan 로딩중...");
        System.out.println("\nUniPlan 로딩 완료!");

        while (true) {
            if (currentUser == null) {
                showLoginMenu();
            } else {
                showLoginMenu();
            }
        }
    }

    private static void showLoginMenu() {
        System.out.println("\n-----UniPlan Main-----");
        System.out.println("1. 로그인");
        System.out.println("2. 회원가입");
        System.out.println("3. exit");

        String choice = sc.nextLine();
        switch (choice) {
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

    private static void handleLogin() {
        currentUser = login.tryLogin(sc);
    }

    private static void showSelectMenu() {
        System.out.println("-----UniPlan 메뉴-----");
        System.out.println("1. 시간표 관리");
        System.out.println("2. 알람");
        System.out.println("3. 이벤트/과제 관리");

        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                handleLogin();
                break;
            case "2":
                Register.processRegister(sc);
                break;
            case "3":
                handleEventManagement();
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
        }
    }

    private static void handleEventManagement() {
        while (true) {
            System.out.println("--시간표 메뉴--");
            System.out.println("1. 강의 추가");
            System.out.println("2. 강의 목록 확인");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    addLecture();
                    break;
                case "2":
                    Schedule.printAllLectures();
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }

    private static void addLecture()
    {
        try
        {
            System.out.println("인덱스를 입력하세요");
            int index = Integer.parseInt(sc.nextLine());

            System.out.println("강의명을 입력하세요");
            String name = sc.nextLine();

            System.out.println("강의 요일을 입력하세요");
            String day = sc.nextLine();

            System.out.print("강의 시간을 입력하세요");
            String time = sc.nextLine();

            Schedule.setLectureList(index, name, day, time);
        }
        catch (NumberFormatException e)
        {
            System.out.println("인덱스와 강의 시간은 숫자로만 입력가능합니다.");
        }
    }

    private static void handleAlarmManagement()
    {
        System.out.println("-----알람 관리 -----");
        System.out.println("1. 알람 추가");
        System.out.println("2. 알람 삭제");

        String choice = sc.nextLine();

        switch (choice)
        {
            case "1":
                addLecture();
                break;
            case "2":
                removeAlarm();
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
        }

    }
    private static void addAlarm()
    {
        Schedule.printAllLectures();
        System.out.println("알람을 설정할 강의의 인덱스를 입력하세요.");
        int index = Integer.parseInt(sc.nextLine());

        if(Schedule.lecture[index][0] == null || Schedule.lecture[index][0].isEmpty())
        {
            System.out.println("해당 인덱스에 등록된 강의가 존재하지 않습니다.");
            return;
        }

        System.out.print("알람 시간을 입력하세요(ex: 2025-05-10 10:00");
        String timeValueStr = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime setAlarm = LocalDateTime.parse(timeValueStr, formatter);

        System.out.print("메시지를 입력하세요.");
        String message = sc.nextLine();

        Alarm.AlarmData.addAlarm(index, setAlarm, message);
    }

    private static void removeAlarm()
    {
        Alarm.AlarmData.printAllAlarms();

        if (Alarm.AlarmData.getAlarmsCount() == 0) {
            return;
        }

        System.out.print("삭제할 알람의 번호를 입력하세요 (취소: 0): ");
        try {
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 0) {
                System.out.println("삭제를 취소했습니다.");
                return;
            }

            int indexToDelete = choice - 1;
            Alarm.AlarmData.removeAlarm(indexToDelete);

        } catch (NumberFormatException e) {
            System.out.println("오류: 숫자를 입력해야 합니다.");
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