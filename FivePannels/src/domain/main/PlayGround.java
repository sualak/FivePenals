package main;

import validation.Ensure;

import java.io.IOException;
import java.time.Instant;
import java.util.List;

public class PlayGround
{
    public static void main(String[] args) throws IOException
    {
        User u = new User();
        User u2 = new User();
        User u3 = new User();
        DataBeseGIdentifiers db = new DataBeseGIdentifiers();

        Case c = new Case("testT", "testQ", u);
        c.getVoting().setAnswers("test");
        c.getVoting().setAnswers("testA");

        ScoreEntry s1  = new ScoreEntry("test", "test", 10);
        ScoreEntry s2 = new ScoreEntry("test", "test", 10);

        u2.addProfession(1,db);
        u3.addProfession(1,db);
        u2.addKeyword(0,db);

        c.addProfession(1,db);
        c.addKeywords(1,db);
        c.addUser(u2);
        c.addUser(u3);
        c.getVoting().addVote(0,u2);
        c.getVoting().addVote(1,u3);
        c.getVoting().printResult(u2);
        u.addProfession(1, db);
        System.out.println(u.getProfession().getName());
        u3.getScore().printAllAScoreEntrys();

    }
}
