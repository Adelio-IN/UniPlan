import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventSchedule
{
    private String eventName;
    private LocalDateTime eventDateTime;
    private String description;
    private boolean notification;
    private String lectureTime;

    public EventSchedule(String eventName, LocalDateTime eventDateTime, String description, boolean notification, String lectureTime)
    {
        this.eventName = eventName;
        this.eventDateTime = eventDateTime;
        this.description = description;
        this.notification = false;
        this.lectureTime = lectureTime;
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

    public boolean isNotification()
    {
        return notification;
    }

    public String getLectureTime()
    {
        return lectureTime;
    }

    public List<EventSchedule> getAssignments()
    {
        return
    }

    public void setNotification(boolean notificationSent) {
        this.notification = notificationSent;
    }
    @Override
    public String toString()
    {
        return "EventSchedule [eventName=" + eventName + ", eventDateTime=" + eventDateTime + ", description=" + description + "'}";
    }
}
