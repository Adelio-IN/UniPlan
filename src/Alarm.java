import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

public class Alarm
{
    static class AlarmData
    {
        public static boolean getAlarms;
        private final int scheduleIndex;
        private final LocalDateTime alarmTime;
        private final String message;
        private boolean triggered = false;


        public AlarmData(int scheduleIndex, LocalDateTime alarmTime, String message)
        {
            this.scheduleIndex = scheduleIndex;
            this.alarmTime = alarmTime;
            this.message = message;
        }

        public static void addAlarm(int index, LocalDateTime alarmTime, String message)
        {
            alarmList.add(new AlarmData(index, alarmTime, message));
            System.out.println("알람 추가 완료" + alarmTime.format(DateTimeFormatter.ofPattern("yyyy-MM-DD HH:mm")) + " - " + message);
        }

        public static void printAllAlarms()
        {
            
        }

        public int getScheduleIndex()
        {
            return scheduleIndex;
        }
        public LocalDateTime getAlarmTime()
        {
            return alarmTime;
        }
        public String getMessage()
        {
            return message;
        }
        public boolean isTriggered()
        {
            return triggered;
        }
        public void setTriggered(boolean triggered)
        {
            this.triggered = triggered;
        }

        private static final List<AlarmData> alarmList = new ArrayList<>();
        private static Timer schduleTimer;

        public static void addAlarm(int scheduleIndex, LocalDateTime alarmTime, String message, boolean triggered)
        {
            alarmList.add(new AlarmData(scheduleIndex, alarmTime, message));
            System.out.println("알람 추가 완료");
        }
        public static void scheduler()
        {
            if (schduleTimer != null)
            {
                System.out.println("실행중");
            }
            schduleTimer = new Timer();
            TimerTask task = new TimerTask()
            {
                @Override
                public void run()
                {
                    LocalDateTime now = LocalDateTime.now();

                    for(AlarmData alarm : alarmList)
                    {
                        if(!alarm.isTriggered() && alarm.getAlarmTime().isAfter(now))
                        {
                            ringAlarm(alarm);
                            alarm.setTriggered(true);
                        }
                    }
                }
            };
            schduleTimer.schedule(task, 0, 1000); // task를 즉시 시작하여 1초마다 반복 실행
        }
        private static void ringAlarm(AlarmData alarm)
        {
            String lectureInfo = Schedule.getLectureInfo(alarm.getScheduleIndex());

            System.out.println("-----------\uD83D\uDEA8---------");
            System.out.println("\n강의 명:" + lectureInfo);
            System.out.println("\n메시지: " + alarm.getMessage());
            System.out.println("\n마감 시간: " + alarm.getAlarmTime());
            System.out.println("--------------------------------");
        }

        public static void removeAlarm(int index)
        {
            if (index >= 0 && index < alarmList.size())
            {
                AlarmData removeAlarm = alarmList.remove(index);
                System.out.println("✅ 알람이 삭제되었습니다: " + removeAlarm.message);
            }
            else
            {
                System.out.println("잘못된 번호입니다. 목록에 있는 번호를 입력해주세요.");
            }
        }
    }
}
