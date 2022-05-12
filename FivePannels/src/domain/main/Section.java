package main;

import java.time.Instant;

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
}
