import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Main {
    private static Register.User currentUser = null;
    private static final Login login = new Login();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        SampleData.initialize();
        Alarm.startScheduler();
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

    private static void handleLogin()
    {
        System.out.println("\n--- 로그인 유형 선택 ---");
        System.out.println("1. 교수 로그인");
        System.out.println("2. 학생 로그인");
        System.out.println("3. 이전 메뉴로");
        System.out.print("메뉴 선택: ");

        String choice = sc.nextLine();
        String role = "";

        switch (choice) {
            case "1":
                role = "교수";
                break;
            case "2":
                role = "학생";
                break;
            case "3":
                return;
            default:
                System.out.println("잘못된 입력입니다.");
        }
        currentUser = login.tryLogin(sc, role);
    }

    private static void showSelectMenu() {
        System.out.println("-----UniPlan 메뉴-----");
        System.out.println("1. 시간표 관리");
        System.out.println("2. 알람 관리");
        System.out.println("3. 이벤트/과제 관리");

        String choice = sc.nextLine();
        switch (choice) {
            case "1":
                handleLogin();
                break;
            case "2":
                handleAlarmManagement();
                break;
            case "3":
                handleEventManagement();
                break;
            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
        }
    }

    private static void handleEventManagement()
    {
        while (true)
        {
            System.out.println("--시간표 메뉴--");
            System.out.println("1. 강의 추가");
            System.out.println("2. 강의 목록 확인");
            System.out.println("3. 이전 메뉴로");
            String choice = sc.nextLine();
            if(choice.equals("exit"))
            {
                choice = "4";
            }
            switch (choice)
            {
                case "1":
                    addLecture();
                    break;
                case "2":
                    Schedule.printAllLectures();
                    break;
                case "3":
                    return;
                case "4":
                    
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
        System.out.println("3. 알람 리스트 확인");
        System.out.println("exit");

        String choice = sc.nextLine();
        if (choice.equalsIgnoreCase("exit"))
        {
            choice =  "3";
        }
        switch (choice)
        {
            case "1":
                addAlarm();
                break;
            case "2":
                removeAlarm();
                break;
            case "3":
                Alarm.printAllAlarms();
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

        Alarm.addAlarm(index, setAlarm, message);
    }

    private static void removeAlarm()
    {
        Alarm.printAllAlarms();

        if (Alarm.getAlarmsCount() == 0)
        {
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
            Alarm.removeAlarm(indexToDelete);

        } catch (NumberFormatException e) {
            System.out.println("오류: 숫자를 입력해야 합니다.");
        }
    }
}