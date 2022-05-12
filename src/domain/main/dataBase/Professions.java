package main.dataBase;

public class Professions
{
    private final String name;
    private final ProfessionType type;

    public Professions(String name, ProfessionType type)
    {
        this.name = name;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public ProfessionType getType()
    {
        return type;
    }

    public enum ProfessionType{
        CHIROG
    }
}


