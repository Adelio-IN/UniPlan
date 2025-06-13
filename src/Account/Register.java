import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Register
{
    public static List<User> userList = new ArrayList<>();

    public void registerUser(User user)
    {
        if(user != null)
        {
            this.userList.add(user);
            System.out.println(user.getName() + " has been registered");
        }
    }
    public static boolean isValidAlphaNumeric(String input)
    {
        if (input == null || input.isEmpty())
        {
            return false;
        }
        return input.matches("^[a-zA-Z0-9]*$");
    }

    public static boolean isValidIdFormat(String input)
    {
        if (input == null || input.isEmpty())
        {
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
    public static boolean isNumber(String str)
    {
        if (str ==null)
        {
            return false;
        }return str.matches("\\d+");
    }
    public static boolean isValidStudentNumber(String number)
    {
        return number != null && number.length() == 9 && isNumber(number);
    }

    public static boolean isValidProfessorNumber(String number)
    {
        return number != null && number.length() == 5 && isNumber(number);
    }

    public static String getValidIdInput(Scanner sc)
    {
        String id;
        while (true)
        {
            System.out.print("등록할 아이디를 입력하세요 (영문+숫자 조합, 8자 이상). 'exit' 입력 시 종료: ");
            id = sc.nextLine();
            if (id.equalsIgnoreCase("exit"))
            {
                return "exit";
            }
            if (id.isEmpty())
            {
                System.out.println("아이디는 비워둘 수 없습니다. 다시 입력하세요.");
                continue;
            }
            if (!isValidIdFormat(id))
            {
                System.out.println("영문자와 숫자를 포함하여 8자 이상이어야 합니다. 다시 입력하세요.");
                continue;
            }
            if (isIdTaken(id))
            {
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
            System.out.print("역할을 선택하세요 ('교수' 또는 '학생'): ");
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
    private static String getPasswordInput(Scanner sc)
    {
        String password;
        while (true)
        {
            System.out.print("사용할 비밀번호를 입력하세요: ");
            password = sc.nextLine().trim();
            if (password.equalsIgnoreCase("exit"))
            {
                return "exit";
            }
            if (!password.isEmpty())
            {
                break;
            } else {
                System.out.println("⚠️ 비밀번호는 비워둘 수 없습니다.");
            }
        }
        return password;
    }
    public static void processRegister(Scanner sc) {
        System.out.println("\n✨ 회원가입(계정 활성화)을 시작합니다.");
        System.out.println("시스템에 등록된 본인의 정보를 먼저 인증해주세요.");

        String roleString;
        while (true) {
            System.out.print("역할을 선택하세요 (학생, 교수). 'exit' 입력 시 종료: ");
            roleString = sc.nextLine().trim();
            if (roleString.equalsIgnoreCase("exit"))
            {
                System.out.println("회원가입을 중단했습니다.");
                return;
            }
            if (roleString.equals("학생") || roleString.equals("교수"))
            {
                break;
            }
            else
            {
                System.out.println("⚠️ 잘못된 역할입니다. '학생' 또는 '교수' 중에서 입력해주세요.");
            }
        }

        System.out.print("이름을 입력하세요: ");
        String name = sc.nextLine().trim();

        String number;
        while (true)
        {
            if (roleString.equals("학생"))
            {
                System.out.print("학번(9자리 숫자)을 입력하세요: ");
                number = sc.nextLine().trim();
                if (isValidStudentNumber(number))
                {
                    break;
                }
                else
                {
                    System.out.println("⚠️ 학번은 반드시 9자리 숫자여야 합니다. 다시 입력해주세요.");
                    continue;
                }
            }
            else
            {
                System.out.print("교번(5자리 숫자)을 입력하세요: ");
                number = sc.nextLine().trim();
                if (isValidProfessorNumber(number))
                {
                    break;
                } else {
                    System.out.println("⚠️ 교번은 반드시 5자리 숫자여야 합니다. 다시 입력하세요.");
                }
            }
        }

        if (!SystemRoster.isMember(number, name))
        {
            System.out.println("시스템에 등록되지 않은 사용이거나, 정보가 일치하지 않습니다.");
            System.out.println("회원가입을 진행할 수 없습니다.");
            return;
        }

        for (User user : userList)
        {
            if (user.getId().equals(number))
            {
                System.out.println("이미 가입된 계정이 존재합니다.");
                return;
            }
        }

        System.out.println("\n✅ 본인 인증에 성공했습니다. 사용할 계정 정보를 생성합니다.");

        String id = getValidIdInput(sc);
        if ("exit".equals(id))
        {
            System.out.println("회원가입을 중단했습니다.");
            return;
        }

        String password = getPasswordInput(sc);
        if ("exit".equals(password))
        {
            System.out.println("회원가입을 중단했습니다.");
            return;
        }

        User newUser;
        if (roleString.equals("학생"))
        {
            newUser = new Student(name, number, id, password);
        }
        else
        {
            newUser = new Professor(name, number, id, password);
        }
        userList.add(newUser);

        System.out.println("🎉 [" + name + " (" + id + ")]님의 계정 활성화(회원가입)가 완료되었습니다!");
    }
}