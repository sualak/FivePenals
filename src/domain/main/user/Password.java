package main.user;

import static validation.Ensure.*;

public class Password
{
    private final String password;

    public Password(String password)
    {
        this.password = ensureValidPassword(password);
    }

    public String getPassword()
    {
        return password;
    }
}
