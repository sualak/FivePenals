package main;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ScoreEntryTest
{
    private final Instant cDate;
    private final String caseTitel;
    private final String reason;
    private final int addedScore;

    public ScoreEntryTest(String caseTitel, String reason, int addedScore, Instant cDate)
    {
        this.caseTitel = caseTitel;
        this.reason = reason;
        this.addedScore = addedScore;
        this.cDate = cDate;
    }

    public String getcDate()
    {
        String PATTERN_FORMAT = "dd.MM.yyyy";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());
        return formatter.format(cDate);
    }

    public Instant getcDateAsInstant()
    {
        return cDate;
    }

    public LocalDate getcDateAsLocalDate()
    {
        return LocalDate.ofInstant(cDate,ZoneId.systemDefault());
    }

    public String getCaseTitel()
    {
        return caseTitel;
    }

    public String getReason()
    {
        return reason;
    }

    public int getAddedScore()
    {
        return addedScore;
    }

    public String toString()
    {
        return String.format("%s %s %d %s", getcDate(), reason, addedScore, caseTitel);
    }
}
