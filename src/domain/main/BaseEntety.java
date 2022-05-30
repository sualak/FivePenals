package main;

import java.time.Instant;
import java.util.UUID;

public abstract class BaseEntety
{
    private final UUID id = UUID.randomUUID();
    private final Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();
    private final IDType type;

    public BaseEntety(IDType type)
    {
        this.type = type;
    }

    public UUID getId()
    {
        return id;
    }

    public Instant getCreatedAt()
    {
        return createdAt;
    }

    public Instant getUpdatedAt()
    {
        return updatedAt;
    }

    public IDType getType()
    {
        return type;
    }

    public void setUpdatedAt()
    {
        updatedAt = Instant.now();
    }

    public void printDate(Instant date)
    {
        System.out.println(date);
    }

    public enum IDType{
        USER, CASE
    }
}




