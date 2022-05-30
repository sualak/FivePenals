package main.user;

import validation.Ensure;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class ScoreEntry
{
    private final Instant createdAt = Instant.now();
    private final String caseTitel;
    private final String reason;
    private final int addedScore;

    public static final String PATTERN_FORMAT = "dd.MM.yyyy";

    //constructor
    public ScoreEntry(String caseTitel, String reason, int addedScore)
    {
        this.caseTitel = Ensure.ensureTitelValid(caseTitel, "Casetitel");
        this.reason = Ensure.ensureStringValid(reason, "reason");
        this.addedScore = addedScore;
//        String className = new Throwable()                // or new `Exception()`
//                .getStackTrace()[1]
//                .getClassName();
        String instance = StackWalker.getInstance(StackWalker.Option.RETAIN_CLASS_REFERENCE).getCallerClass().getName();
        if(!instance.equals("main.user.Score"))
            throw new RuntimeException("Score Entry is not allowed to be instanced from " + instance);
    }

    //getter
    public String getcDateAsString()
    {


        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_FORMAT)
                .withZone(ZoneId.systemDefault());
        return formatter.format(createdAt);
    }

    public Instant getcDateAsInstant()
    {
        return createdAt;
    }

    public LocalDate getcDateAsLocalDate()
    {
        return LocalDate.ofInstant(createdAt,ZoneId.systemDefault());
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
