import java.util.Scanner;

public class cal
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("강의 수를 입력하세요.");
        int count = sc.nextInt();
        sc.nextLine();

        String[] Lecture_Name = new String[count]; // 변수명 변경 요망

        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + "번째 강의명을 입력하세요: ");
            Lecture_Name[i] = sc.nextLine();
        }

        String[] Lecture_Day = new String[count]; // 변수명 변경 요망

        for (int i = 0; i < count; i++)
        {
            System.out.print((i + 1) + "강의 요일을 입력하세요. Ex: 월, 화 ");
            Lecture_Day[i] = sc.nextLine();
        }

        int schedule = sc.nextInt();
        for (int i = 0; i < count; i++)
        {
            System.out.print((i + 1) + "강의 시간을 입력하세요. Ex: 10, 13");
            schedule = sc.nextInt();
            sc.nextLine();
        }

        System.out.println("강의 목록");
        for (String str : Lecture_Name)
        {
            System.out.println(str);
        }

        System.out.println("강의 요일");
        for (String str : Lecture_Day)
        {
            System.out.println(str);
        }
    }
}
