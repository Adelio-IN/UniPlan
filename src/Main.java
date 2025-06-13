import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main
{
    private static User currentUser = null;
    private static final Login login = new Login();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args)
    {
        SampleData.initialize();
        Alarm.startScheduler();
        System.out.println("UniPlan 로딩중...");
        System.out.println("\nUniPlan 로딩 완료!");

        while (true)
        {
            if (currentUser == null)
            {
                showLoginMenu();
            }
            else
            {
                showSelectMenu();
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
        switch (choice)
        {
            case "1":
                handleLogin();
                break;
            case "2":
                Register.processRegister(sc);
                break;
            case "3":
                System.out.println("사용자의 exit 입력으로 프로그램이 종료됩니다.");
                Alarm.stopScheduler();
                sc.close();
                System.exit(0);
            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
        }
    }

    private static void handleLogin()
    {
        User user = login.tryLogin(sc);

        if (user != null)
        {
            System.out.println(user.getName() + " 님 로그인에 성공하셨습니다");
            currentUser = user;
        }
        else
        {
            System.out.println("로그인에 실패하였습니다.");
        }
    }

    private static void showSelectMenu()
    {
        System.out.println("-----UniPlan 메뉴-----");
        System.out.println("1. 시간표 관리");
        System.out.println("2. 알람 관리");
        System.out.println("3. 과제 관리");
        System.out.println("4. 로그아웃");
        System.out.println("5. exit");

        String choice = sc.nextLine();
        switch (choice)
        {
            case "1":
                handleEventManagement();
                break;
            case "2":
                handleAlarmManagement();
                break;
            case "3":
                handleEventAssignmentManagement();
                break;
            case "4":
                System.out.println("로그아웃에 성공하셨습니다");
                currentUser = null;
                return;
            case "5":
                System.out.println("사용자의 exit 입력으로 프로그램이 종료됩니다.");
                Alarm.stopScheduler();
                sc.close();
                System.exit(0);
            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
        }
    }
    private static void handleEventAssignmentManagement()
    {
        System.out.println("---과제 관리 메뉴---");
        System.out.println("1. 과제 등록");
        System.out.println("2. 과제 삭제");
        System.out.println("3. 과제 목록 확인");
        System.out.println("4. 이전 메뉴로");
        System.out.println("5. exit");

        String choice = sc.nextLine();
        switch(choice)
        {
            case "1":
                if (currentUser.getRole() == UserType.Professor)
                {
                    addEvent();
                } else {
                    System.out.println("⚠️ 권한이 없습니다. 교수만 과제/이벤트를 등록할 수 있습니다.");
                }
                break;
            case "2":
                if (currentUser.getRole() == UserType.Professor)
                {
                    removeEvent();
                } else {
                    System.out.println("⚠️ 권한이 없습니다. 교수만 과제/이벤트를 삭제할 수 있습니다.");
                }
                break;
            case "3":
                printAllEvents();
                break;
            case "4":
                return;
            case "5":
                System.out.println("사용자의 exit 입력으로 프로그램이 종료됩니다.");
                Alarm.stopScheduler();
                sc.close();
                System.exit(0);
            default:
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
        }
    }

    private static void addEvent()
    {
        try
        {
            System.out.println("\n--- 과제/이벤트 등록 ---");
            Schedule.printAllLectures();
            if (Schedule.getLectureCount() == 0) {
                System.out.println("⚠️ 먼저 강의를 등록해야 합니다.");
                return;
            }

            System.out.print("이벤트를 등록할 강의의 인덱스를 입력하세요: ");
            int lectureIndex = Integer.parseInt(sc.nextLine());

            if (lectureIndex < 0 || lectureIndex >= 10 || Schedule.getLectureInfo(lectureIndex).contains("등록되지 않은 강의"))
            {
                System.out.println("⚠️ 잘못된 인덱스이거나 등록된 강의가 없습니다.");
                return;
            }

            System.out.print("등록할 과제/이벤트 명을 입력하세요: ");
            String eventName = sc.nextLine();

            System.out.print("설명을 입력하세요: ");
            String description = sc.nextLine();

            System.out.print("마감 또는 이벤트 날짜와 시간을 입력하세요 (예: 2025-10-25 23:59): ");
            String dateTimeStr = sc.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime eventDateTime = LocalDateTime.parse(dateTimeStr, formatter);


            EventSchedule newEvent = new EventSchedule(lectureIndex, eventName, eventDateTime, description);
            EventSchedule.eventList.add(newEvent);

            System.out.println("등록 완료!");

        } catch (NumberFormatException e)
        {
            System.out.println("인덱스는 숫자로 입력해야 합니다.");
        } catch
        (DateTimeParseException e)
        {
            System.out.println("날짜 형식이 잘못되었습니다. 'yyyy-MM-dd HH:mm' 형식으로 입력해주세요.");
        }
    }
    private static void removeEvent()
    {
        System.out.println("\n--- 과제/이벤트 삭제 ---");
        printAllEvents();

        if (EventSchedule.eventList.isEmpty())
        {
            return;
        }

        try
        {
            System.out.print("삭제할 항목의 번호를 입력하세요 (취소: 0): ");
            int choice = Integer.parseInt(sc.nextLine());

            if (choice == 0)
            {
                System.out.println("삭제를 취소했습니다.");
                return;
            }

            int indexToDelete = choice - 1;

            if (indexToDelete >= 0 && indexToDelete < EventSchedule.eventList.size())
            {
                EventSchedule removedEvent = EventSchedule.eventList.remove(indexToDelete);
                System.out.println("다음 항목이 삭제되었습니다: " + removedEvent.getEventName());
            }
            else
            {
                System.out.println("목록에 있는 번호를 입력하세요");
            }
        }
        catch (NumberFormatException e)
        {
            System.out.println("숫자로 입력하세요");
        }
    }

    private static void printAllEvents()
    {
        System.out.println("-- 전체 과제 목록 ---");
        if (EventSchedule.eventList.isEmpty())
        {
            System.out.println("등록된 과제가 없습니다.");
        }
        else
        {
            for (int i = 0; i < EventSchedule.eventList.size(); i++)
            {
                System.out.println((i + 1) + ". " + EventSchedule.eventList.get(i).toString());
            }
        }
        System.out.println("------------------------------");
    }
    private static void handleEventManagement()
    {
        while (true)
        {
            System.out.println("--시간표 메뉴--");
            System.out.println("1. 강의 추가");
            System.out.println("2. 강의 목록 확인");
            System.out.println("3. 이전 메뉴로");
            System.out.println("4. exit");
            String choice = sc.nextLine();

            switch (choice)
            {
                case "1":
                    if(currentUser.getRole() == UserType.Professor)
                    {
                        addLecture();
                    }
                    else
                    {
                        System.out.println("학생은 알람을 추가할 수 없습니다.");
                    }
                    break;
                case "2":
                    Schedule.printAllLectures();
                    break;
                case "3":
                    return;
                case "4":
                    System.out.println("사용자의 exit 입력으로 프로그램이 종료됩니다.");
                    Alarm.stopScheduler();
                    sc.close();
                    System.exit(0);
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

            String professorId = (currentUser.getRole() == UserType.Professor) ? currentUser.getName() : "";
            Schedule.setLectureList(index, name, day, time);
        }
        catch (NumberFormatException e)
        {
            System.out.println("인덱스와 강의 시간은 숫자로만 입력가능합니다.");
        }
    }

    private static void handleAlarmManagement()
    {
        while(true)
        {
            System.out.println("-----알람 관리 -----");
            System.out.println("1. 알람 추가");
            System.out.println("2. 알람 삭제");
            System.out.println("3. 알람 리스트 확인");
            System.out.println("4. 이전 메뉴로");
            System.out.println("5. exit");

            String choice = sc.nextLine();
            if (choice.equalsIgnoreCase("exit"))
            {
                choice =  "5";
            }
            switch (choice)
            {
                case "1":
                    if(currentUser.getRole() == UserType.Professor)
                    {
                        addAlarm();
                    }
                    else
                    {
                        System.out.println("학생은 알람을 추가할 수 없습니다.");
                    }
                    break;
                case "2":
                    if(currentUser.getRole() == UserType.Professor)
                    {
                        removeAlarm();
                    }
                    else
                    {
                        System.out.println("학생은 알람을 삭제할 수 없습니다.");
                    }
                    break;
                case "3":
                    Alarm.printAllAlarms();
                    break;
                case "4":
                    return;
                case "5":
                    System.out.println("사용자의 exit 입력으로 프로그램이 종료됩니다.");
                    Alarm.stopScheduler();
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("잘못된 입력입니다. 다시 시도하세요.");
            }
        }
    }
    private static void myLectureView()
    {
        System.out.println(currentUser.getName() + " 님의 강의 목록");
        boolean found = false;
        String userId = currentUser.getId();

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

        System.out.print("알람 시간을 입력하세요(ex: 2025-05-10 10:00)");
        String timeValueStr = sc.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
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