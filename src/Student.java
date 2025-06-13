import java.util.ArrayList;
import java.util.List;

public class Student extends User
{
    private String studentId;

    private List<Integer> myLectureList = new ArrayList<>();

    public Student(String name, String studentId, String id, String password)
    {
        super(id, name, studentId, password, UserType.Student);
        this.studentId = studentId;
    }
    @Override
    public void displayInfo()
    {
        System.out.println("--- 학생 정보 ---");
        System.out.println("ID: " + getId());
        System.out.println("이름: " + getName());
        System.out.println("학번: " + this.studentId);
        System.out.println("-----------------");
    }

    public void addLectureToTimetable(int lectureIndex)
    {
        if (myLectureList.contains(lectureIndex))
        {
            System.out.println("⚠️ 이미 시간표에 추가된 강의입니다.");
        } else {
            myLectureList.add(lectureIndex);
            System.out.println("✅ 강의가 내 시간표에 추가되었습니다: " + Schedule.getLectureInfo(lectureIndex));
        }
    }

    public void printMyTimetable()
    {
        System.out.println("\n--- " + getName() + "님의 시간표 ---");
        if (myLectureList.isEmpty()) {
            System.out.println("등록된 강의가 없습니다.");
            System.out.println("-------------------------");
            return;
        }

        List<Sort> myLectures = new ArrayList<>();
        for (int index : myLectureList)
        {
            myLectures.add(new Sort(index, Schedule.lecture[index][0], Schedule.lecture[index][1], Schedule.lecture[index][2]));
        }

        LectureSort.sortByDayAndTime(myLectures);

        System.out.printf("%-5s | %-25s | %-5s | %s%n", "인덱스", "강의명", "요일", "강의 시간");
        System.out.println("---------------------------------------------");
        for (Sort lectureSort : myLectures)
        {
            System.out.printf("%-5d | %-25s | %-4s | %s%n", lectureSort.index, lectureSort.name, lectureSort.day, lectureSort.time);
        }
        System.out.println("---------------------------------------------");
        System.out.println(myLectures.size() + "개의 강의가 등록되어 있습니다.");
    }
}
