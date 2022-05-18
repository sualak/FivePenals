package main.Case;

import validation.Ensure;

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
    public void editContent(String content)
    {
        this.content = Ensure.ensureContentValid(content);
    }
}
