package main.user;

import main.BaseEntety;
import main.Case.Case;
import main.dataBase.*;
import static validation.Ensure.*;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class User extends BaseEntety
{
    private Email eMail;
    private Password password;
    private final Social social = new Social(this);
    private Personal personal;
    private final Score score = new Score();
    //private final GIdentifiers gIdentifiers = new GIdentifiers();
    private Professions profession;
    private final Set<Keywords> keywords = new HashSet<>();
    private Location location;
    private Language language;
    private final Set<Case> cases = new HashSet<>();

    public User(String eMail, String password, Personal.Titel titel, String vName, String nName, LocalDate birthday)
    {
        super(IDType.USER);
        this.eMail = new Email(eMail);
        this.password = new Password(password);
        this.personal = new Personal(titel, vName, nName, birthday);
        ID.addUser(this);
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

    public Social getSocial()
    {
        return social;
    }

    public Personal getPersonal()
    {
        return personal;
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
        super.setUpdatedAt();
    }

    public void changeLocation(int key, DataBasePIdentifiers db)
    {
        this.location = db.getLocation(key);
        super.setUpdatedAt();
    }

    public void addKeyword(int key, DataBaseGIdentifiers db)
    {
        //kein valid nötig
        keywords.add(db.getKeyword(key));
        super.setUpdatedAt();
    }

    public void resetPassword(String newPassword)
    {
        ensureValidPassword(newPassword);
        this.password = new Password(newPassword);
        super.setUpdatedAt();
    }

    public void resetEmail(String newEmail)
    {
        ensureValidMail(newEmail);
        this.eMail = new Email(newEmail);
        super.setUpdatedAt();
    }

    public void removeKeyword(int key, DataBaseGIdentifiers db)
    {
        keywords.remove(db.getKeyword(key));
        super.setUpdatedAt();
    }

    public void addCase(Case cases)
    {
        this.cases.add(Objects.requireNonNull(cases, "Case"));
    }

    public void changeFirstName(String firstname)
    {
        personal.setvName(firstname);
        super.setUpdatedAt();
    }

    public void changeLastName(String lastname)
    {
        personal.setnName(lastname);
        super.setUpdatedAt();
    }

    public void changeTitel(Personal.Titel titel)
    {
        personal.setTitel(titel);
        super.setUpdatedAt();
    }

    public void changeLanguage(int key, DataBasePIdentifiers db)
    {
        this.language = db.getLanguage(key);
        super.setUpdatedAt();
    }

    @Override
    public String toString()
    {
        return "--------------User--------------\n" +
                "Vorname: " + personal.getnName() +
                "\nNachname: " + personal.getnName() +
                "\nTitel: " + personal.getTitel() +
                "\nGeburtsdatum: " + personal.getBirthday() +
                "\nEmail: " + eMail.geteMail() +
                "\nPasswort: " + password.getPassword() +
                "\nActive Score: " + score.getActiveScore() +
                "\nExpert Score: " + score.getExpertScore() +
                "\nSprache: " + getLanguage() +
                "\nOrt: " + getLocation() +
                "\nProfession: " + getProfession() +
                "\nKeywords: " + getKeywords() +
                "\nKontakte: " + social.getContacts();

    }
}
