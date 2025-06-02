import java.util.ArrayList;

public class Schedule
{
    static final int maxLectureList = 10;

    static String[][] lecture = new String[maxLectureList][3];

    static ArrayList<String>[] lectureList = new ArrayList[maxLectureList];

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
        lecture[index][0] = name;
        lecture[index][1] = day;
        lecture[index][2] = time;
    }
    public static void addLecture(int index, String lectureName)
    {
        lectureList[index].add(lectureName);
    }
    public static int getLectureCount(int index)
    {
        return lectureList[index].size();
    }
    public static void printAllLectures()
    {
        for (int i = 0; i < maxLectureList; i++)
        {
            printLectureInfo(i);
        }
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
