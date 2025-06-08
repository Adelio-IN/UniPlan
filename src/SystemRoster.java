import java.util.HashMap;
import java.util.Map;

public class SystemRoster
{
    private static final Map<String, String> allowUser = new HashMap<>();

    public static void loadDataRoaster()
    {
        allowUser.put("202139079", "이인");
        allowUser.put("202508199", "박시현");
        allowUser.put("10239", "정시우");
        allowUser.put("10973", "권유하");
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
