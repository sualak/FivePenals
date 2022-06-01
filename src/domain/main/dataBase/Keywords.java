package main.dataBase;

import validation.Ensure;

public class Keywords
{
    private final String name;
    private final KeywordType type;

    public Keywords(String name, KeywordType type)
    {
        //todo name notNullNotBlank
        this.name = Ensure.ensureNonNullNonBlankValid(name,"name");
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
        SYMPTOM,
        PROCEDURE
    }
}


