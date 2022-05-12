package main.Case;

import main.Case.Section;


//test klasse nicht verwenden
public class TextSection extends Section
{
    private String content;

    public TextSection(String content)
    {
        this.content = content;
    }

    public String getContent()
    {
        return content;
    }
}
