package main.dataBase;

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

    public enum LanguagType{
        MANDARIN_CHINESE,
        SPANISH,
        ENGLISH,
        HINDI,
        BENGALI,
        PORTUGUESE,
        RUSSIAN,
        JAPANESE,
        WESTERN_PUNJABI,
        VIETNAMESE,
        MARATHI,
        TELUGU,
        TURKISH,
        KOREAN,
        FRENCH,
        GERMAN,
        ITALIAN,
        EGYPTIAN_ARABIC,
        UKRAINIAN,
        DUTCH,
        SOMALI,
        GREEK,
        ARABIC,
        CZECH;


    }
}



