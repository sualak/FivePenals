package main;

import java.time.Instant;
import java.util.Objects;
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

    public void updateUDate()
    {
        uDate = Instant.now();
    }

    public enum IDType{
        USER, CASE
    }

    public boolean equals(BaseEntety baseEntety)
    {
        return id.equals(baseEntety.id);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(getId());
    }

    public void printDate(Instant date)
    {
        System.out.println(date);
    }
}




