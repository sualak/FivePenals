package main.Case;

import java.time.Instant;
import java.util.ArrayList;

public abstract class Section
{
    private final Instant createdAt = Instant.now();
    private Instant updatedAt = Instant.now();

    public Instant getCreatedAt()
    {
        return createdAt;
    }

    public Instant getUpdatedAt()
    {
        return updatedAt;
    }

    public void setUpdatedAt()
    {
        this.updatedAt = Instant.now();
    }

    public abstract void editContent(String newContent, ArrayList<String> content, int position);

}
