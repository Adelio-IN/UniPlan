import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.regex.Pattern;

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
        }
        private static void ringAlarm(AlarmData alarm)
        {
            String lectureInfo = Schedule.getLectureCount()
        }
    }
}
