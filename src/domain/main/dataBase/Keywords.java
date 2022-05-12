package main.dataBase;

public class Keywords
{
    private String name;
    private KeywordType type;

    public Keywords(String name, KeywordType type)
    {
        this.name = name;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public KeywordType getType()
    {
        return type;
    }

    public enum KeywordType{
        TEST
    }
}


