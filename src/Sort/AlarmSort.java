import java.util.Comparator;
import java.util.List;

public class AlarmSort
{
    public static void sortTime(List<Alarm.AlarmData> alarms)
    {
        if (alarms == null || alarms.isEmpty())
        {
            return;
        }
        alarms.sort(Comparator.comparing(Alarm.AlarmData::getAlarmTime));
    }
}
