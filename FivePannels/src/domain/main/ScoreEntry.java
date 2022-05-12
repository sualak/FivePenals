package main;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ScoreEntry
{
    private final Instant cDate = Instant.now();
    private final String caseTitel;
    private final String reason;
    private final int addedScore;

    //constructor
    public ScoreEntry(String caseTitel, String reason, int addedScore)
    {
        this.caseTitel = caseTitel;
        this.reason = reason;
        this.addedScore = addedScore;
    }

    //getter
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


    //toString
    public String toString()
    {
        return String.format("%s %s %d %s", getcDate(), reason, addedScore, caseTitel);
    }

    //forTestingOnly cDate final must be deleated
//    public void setcDate(Instant cDate)
//    {
//        this.cDate = cDate;
//    }
}
