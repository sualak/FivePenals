package main.Case;


import main.*;
import main.dataBase.DataBaseGIdentifiers;
import main.dataBase.Keywords;
import main.dataBase.Professions;
import main.user.User;
import validation.Ensure;

import java.util.*;

public class Case extends BaseEntety
{
    private String titel;
    //private final GIdentifiers identifiers = new GIdentifiers();
    private final Set<Professions> professions = new HashSet<>();
    private final Set<Keywords> keywords = new HashSet<>();
    private Voting voting;
    private final User owner;
    private final List<User> users = new ArrayList<>();
    private final List<Section> sections = new ArrayList<>();
    private boolean isOpen = true;

    public Case(String titel, String Question, User owner)
    {
        super(IDType.CASE);
        this.titel = Ensure.ensureTitelValid(titel, "Titel");
        this.voting = new Voting(Question, this);
        this.owner = Ensure.ensureUserValid(owner, users, getOwner());
    }

    public void setOpen(boolean open)
    {
        isOpen = open;
    }

    public String getTitel()
    {
        return titel;
    }

    public void setTitel(String titel)
    {
        Ensure.ensureTitelValid(titel, "Titel");
        this.titel = titel;
    }

    public Set<Professions> getProfessions()
    {
        return Collections.unmodifiableSet(professions);
    }

    public Set<Keywords> getKeywords()
    {
        return Collections.unmodifiableSet(keywords);
    }

    public Voting getVoting()
    {
        return voting;
    }

    public User getOwner()
    {
        return owner;
    }

    public List<User> getUsers()
    {
        return Collections.unmodifiableList(users);
    }

    public List<Section> getSections()
    {
        return Collections.unmodifiableList(sections);
    }

    public boolean isOpen()
    {
        return isOpen;
    }

    public void addProfession(int key, DataBaseGIdentifiers db)
    {
        professions.add(db.getProfession(key));
    }

    public void addKeywords(int key, DataBaseGIdentifiers db)
    {
        keywords.add(db.getKeyword(key));
    }

    public void addUser(User user)
    {
        users.add(Ensure.ensureUserValid(user, users, owner));
    }

    public void resetVoting(String question)
    {
        Ensure.ensureStringValid(question, "Question");
        this.voting = new Voting(question, this);
    }

    public void addSection(Section section)
    {
        sections.add(Ensure.ensureSectionValid(this, section, this.owner));
    }

    public void removeSection(Section section)
    {
        sections.remove(Ensure.ensureSectionValid(this, section, this.owner));
    }

    public void removeKeyword(int key, DataBaseGIdentifiers dataBaseGIdentifiers)
    {
        keywords.remove(dataBaseGIdentifiers.getKeyword(key));
    }

    public void removeProfessions(int key, DataBaseGIdentifiers dataBaseGIdentifiers)
    {
        professions.remove(dataBaseGIdentifiers.getProfession(key));
    }

}
