package main.user;

import main.*;
import main.Case.Case;
import main.dataBase.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User extends BaseEntety
{
    private Email eMail;
    private Password password;
    private Social sData = new Social();
    private Personal pData;
    private Score score = new Score();
    //private final GIdentifiers gIdentifiers = new GIdentifiers();
    private Professions profession;
    private final Set<Keywords> keywords = new HashSet<>();
    private Location location;
    private Language language;
    private Set<Case> cases = new HashSet<>();

    public User(Email eMail, Password password, Personal pData)
    {
        super(IDType.USER);
        this.eMail = eMail;
        this.password = password;
        this.pData = pData;
    }

    public User()
    {
        super(IDType.valueOf("USER"));
    }

    public Email geteMail()
    {
        return eMail;
    }

    public Password getPassword()
    {
        return password;
    }

    public Social getsData()
    {
        return sData;
    }

    public Personal getpData()
    {
        return pData;
    }

    public Score getScore()
    {
        return score;
    }

    public Professions getProfession()
    {
        return profession;
    }

    public Set<Keywords> getKeywords()
    {
        return Collections.unmodifiableSet(keywords);
    }

    public Location getLocation()
    {
        return location;
    }

    public Language getLanguage()
    {
        return language;
    }

    public Set<Case> getCases()
    {
        return Collections.unmodifiableSet(cases);
    }

    public void addProfession(int key, DataBaseGIdentifiers db)
    {
        this.profession = db.getProfession(1);
    }

    public void addKeyword(int key, DataBaseGIdentifiers db)
    {
        keywords.add(db.getKeyword(1));
    }
}
