package main;

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
        this.vName = vName;
        this.nName = nName;
        this.birthday = birthday;
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
}

enum Titel{

}
