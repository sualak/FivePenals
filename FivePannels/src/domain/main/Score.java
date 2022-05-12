package main;

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
    private int aScore = zero;
    private int eScoer = zero;
    
    private static final int zero = 0;
    private static final int mounthOffset = 3;
    private static final int yearOffset = 6;
    
    

    //getter
    public int getaScore()
    {
        return aScore;
    }

    public int geteScoer()
    {
        return eScoer;
    }

    public List<ScoreEntry> getaScoreHistory()
    {
        return Collections.unmodifiableList(aScoreHistory);
    }

    public List<ScoreEntry> geteScoreHistory()
    {
        return Collections.unmodifiableList(eScoreHistory);
    }


    //setter
    public void setaScore(String caseTitel,String reason, int aScore)
    {
        this.aScore += aScore;
        aScoreHistory.add(new ScoreEntry(caseTitel, reason, aScore));
    }

    public void seteScoer(String caseTitel,String reason, int eScoer)
    {
        this.eScoer += eScoer;
        eScoreHistory.add(new ScoreEntry(caseTitel, reason, eScoer));
    }

    public void printFromToPeriod(Instant cDate,Instant endDate, Period period, boolean offset)
    {
        StringBuilder sb = new StringBuilder();
        LocalDate start = LocalDate.ofInstant(cDate, ZoneId.systemDefault());
        LocalDate end = LocalDate.ofInstant(endDate, ZoneId.systemDefault());
        List<LocalDate> dateList = start.datesUntil(end ,period).toList();
        int lastListEntry = (dateList.size() - 1);

        if(!end.equals(dateList.get(lastListEntry)))
            dateList.add(end);

        int sum = zero;

        for (int i = zero; i < lastListEntry; i++)
        {
            LocalDate from = dateList.get(i);
            LocalDate to = dateList.get(i+1);
            for (ScoreEntry s: aScoreHistory)
            {
                if(from.compareTo(s.getcDateAsLocalDate()) > zero && to.compareTo(s.getcDateAsLocalDate()) < zero)
                    sum+=s.getAddedScore();
            }
            sb.append(from.toString()).append("-").append(to.toString()).append("Added Score : ").append(sum).append("\n");
            if(!offset)
                sum = zero;
        }

        System.out.println(sb.toString());
    }

    // print from to
    public void printFromTo(Instant from, Instant to)
    {
        long fromSecond = from.getEpochSecond();
        long toSecond = to.getEpochSecond();
        StringBuilder sb = new StringBuilder();
        for (ScoreEntry s : aScoreHistory)
        {
            long nowSecond = s.getcDateAsInstant().getEpochSecond();
            if(nowSecond >= fromSecond && nowSecond <= toSecond)
                sb.append(s.toString()).append("\n");

            if(nowSecond > toSecond)
                break;
        }
        System.out.println(sb);
    }

    // print all entrys
    public void printAllAScoreEntrys()
    {
        StringBuilder sb = new StringBuilder();
        for (ScoreEntry s: aScoreHistory)
        {
            sb.append(s.toString()).append("\n");
        }
        System.out.println(sb);
    }

    public void printAllEScoreEntrys()
    {
        StringBuilder sb = new StringBuilder();
        for (ScoreEntry s: eScoreHistory)
        {
            sb.append(s.toString()).append("\n");
        }
        System.out.println(sb);
    }

    //print by day
    public void printValuesByDayAScore()
    {
        StringBuilder sb = new StringBuilder();
        int sumOfDay = zero;
        String currentDate = "";
        for (int i = zero; i < aScoreHistory.size()-1; i++)
        {
            ScoreEntry now = aScoreHistory.get(i);
            ScoreEntry nextDay = aScoreHistory.get(i+1);

            if(sumOfDay == zero)
            {
                sumOfDay += now.getAddedScore();
                currentDate = now.getcDate();
            }

            if(now.getcDate().equals(nextDay.getcDate()))
            {
                sumOfDay += nextDay.getAddedScore();
            }
            else
            {
                sb.append(currentDate).append(sumOfDay);
                nextDay.getcDate();
                sumOfDay = zero;
            }
        }
        sb.append(currentDate).append(sumOfDay);
        System.out.println(sb);
    }

    public void printValuesByDayEScore()
    {
        StringBuilder sb = new StringBuilder();
        int sumOfDay = zero;
        String currentDate = "";
        for (int i = zero; i < eScoreHistory.size()-1; i++)
        {
            ScoreEntry now = eScoreHistory.get(i);
            ScoreEntry nextDay = eScoreHistory.get(i+1);

            if(sumOfDay == zero)
            {
                sumOfDay += now.getAddedScore();
                currentDate = now.getcDate();
            }

            if(now.getcDate().equals(nextDay.getcDate()))
            {
                sumOfDay += nextDay.getAddedScore();
            }
            else
            {
                sb.append(currentDate).append(sumOfDay);
                nextDay.getcDate();
                sumOfDay = zero;
            }
        }
        sb.append(currentDate).append(sumOfDay);
        System.out.println(sb);
    }


    //print by month
    public void printValuesByMonthAScore()
    {
        StringBuilder sb = new StringBuilder();
        int sumOfDay = zero;
        String currentDate = "";
        for (int i = zero; i < aScoreHistory.size()-1; i++)
        {
            ScoreEntry now = aScoreHistory.get(i);
            ScoreEntry nextDay = aScoreHistory.get(i+1);

            if(sumOfDay == zero)
            {
                sumOfDay += now.getAddedScore();
                currentDate = now.getcDate().substring(mounthOffset);
            }

            if(now.getcDate().substring(mounthOffset).equals(nextDay.getcDate().substring(mounthOffset)))
            {
                sumOfDay += nextDay.getAddedScore();
            }
            else
            {
                sb.append(currentDate).append(sumOfDay);
                nextDay.getcDate();
                sumOfDay = zero;
            }
        }
        sb.append(currentDate).append(sumOfDay);
        System.out.println(sb);
    }

    public void printValuesByMonthEScore()
    {
        StringBuilder sb = new StringBuilder();
        int sumOfDay = zero;
        String currentDate = "";
        for (int i = zero; i < eScoreHistory.size()-1; i++)
        {
            ScoreEntry now = eScoreHistory.get(i);
            ScoreEntry nextDay = eScoreHistory.get(i+1);

            if(sumOfDay == zero)
            {
                sumOfDay += now.getAddedScore();
                currentDate = now.getcDate().substring(mounthOffset);
            }

            if(now.getcDate().substring(mounthOffset).equals(nextDay.getcDate().substring(mounthOffset)))
            {
                sumOfDay += nextDay.getAddedScore();
            }
            else
            {
                sb.append(currentDate).append(sumOfDay);
                nextDay.getcDate();
                sumOfDay = zero;
            }
        }
        sb.append(currentDate).append(sumOfDay);
        System.out.println(sb);
    }

    //print by Year
    public void printValuesByYearEScore()
    {
        StringBuilder sb = new StringBuilder();
        int sumOfDay = zero;
        String currentDate = "";
        for (int i = zero; i < eScoreHistory.size()-1; i++)
        {
            ScoreEntry now = eScoreHistory.get(i);
            ScoreEntry nextDay = eScoreHistory.get(i+1);

            if(sumOfDay == zero)
            {
                sumOfDay += now.getAddedScore();
                currentDate = now.getcDate().substring(yearOffset);
            }

            if(now.getcDate().substring(yearOffset).equals(nextDay.getcDate().substring(yearOffset)))
            {
                sumOfDay += nextDay.getAddedScore();
            }
            else
            {
                sb.append(currentDate).append(sumOfDay);
                nextDay.getcDate();
                sumOfDay = zero;
            }
        }
        sb.append(currentDate).append(sumOfDay);
        System.out.println(sb);
    }

    public void printValuesByYearAScore()
    {
        StringBuilder sb = new StringBuilder();
        int sumOfDay = zero;
        String currentDate = "";
        for (int i = zero; i < aScoreHistory.size()-1; i++)
        {
            ScoreEntry now = aScoreHistory.get(i);
            ScoreEntry nextDay = aScoreHistory.get(i+1);

            if(sumOfDay == zero)
            {
                sumOfDay += now.getAddedScore();
                currentDate = now.getcDate().substring(yearOffset);
            }

            if(now.getcDate().substring(yearOffset).equals(nextDay.getcDate().substring(yearOffset)))
            {
                sumOfDay += nextDay.getAddedScore();
            }
            else
            {
                sb.append(currentDate).append(sumOfDay);
                nextDay.getcDate();
                sumOfDay = zero;
            }
        }
        sb.append(currentDate).append(sumOfDay);
        System.out.println(sb);
    }
}
