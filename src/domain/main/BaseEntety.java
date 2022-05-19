package main;

import java.time.Instant;
import java.util.UUID;

public abstract class BaseEntety
{
    private final UUID id = UUID.randomUUID();
    private final Instant cDate = Instant.now();
    private Instant uDate = Instant.now();
    private final IDType type;

    public BaseEntety(IDType type)
    {
        this.type = type;
    }

    public UUID getId()
    {
        return id;
    }

    public Instant getcDate()
    {
        return cDate;
    }

    public Instant getuDate()
    {
        return uDate;
    }

    public IDType getType()
    {
        return type;
    }

    public void setuDate()
    {
        uDate = Instant.now();
    }

    public enum IDType{
        USER, CASE
    }
}




