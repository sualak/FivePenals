package main.Case;


import main.*;
import main.dataBase.DataBaseGIdentifiers;
import main.dataBase.ID;
import main.dataBase.Keywords;
import main.dataBase.Professions;
import main.user.User;
import validation.Ensure;

import java.io.IOException;
import java.util.*;

public class Case extends BaseEntety <Case>
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

    public Case(String titel, String question, User owner)
    {
        super(IDType.CASE);
        this.titel = Ensure.ensureNonNullNonBlankValid(titel, "Titel");
        this.voting = new Voting(Ensure.ensureNonNullNonBlankValid(question, "Question"), this);
        this.owner = Ensure.ensureOwnerValid(owner, users, getOwner());
        ID.addCase(this);
    }

    public void setOpen(boolean open)
    {
        isOpen = open;
        super.setUpdatedAt();
    }

    public String getTitel()
    {
        return titel;
    }

    public void setTitel(String titel)
    {
        Ensure.ensureNonNullNonBlankValid(titel, "Titel");
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
        Ensure.ensureCaseNotClosed(this);
        professions.add(db.getProfession(key));
        super.setUpdatedAt();
    }

    public void addKeywords(int key, DataBaseGIdentifiers db)
    {
        Ensure.ensureCaseNotClosed(this);
        keywords.add(db.getKeyword(key));
        super.setUpdatedAt();
    }

    public void addUser(User user)
    {
        users.add(Ensure.ensureUserValid(user, users, owner));
        user.addCase(this);
    }

    public void resetVoting(String question)
    {
        Ensure.ensureCaseNotClosed(this);
        Ensure.ensureNonNullNonBlankValid(question, "Question");
        this.voting = new Voting(question, this);
        super.setUpdatedAt();
    }

    public void addSection(Section section)
    {
        Ensure.ensureCaseNotClosed(this);
        sections.add(Ensure.ensureSectionValid(this, section, this.owner));
        super.setUpdatedAt();
    }

    public void editTextsection(int sectionIndex, String newContent, String content, boolean beginning)
    {
        Ensure.ensureCaseNotClosed(this);
        Ensure.ensureRangeValid(sectionIndex, sections);
        sections.get(sectionIndex).editContent(newContent, content, beginning);
        super.setUpdatedAt();
    }

    public void replaceTextsectionContent(int sectionIndex, String newContent)
    {
        Ensure.ensureCaseNotClosed(this);
        Ensure.ensureRangeValid(sectionIndex, sections);
        ((TextSection) sections.get(sectionIndex)).replaceSectionContent(newContent);
        super.setUpdatedAt();
    }

    public void addPictureToMediasection(int sectionIndex, String path) throws IOException
    {
        Ensure.ensureCaseNotClosed(this);
        Ensure.ensureRangeValid(sectionIndex, sections);
        ((MediaSection) sections.get(sectionIndex)).addPicture(path);
        super.setUpdatedAt();
    }

    public void removeSection(int sectionIndex)
    {
        Ensure.ensureCaseNotClosed(this);
        Ensure.ensureRangeValid(sectionIndex, sections);
        sections.remove(sections.get(sectionIndex));
        super.setUpdatedAt();
    }

    public void removeKeyword(int key, DataBaseGIdentifiers dataBaseGIdentifiers)
    {
        Ensure.ensureCaseNotClosed(this);
        keywords.remove(dataBaseGIdentifiers.getKeyword(key));
        super.setUpdatedAt();
    }

    public void removeProfessions(int key, DataBaseGIdentifiers dataBaseGIdentifiers)
    {
        Ensure.ensureCaseNotClosed(this);
        professions.remove(dataBaseGIdentifiers.getProfession(key));
        super.setUpdatedAt();
    }

}
