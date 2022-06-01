package main.dataBase;

import main.Case.Case;
import main.user.User;

import java.util.*;

public abstract class ID
{
    private static final Map<UUID, User> users = new HashMap<>();
    private static final Map<UUID, Case> cases = new HashMap<>();

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

    public static void addUser(User user)
    {
        users.put(user.getId(), Objects.requireNonNull(user,"User"));
    }

    public static void addCase(Case aCase)
    {
        cases.put(aCase.getId(), Objects.requireNonNull(aCase,"case"));
    }
}
