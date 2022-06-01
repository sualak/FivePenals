package main;

import main.Case.Case;
import main.Case.TextSection;
import main.dataBase.DataBaseGIdentifiers;
import main.dataBase.DataBasePIdentifiers;
import main.dataBase.Professions;
import main.user.Personal;
import main.user.ScoreEntry;
import main.user.User;

import java.io.IOException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class PlayGround
{
    public static void main(String[] args) throws IOException
    {
        //databases
        DataBaseGIdentifiers dbG = new DataBaseGIdentifiers("C:\\Users\\suala\\Downloads\\Professions.csv","C:\\Users\\suala\\Downloads\\Keywords.csv");
        DataBasePIdentifiers dbP = new DataBasePIdentifiers("C:\\Users\\suala\\Downloads\\Locations.csv","C:\\Users\\suala\\Downloads\\Languages.csv");

        //User
        User u0 = new User("owner@gmx.at","ownerPW1", Personal.Titel.DOKTOR,"owner","owner",LocalDate.of(1996,5,31));
        User u1 = new User("berni.piffel@gmx.at", "bernispasswort1", Personal.Titel.INGENIEUR, "berni","piffel", LocalDate.of(1996,5,31));
        User u2 = new User("c.schramme@gmx.at", "christopher1", Personal.Titel.DOKTOR, "Christopher", "Schramme", LocalDate.of(2001,11,7));
        User u3 = new User();
        User u4 = new User();

        //case
        Case c = new Case("Grade of the Project", "Wich grade", u0);

        //answers
        c.getVoting().setAnswers("Grade 1");
        c.getVoting().setAnswers("Grade 2");
        c.getVoting().setAnswers("Grade 3");
        c.getVoting().setAnswers("Grede 4");
        c.getVoting().setAnswers("Grade 5");

        //Location
        u1.changeLocation(1,dbP);
        u2.changeLocation(2,dbP);
        u3.changeLocation(3,dbP);
        u4.changeLocation(4,dbP);

        //language
        u1.changeLanguage(1,dbP);
        u2.changeLanguage(2,dbP);
        u3.changeLanguage(3,dbP);
        u4.changeLanguage(4,dbP);

        //Professions
        u1.changeProfession(1,dbG);
        u2.changeProfession(2,dbG);
        u3.changeProfession(3,dbG);
        u4.changeProfession(4,dbG);

        //keywords_User
        u1.addKeyword(0,dbG);
        u1.addKeyword(1,dbG);
        u1.addKeyword(2,dbG);
        u2.addKeyword(0,dbG);
        u2.addKeyword(2,dbG);
        u2.addKeyword(4,dbG);
        u3.addKeyword(0,dbG);
        u3.addKeyword(3,dbG);
        u3.addKeyword(6,dbG);
        u4.addKeyword(0,dbG);
        u4.addKeyword(4,dbG);
        u4.addKeyword(8,dbG);

        //keywords_Case
        c.addProfession(1,dbG);
        c.addProfession(2,dbG);
        c.addProfession(3,dbG);
        c.addKeywords(0,dbG);
        c.addKeywords(1,dbG);
        c.addKeywords(2,dbG);
        c.addKeywords(3,dbG);

        //Users to Case
        c.addUser(u1);
        c.addUser(u2);
        c.addUser(u3);
        c.addUser(u4);

        c.getVoting().printQuestionAndAnswers();
        //Voting
        c.getVoting().addVote(0,u1);
        c.getVoting().printResult(u1);
        c.getVoting().addVote(1,u2);
        c.getVoting().printResult(u2);
        c.getVoting().addVote(0,u3);
        c.getVoting().printResult(u3);
        c.getVoting().addVote(1,u4);
        c.getVoting().printResult(u4);

        //Print Active Score
        u1.getScore().printAllAScoreEntrys();
        u2.getScore().printAllAScoreEntrys();
        u3.getScore().printAllAScoreEntrys();
        u4.getScore().printAllAScoreEntrys();

        //give corect Answer
        c.getVoting().giveCAnswer(0);

        //print Export scores
        u1.getScore().printAllEScoreEntrys();
        u2.getScore().printAllEScoreEntrys();
        u3.getScore().printAllEScoreEntrys();
        u4.getScore().printAllEScoreEntrys();

        //send Requests
        u1.getSocial().sendRequest(u2);
        u2.getSocial().sendRequest(u3);
        u3.getSocial().sendRequest(u4);
        u4.getSocial().sendRequest(u1);

        //accept/denie Requests
        u1.getSocial().sendRequest(u4);
        u2.getSocial().handleRequest(u1,true);
        u3.getSocial().handleRequest(u2,false);

        //print firends
        u1.getSocial().printContacts();
        u2.getSocial().printContacts();
        u3.getSocial().printContacts();
        u4.getSocial().printContacts();


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
