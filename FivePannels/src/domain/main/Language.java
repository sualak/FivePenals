package main;

public class Language
{
    private String name;
    private LanguagType type;

    public Language(String name, LanguagType type)
    {
        this.name = name;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public LanguagType getType()
    {
        return type;
    }
}

enum LanguagType{

}
