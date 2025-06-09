import java.util.HashMap;
import java.util.Map;

public class SystemRoster
{
    private static final Map<String, String> allowUser = new HashMap<>();

    public static void loadDataRoaster()
    {
        allowUser.put("202410234", "이유림");
        allowUser.put("202410094", "권공하");
        allowUser.put("10324", "신시우");
        allowUser.put("10825", "최단");
    }

    public static boolean isMember(String number, String name)
    {
        return allowUser.containsKey(number) && allowUser.get(number).equals(name);
    }

    public static boolean isEmpty()
    {
        return allowUser.isEmpty();
    }
}
