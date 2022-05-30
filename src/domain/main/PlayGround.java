package main;

import main.Case.Case;
import main.dataBase.DataBaseGIdentifiers;
import main.dataBase.Professions;
import main.user.ScoreEntry;
import main.user.User;

import java.io.IOException;
import java.time.Instant;
import java.time.Period;

public class PlayGround
{
    public static void main(String[] args) throws IOException
    {
        User u = new User();
        User u2 = new User();
        User u3 = new User();
//        DataBaseGIdentifiers db = new DataBaseGIdentifiers("C:\\Users\\suala\\Downloads\\Professions.csv","C:\\Users\\suala\\Downloads\\Keywords.csv");
//
//        Case c = new Case("testT", "testQ", u);
//        c.getVoting().setAnswers("test");
//        c.getVoting().setAnswers("testA");
//
//        u2.addProfession(1,db);
//        u3.addProfession(1,db);
//        u2.addKeyword(0,db);
//
//        c.addProfession(1,db);
//        c.addKeywords(0,db);
//        c.addUser(u2);
//        c.addUser(u3);
//        c.getVoting().addVote(0,u2);
//        c.getVoting().addVote(1,u3);
//        c.getVoting().printResult(u2);
//        u.addProfession(1, db);
//        System.out.println(u.getProfession().getName());
//        u3.getScore().printAllAScoreEntrys();
//        c.getVoting().giveCAnswer(1);
//        System.out.println(u2.getScore().geteScoreHistory());
//        System.out.println(u3.getScore().geteScoreHistory());
//        System.out.println(c.isOpen());
//        System.out.println(c.getVoting().isOpen());

        u3.getScore().setaScore("test","test", 1);
        u3.getScore().printAllAScoreEntrys();
        ScoreEntry s = new ScoreEntry("test","test", 1);

//        for testing purposes only
//        for (int i = 0; i < 50; i++)
//        {
//            u.getScore().setaScore("test","test",10);
//        }
//        int i = 50000;
//        int start = 0;
//        for (ScoreEntry s: u.getScore().getaScoreHistory())
//        {
//            s.setcDate(Instant.ofEpochSecond(start+i));
//            i+=50000;
//        }
//        u.getScore().printAllAScoreEntrys();
//        System.out.println(u.getScore().getaScore());
//        u.getScore().printFromToPeriod(Instant.ofEpochSecond(start), Instant.ofEpochSecond(start+i), Period.ofDays(3),true);
    }
}
