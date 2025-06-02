import java.time.ZoneId;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.Clock;

public class SystemClock
{
    private static Clock clock = Clock.systemDefaultZone();
    private static final ZoneId Zone_ID = ZoneId.of("Asia/Seoul");
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static void setFixedTime(LocalDateTime fixedDateTime) {
        clock = Clock.fixed(fixedDateTime.atZone(Zone_ID).toInstant(), Zone_ID);
        System.out.println("🕒 시스템 시간이 수동으로 설정되었습니다: " + fixedDateTime.format(formatter) + " (KST)");
    }
}
