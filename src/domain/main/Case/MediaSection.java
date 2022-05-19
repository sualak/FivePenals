package main.Case;

import validation.Ensure;

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
    public void editContent(String content)
    {

    }

    public enum FileIdentifierType{
        JPG, JPEG, AVIF, GIF
    }
}
