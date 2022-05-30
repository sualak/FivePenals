package main.user;

import static validation.Ensure.*;

import java.time.LocalDate;

public class Personal
{
    private Titel titel;
    private String vName;
    private String nName;
    private final LocalDate birthday;


    public Personal(Titel titel, String vName, String nName, LocalDate birthday)
    {
        this.titel = titel;
        this.vName = ensureValidName(vName);
        this.nName = ensureValidName(nName);
        this.birthday = ensureValidBirthday(birthday);
    }

    public Titel getTitel()
    {
        return titel;
    }

    public String getvName()
    {
        return vName;
    }

    public String getnName()
    {
        return nName;
    }

    public LocalDate getBirthday()
    {
        return birthday;
    }

    public void setvName(String vName)
    {
        this.vName = ensureValidName(vName);
    }

    public void setnName(String nName)
    {
        this.nName = ensureValidName(nName);
    }

    public void setTitel(Titel titel)
    {
        this.titel = titel;
    }

    public enum Titel{
        TEST, TEST2, TEST3
    }
}


