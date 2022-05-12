package main.dataBase;

import main.Case.Case;
import main.user.User;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public abstract class ID
{
    private static final Map<UUID, User> users = new HashMap<UUID, User>();
    private static final Map<UUID, Case> cases = new HashMap<UUID, Case>();

    public static Map<UUID, User> getUsers()
    {
        return Collections.unmodifiableMap(users);
    }

    public static User getUser(UUID uuid)
    {
        return users.get(uuid);
    }

    public static Map<UUID, Case> getCases()
    {
        return Collections.unmodifiableMap(cases);
    }
}
