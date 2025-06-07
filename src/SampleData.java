import java.time.LocalDateTime;

public class SampleData
{
    public static void initialize() {
        if (!Register.userList.isEmpty()) {
            return;
        }
        {
            Register.userList.add(new Register.User("이인", "교수", "rkdt1234", "Lyu1234ty"));
            Register.userList.add(new Register.User("정시우", "교수", "rlot1234", "Tue12034"));
            Register.userList.add(new Register.User("박시현", "학생", "riot1234", "Lke12093"));
            Register.userList.add(new Register.User("권유하", "학생", "quid1234", "Mnx0923"));

            Schedule.setLectureList(0, "자료구조", "월", "10");
            Schedule.setLectureList(1, "알고리즘", "월", "9");
            Schedule.setLectureList(2, "운영체제", "화", "15");
            Schedule.setLectureList(3, "데이터베이스", "목", "13");
            Schedule.setLectureList(4, "컴퓨터네트워크", "금", "16");

            Alarm.addAlarm(2, LocalDateTime.of(2025, 6, 30, 14, 0), "17주차 과제 제출일입니다.");
            Alarm.addAlarm(4, LocalDateTime.of(2025, 7, 30, 9, 0), "퀴즈가 예정되어 있습니다.");
            System.out.println("시스템: 샘플 데이터 생성이 완료되었습니다.");
        }
    }
}