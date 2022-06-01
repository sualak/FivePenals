package main.user;

import main.BaseEntety;
import main.Case.Case;
import main.dataBase.*;
import static validation.Ensure.*;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class User extends BaseEntety
{
    private Email eMail;
    private Password password;
    private final Social sData = new Social(this);
    private Personal pData;
    private final Score score = new Score();
    //private final GIdentifiers gIdentifiers = new GIdentifiers();
    private Professions profession;
    private final Set<Keywords> keywords = new HashSet<>();
    private Location location;
    private Language language;
    private final Set<Case> cases = new HashSet<>();

    public User(Email eMail, Password password, Personal pData)
    {
        super(IDType.USER);
        this.eMail = eMail;
        this.password = password;
        this.pData = pData;


    }
//    public User(Email eMail, Password password, Personal pData)
//    {
//        super(IDType.USER);
//        this.eMail = ensureValidMail(eMail);
//        this.password = password;
//        this.pData = pData;
//    }

//    public User()
//    {
//        super(IDType.valueOf("USER"));
//    }

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

    public void changeProfession(int key, DataBaseGIdentifiers db)
    {
        //kein valid nötig
        this.profession = db.getProfession(key);
        super.updateUDate();
    }

    public void changeLocation(int key, DataBasePIdentifiers db)
    {
        this.location = db.getLocation(key);
        super.updateUDate();
    }

    public void addKeyword(int key, DataBaseGIdentifiers db)
    {
        //kein valid nötig
        keywords.add(db.getKeyword(key));
    }

    public void resetPassword(String newPassword)
    {
        ensureValidPassword(newPassword);
        this.password = new Password(newPassword);
        super.updateUDate();
    }

    public void resetEmail(String newEmail)
    {
        ensureValidMail(newEmail);
        this.eMail = new Email(newEmail);
        super.updateUDate();
    }

    public void removeKeyword(int key, DataBaseGIdentifiers db)
    {
        keywords.remove(db.getKeyword(key));
    }

}
