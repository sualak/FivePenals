package main;

import java.util.*;

public class Case extends BaseEntety
{
    private String titel;
    //private final GIdentifiers identifiers = new GIdentifiers();
    private final Set<Professions> professions = new HashSet<>();
    private final Set<Keywords> keywords = new HashSet<>();
    private Voting voting;
    private User owner;
    private List<User> users = new ArrayList<>();
    private List<Section> sections = new ArrayList<>();
    private boolean isOpen = true;

    public Case(String titel, String Question, User owner)
    {
        super(IDType.CASE);
        this.titel = titel;
        this.voting = new Voting(Question, this);
        this.owner = owner;
    }

    public String getTitel()
    {
        return titel;
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

    public void addProfession(int key, DataBeseGIdentifiers db)
    {
        professions.add(db.getProfession(key));
    }

    public void addKeywords(int key, DataBeseGIdentifiers db)
    {
        keywords.add(db.getKeyword(key));
    }

    public void addUser(User user)
    {
        users.add(user);
    }
}
