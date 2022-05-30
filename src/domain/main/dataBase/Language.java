package main.dataBase;

import validation.Ensure;

public class Language
{
    private final String name;
    private final LanguagType type;

    public Language(String name, LanguagType type)
    {
        this.name = Ensure.ensureNonNullNonBlankValid(name,"name");
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

    public enum LanguagType{
        SPANISH,
        ENGLISH,
        PORTUGUESE,
        RUSSIAN,
        JAPANESE,
        VIETNAMESE,
        TURKISH,
        KOREAN,
        FRENCH,
        GERMAN,
        ITALIAN,
        DUTCH,
        GREEK,
        ARABIC,
        CZECH
    }
}



