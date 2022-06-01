package main.Case;

import validation.Ensure;

import java.util.ArrayList;

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

    @Override
    public void editContent(String newContent, String content, boolean beginning)
    {
        if(beginning)
        {
            content = Ensure.ensureContentValid(newContent)+content;
        }
        else {
            content += Ensure.ensureContentValid(newContent);
        }
    }

    public void replaceSectionContent(String newContent)
    {
        Ensure.ensureContentValid(newContent);
        content = newContent;
    }
}
