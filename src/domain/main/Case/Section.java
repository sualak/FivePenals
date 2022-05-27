package main.Case;

import java.time.Instant;
import java.util.ArrayList;

public abstract class Section
{
    private final Instant cDate = Instant.now();
    private Instant uDate = Instant.now();

    public Instant getcDate()
    {
        return cDate;
    }

    public Instant getuDate()
    {
        return uDate;
    }

    public void setuDate()
    {
        this.uDate = Instant.now();
    }

    public abstract void editContent(String newContent, ArrayList<String> content, int position);

}
