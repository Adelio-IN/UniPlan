import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Register
{
    public static List<User> userList = new ArrayList<>();

    static class User
    {
        private String name;
        private String id;
        private String password;
        private UserType role;
        private String classNumber;

        public User(String name, UserType role, String classNumber,String id, String password)
        {
            this.name = name;
            this.classNumber = classNumber;
            this.id = id;
            this.password = password;
            this.role = role;
        }
        public String getName()
        {
            return name;
        }
        public UserType getRole()
        {
            return role;
        }
        public String getClassNumber()
        {
        return classNumber;
        }

        public String getId()
        {
            return id;
        }

        public String getPassword()
        {
            return password;
        }
    }

    public static boolean isValidAlphaNumeric(String input)
    {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches("^[a-zA-Z0-9]*$");
    }

    public static boolean isValidIdFormat(String input) {
        if (input == null || input.isEmpty()) {
            return false;
        }
        return input.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    public static boolean isIdTaken(String id)
    {
        {
            for (User user : userList)
            {
                if (user.getId().equals(id))
                {
                    return true;
                }
            }
        }
        return false;
    }
    public static boolean isValidName(String input)
    {
        if (input == null || input.isEmpty())
        {
            return false;
        }
        return input.matches("^[a-zA-Z가-힣]*$");
    }

    public static String getClassNumberInput(Scanner sc)
    {
        String input;
        input = sc.nextLine();
        while(true)
        {
            if(input.equals("exit"))
            {
                return "exit";
            }
            if (input.isEmpty())
            {
                System.out.println("학번/교번을 입력하세요");
                continue;
            }
            if (isValidIdFormat(input))
            {
                break;
            }
        }
        return input;
    }
    public static String getValidNameInput(Scanner sc)
    {
        String input;
        while(true)
        {
            System.out.print("사용자 이름을 입력하세요. 한글/영문으로 입력가능하며 중단하려면 'exit'를 입력하세요.");
            input = sc.nextLine();
            if (input.equalsIgnoreCase("exit"))
            {
                return "exit";
            }
            if (input.isEmpty())
            {
                System.out.println("이름은 비워둘 수 없습니다.");
                continue;
            }
            if (isValidName(input))
            {
                break;
            }
            else
            {
                System.out.println("한글과 영문 대/소문자만 입력할 수 있습니다.");
            }
        }
        return input;
    }

    public static String getValidAlphaNumericInput(Scanner sc, String fieldName)
    {
        String input;
        while (true)
        {
            System.out.printf("%s 를 입력하세요. 영문(대/소문자), 숫자만 입력가능합니다. 중단하려면, 'exit'를 입력하세요 \n1", fieldName);
            input = sc.nextLine();
            if (input.equalsIgnoreCase("exit"))
            {
                return "exit";
            }
            if (input.isEmpty())
            {
                System.out.println(fieldName + "은 비워둘 수 없습니다. 재시도하세요.");
                continue;
            }
            if (isValidAlphaNumeric(input))
            {
                break;
            }
            else
            {
                System.out.println(fieldName + "영문 대/소문자와 숫자만 입력할 수 있습니다. 재시도하세요.");
            }
        }
        return input;
    }
    public static String getValidIdInput(Scanner sc)
    {
        String id;
        while (true)
        {
            System.out.print("등록할 아이디를 입력하세요 (영문+숫자 조합, 8자 이상). 'exit' 입력 시 종료: ");
            id = sc.nextLine();
            if (id.equalsIgnoreCase("exit")) {
                return "exit";
            }
            if (id.isEmpty()) {
                System.out.println("아이디는 비워둘 수 없습니다. 다시 입력하세요.");
                continue;
            }
            if (!isValidIdFormat(id)) {
                System.out.println("영문자와 숫자를 포함하여 8자 이상이어야 합니다. 다시 입력하세요.");
                continue;
            }
            if (isIdTaken(id)) {
                System.out.println("⚠️ [" + id + "]은(는) 이미 존재하는 아이디입니다. 다른 아이디를 사용해주세요.");
                continue;
            }
            break;
        }
        return id;
    }

    public static String getValidRole(Scanner sc)
    {
        String input;
        while(true)
        {
            input = sc.nextLine();
            if(input.equalsIgnoreCase("exit"))
            {
                return "exit";
            }
            if (input.equals("교수") || input.equals("학생"))
            {
                break;
            }
            else
            {
                System.out.println("'교수'와 '학생'만 입력할 수 있습니다.");
            }
        }
        return input;
    }

    public static void processRegister(Scanner sc)
    {
        System.out.println("✨ 회원가입을 시작합니다. (중단하려면 언제든지 'exit'를 입력하세요)\n");
        System.out.println("시스템에 등록된 본인의 정보를 먼저 인증해주세요.");

        String roleString;
        while(true) {
            System.out.print("[교수 / 학생] 중 본인의 역할을 선택하세요: ");
            roleString = sc.nextLine().trim();
            if (roleString.equals("교수") || roleString.equals("학생")) {
                break;
            } else {
                System.out.println("⚠️ '교수' 또는 '학생'만 입력할 수 있습니다.");
            }
        }
        UserType userType = roleString.equals("교수") ? UserType.Professor : UserType.Student;

        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine().trim();
        System.out.print("학번/교번을 입력하세요: ");
        String classNumber = sc.nextLine().trim();

        if(!SystemRoster.isMember(classNumber, name))
        {
            System.out.println("시스템에 등록되지 않은 사용자입니다. 회원가입할 수 없습니다.");
            return;
        }
        System.out.println("\n✅ 본인 인증에 성공했습니다. 사용할 계정 정보를 생성합니다.");

        String id = getValidIdInput(sc);
        if (id.equalsIgnoreCase("exit"))
        {
            System.out.println("회원가입이 중단되었습니다.");
            return;
        }

        String password = getValidAlphaNumericInput(sc, "비밀번호");
        if (password.equalsIgnoreCase("exit"))
        {
            System.out.println("회원가입이 중단되었습니다.");
            return;
        }
        if(roleString.equals("교수"))
        {
            userType = UserType.Professor;
        }
        else if(roleString.equals("학생"))
        {
            userType = UserType.Student;
        }
        else
        {
            userType = null;
            System.out.println("존재하지 않는 역할입니다.");
        }

        User newUser = new User(name, userType, classNumber,  id, password);

        System.out.println("✅ [" + name + " (" + id + ")]님의 회원가입이 완료되었습니다!");
        System.out.println("------------------------------------");
    }
}