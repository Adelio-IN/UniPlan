import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.ArrayList;

public class Alarm
{
    static class AlarmData
    {
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
        public boolean isTriggered() {
            return triggered;
        }
        public void setTriggered(boolean triggered)
        {
            this.triggered = triggered;
        }
    }

    private static final List<AlarmData> alarmList = new ArrayList<>();
    private static Timer schedulerTimer;

    public static void addAlarm(int scheduleIndex, LocalDateTime alarmTime, String message)
    {
        alarmList.add(new AlarmData(scheduleIndex, alarmTime, message));
        System.out.printf("🔔 알람 추가 완료: %s - \"%s\"\n",
                alarmTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")), message);
    }

    public static void startScheduler()
    {
        if (schedulerTimer != null)
        {
            return;
        }
        schedulerTimer = new Timer(true);
        TimerTask checkTask = new TimerTask()
        {
            @Override
            public void run()
            {
                LocalDateTime now = SystemClock.now();
                for (AlarmData alarm : alarmList)
                {
                    if (!alarm.isTriggered() && !alarm.getAlarmTime().isAfter(now))
                    {
                        ringAlarm(alarm);
                        alarm.setTriggered(true);
                    }
                }
            }
        };
        schedulerTimer.scheduleAtFixedRate(checkTask, 0, 1000);
        System.out.println("⏰ 알람 스케줄러가 시작되었습니다.");
    }

    public static void stopScheduler() {
        if (schedulerTimer != null) {
            schedulerTimer.cancel();
            schedulerTimer = null;
            System.out.println("⏰ 알람 스케줄러가 중지되었습니다.");
        }
    }

    private static void ringAlarm(AlarmData alarm) {
        String lectureInfo = Schedule.getLectureInfo(alarm.getScheduleIndex());
        System.out.println("\n================ 🚨 알람 🚨 ================");
        System.out.println("\n  강의 명: " + lectureInfo);
        System.out.println("\n  메시지: " + alarm.getMessage());
        System.out.println("\n  설정된 시간: " + alarm.getAlarmTime().format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("================================================");
    }

    public static void printAllAlarms() {
        System.out.println("\n--- 등록된 알람 목록 ---");
        if (alarmList.isEmpty())
        {
            System.out.println("등록된 알람이 없습니다.");
            return;
        }

        AlarmSort.sortTime(alarmList);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        for (int i = 0; i < alarmList.size(); i++)
        {
            AlarmData alarm = alarmList.get(i);
            String lectureInfo = Schedule.getLectureInfo(alarm.getScheduleIndex());
            System.out.printf("%d. [강의: %s] %s | 시간: %s | 상태: %s\n",
                    (i + 1), lectureInfo, alarm.getMessage(),
                    alarm.getAlarmTime().format(formatter),
                    alarm.isTriggered() ? "알람 울림" : "알람 울리기 전");
        }
        System.out.println("------------------------------");
    }

    public static int getAlarmsCount()
    {
        return alarmList.size();
    }

    public static boolean removeAlarm(int index)
    {
        if (index >= 0 && index < alarmList.size())
        {
            AlarmData removedAlarm = alarmList.remove(index);
            System.out.println("✅ 알람이 삭제되었습니다: \"" + removedAlarm.getMessage());
            return true;
        }
        else
        {
            System.out.println("잘못된 번호입니다. 목록에 있는 번호를 입력해주세요.");
            return false;
        }
    }
}