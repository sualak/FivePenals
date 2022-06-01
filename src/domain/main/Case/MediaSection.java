package main.Case;

import validation.Ensure;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MediaSection extends Section
{
    private BufferedImage picture;
    private final String fileIdentifier;
    private String fileName;

    public MediaSection(String owner, String fileIdentifier, String filename)
    {
        this.fileIdentifier = fileIdentifier;
    }

    public String getFileIdentifier()
    {
        return fileIdentifier;
    }

    public String getFileName()
    {
        return fileName;
    }

    public BufferedImage getPicture()
    {
        return picture;
    }

    public void addPicture(String path) throws IOException
    {
        Ensure.ensurePathValid(path);
        picture = ImageIO.read(new File(path));
    }

    public void deletePicture(BufferedImage pic)
    {
        if(pic == this.picture){
            picture = null;
        }
    }

    @Override
    public void editContent(String newContent, String content, boolean beginning)
    {

    }

    public enum FileIdentifierType{
        JPG, JPEG, AVIF, GIF, WEBP
    }
}
