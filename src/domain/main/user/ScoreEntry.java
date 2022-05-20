package main.user;

import validation.Ensure;

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

    public static final String PATTERN_FORMAT = "dd.MM.yyyy";

    //constructor
    public ScoreEntry(String caseTitel, String reason, int addedScore)
    {
        this.caseTitel = Ensure.ensureStringValid(caseTitel, "Casetitel");
        this.reason = Ensure.ensureStringValid(reason, "reason");
        this.addedScore = addedScore;
        String className = new Throwable()                // or new `Exception()`
                .getStackTrace()[1]
                .getClassName();
        if(!className.equals("main.user.Score"))
            throw new RuntimeException("Score Entry is not allowed to be instanced from " + className);
    }

    //getter
    public String getcDateAsString()
    {


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
        return String.format("%s %s %d %s", getcDateAsString(), reason, addedScore, caseTitel);
    }

    //for Testing purposes only cDate final must be deleated
//    public void setcDate(Instant cDate)
//    {
//        this.cDate = cDate;
//    }
}
