package main.Case;

import validation.Ensure;

import java.util.ArrayList;

//test klasse nicht verwenden
public class TextSection extends Section
{
    private ArrayList<String> content;

    public TextSection(ArrayList<String> content)
    {
        this.content = content;
    }

    public ArrayList<String> getContent()
    {
        return content;
    }

    @Override
    public void editContent(String newContent, ArrayList<String> content, int position)
    {
        Ensure.ensurePositionValid(position, content);
        Ensure.ensureContentValid(newContent);
        content.add(position, newContent);
        setUpdatedAt();
    }
}
