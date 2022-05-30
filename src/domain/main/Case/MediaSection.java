package main.Case;

import validation.Ensure;

import java.util.ArrayList;

public class MediaSection extends Section
{
    private String fileIdentifier;
    private String fileName;

    public MediaSection(String owner, String fileIdentifier, String filename)
    {
        this.fileIdentifier = fileIdentifier;
        this.fileName = fileName;
    }

    public String getFileIdentifier()
    {
        return fileIdentifier;
    }

    public String getFileName()
    {
        return fileName;
    }

    @Override
    public void editContent(String newContent, ArrayList<String> content, int position)
    {
        Ensure.ensurePositionValid(position, content);
//        if(Objects.equals(FileIdentifierType.valueOf(fileIdentifier.toUpperCase()).toString(), this.fileIdentifier))
//        {
//            l.addAll(position, )
//        }
        setUpdatedAt();
    }

    public enum FileIdentifierType{
        JPG, JPEG, AVIF, GIF, WEBP
    }
}
