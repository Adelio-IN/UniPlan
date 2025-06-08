import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.time.format.DateTimeFormatter;

public class EventSchedule
{
    private int lectureIndex;
    private String eventName;
    private LocalDateTime eventDateTime;
    private String description;
    private boolean notification;

    public static final List<EventSchedule> eventList = new ArrayList<>();

    public EventSchedule(int lectureIndex, String eventName, LocalDateTime eventDateTime, String description)
    {
        this.eventName = eventName;
        this.lectureIndex = lectureIndex;
        this.eventDateTime = eventDateTime;
        this.description = description;
        this.notification = false;
    }

    public String getEventName()
    {
        return eventName;
    }
    public LocalDateTime getEventDateTime()
    {
        return eventDateTime;
    }
    public String getDescription()
    {
        return description;
    }
    public int getLectureIndex()
    {
        return lectureIndex;
    }
    @Override
    public String toString()
    {
        String lectureInfo = Schedule.getLectureInfo(this.lectureIndex);
        return String.format("[강의: %s] %s - %s (%s)", lectureInfo, this.eventName, this.description, this.eventDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }
}
