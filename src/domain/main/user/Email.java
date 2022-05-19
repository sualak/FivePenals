package main.user;
import static validation.Ensure.*;

public class Email
{
    private final String eMail;

    public Email(String eMail)
    {
        this.eMail = ensureValidMail(eMail);
    }

    public String geteMail()
    {
        return eMail;
    }
}
