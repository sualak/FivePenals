package main.user;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Score
{
    private final List<ScoreEntry> aScoreHistory = new ArrayList<>();
    private final List<ScoreEntry> eScoreHistory = new ArrayList<>();
    private int activeScore = zero;
    private int expertScore = zero;
    
    private static final int zero = 0;
    
    

    //getter
    public int getActiveScore()
    {
        return activeScore;
    }

    public int getExpertScore()
    {
        return expertScore;
    }

    public List<ScoreEntry> getaScoreHistory()
    {
        return Collections.unmodifiableList(aScoreHistory);
//        for testing purposes only
//        return aScoreHistory;
    }

    public List<ScoreEntry> geteScoreHistory()
    {
        return Collections.unmodifiableList(eScoreHistory);
    }


    //setter
    public void setaScore(String caseTitel,String reason, int aScore)
    {
        this.activeScore += aScore;
        aScoreHistory.add(new ScoreEntry(caseTitel, reason, aScore));
    }

    public void seteScoer(String caseTitel,String reason, int eScoer)
    {
        this.expertScore += eScoer;
        eScoreHistory.add(new ScoreEntry(caseTitel, reason, eScoer));
    }

    //print from to with period
    public void printFromToPeriod(Instant cDate,Instant endDate, Period period, boolean offset)
    {
        StringBuilder sb = new StringBuilder();
        LocalDate start = LocalDate.ofInstant(cDate, ZoneId.systemDefault());
        LocalDate end = LocalDate.ofInstant(endDate, ZoneId.systemDefault());
        List<LocalDate> dateList = new ArrayList<>();

        start.datesUntil(end ,period).forEach((dateList::add));

        dateList.add(end);

        int lastListEntry = (dateList.size() - 1);

        int sum = zero;

        for (int i = zero; i < lastListEntry; i++)
        {
            LocalDate from = dateList.get(i);
            LocalDate to = dateList.get(i+1);
            for (ScoreEntry s: aScoreHistory)
            {
                if((from.compareTo(s.getCreatedAtAsLocalDate()) <= zero && to.compareTo(s.getCreatedAtAsLocalDate()) > zero)
                        || (to.isEqual(dateList.get(lastListEntry)) && from.compareTo(s.getCreatedAtAsLocalDate()) <= zero && to.compareTo(s.getCreatedAtAsLocalDate()) >= zero))
                    sum+=s.getAddedScore();
            }
            sb.append(from.toString()).append(" - ").append(to.toString()).append(" | Added Score : ").append(sum).append("\n");
            if(!offset)
                sum = zero;
        }

        System.out.println(sb);
    }


//    // print all entrys
    public void printAllAScoreEntrys()
    {
        StringBuilder sb = new StringBuilder();
        for (ScoreEntry s: aScoreHistory)
        {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }

    public void printAllEScoreEntrys()
    {
        StringBuilder sb = new StringBuilder();
        for (ScoreEntry s: eScoreHistory)
        {
            sb.append(s).append("\n");
        }
        System.out.println(sb);
    }
}
