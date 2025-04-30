import java.util.Scanner;

public class cal
{
    public static void main(String[] args)
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("강의 수를 입력하세요.");
        int count = sc.nextInt();
        sc.nextLine();

        String[] strings = new String[count]; // 변수명 변경 요망

        for (int i = 0; i < count; i++) {
            System.out.print((i + 1) + "번째 강의명을 입력하세요: ");
            strings[i] = sc.nextLine();
        }
        String[] strings1 = new String[count]; // 변수명 변경 요망

        for (int i = 0; i < count; i++)
        {
            System.out.print((i + 1) + "강의 요일을 입력하세요. Ex: 월, 화 ");
            strings1[i] = sc.nextLine();
        }
        System.out.println("강의 목록");
        for (String str : strings)
        {
            System.out.println(str);
        }
    }
}
