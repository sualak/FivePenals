package validation;

import main.Case.Case;
import main.Case.Section;
import main.dataBase.Keywords;
import main.user.User;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

import static java.lang.String.format;

public abstract class Ensure
{
    //constants
    private static final int maxListSize = 100;
    private static final int minListSize = 60;
    private static final int maxAnswerListSize = 8;
    private static final int zero = 0;

    //generelle ensurer
    private static void isInRange(int zuTesten, int min, int max, String attribut)
    {
        if (zuTesten < min || zuTesten > max)
            throw new IllegalArgumentException(format("%s muss zwischen %d und %d sein.", attribut, min, max));
    }

    private static <T> void isNotNull(T zuTesten, String attribut)
    {
        Objects.requireNonNull(zuTesten, format("%s darf nicht null sein", attribut));
    }

    private static void isAlphabetic(String zuTesten, String attribut)
    {
        if (!zuTesten.matches("[a-zA-Z]+"))
            throw new IllegalArgumentException(format("%s muss aus dem Alphabet bestehen",attribut));
    }

    private static void isInRange(double zuTesten, double min, double max, String attribut)
    {
        if (zuTesten < min || zuTesten > max)
            throw new IllegalArgumentException(format("%s muss zwischen %.2f und %.2f sein.", attribut, min, max));
    }

    private static void isNotBlank(String zuTesten, String attribut)
    {
        if (zuTesten.isBlank())
            throw new IllegalArgumentException(format("%s darf nicht leer sein", attribut));
    }

    private static <T> boolean equals(T zuTesten, T fix)
    {
        return zuTesten.equals(fix);
    }

    private static <T> boolean isContainedList(T zuTesten, List<T> list)
    {
        return list.contains(zuTesten);
    }

    private static <T> boolean isContainedSet(T zuTesten, Set<T> set)
    {
        return set.contains(zuTesten);
    }

    private static <T> boolean isContainedMap(T zuTesten, Map<T,T> map)
    {
        return map.containsKey(zuTesten);
    }

    public static int ensureIntRange(int wert, int min, int max, String attribut)
    {
        isInRange(wert, min, max, attribut);

        return wert;
    }

    public static double ensureDoubleRange(double wert, double min, double max, String attribut)
    {
        isInRange(wert, min, max, attribut);

        return wert;
    }

    //spezielle ensurer
    public static String ensureStringValid(String wert, String attribut)
    {
        isNotNull(wert, attribut);
        isNotBlank(wert, attribut);
        isAlphabetic(wert, attribut);

        return wert;
    }

    public static String ensureNonNullNonBlankValid(String wert, String attribut)
    {
        isNotNull(wert, attribut);
        isNotBlank(wert, attribut);

        return wert;
    }

    public static int ensureGiveCAnswerValid(int wert, boolean isOpen, List<String> answers)
    {
        isInRange(wert, zero, answers.size(), "Correct Answer");
        if (!isOpen)
            throw new IllegalArgumentException("Voting ist schon geschlossen");

        return wert;
    }

    public static <T> int ensureAddVoteValid(int wert, int max, User user, boolean isOpen, Map<User, Integer> map, User owner)
    {
        isInRange(wert, zero, max, "Voting");
        isNotNull(user, "User");
        if(map.containsKey(user))
            throw new IllegalArgumentException("User hat schon gevoted");

        if(equals(user, owner))
            throw new IllegalArgumentException("Owner darf nicht Voten");

        if(!isOpen)
            throw new IllegalStateException("Voting is closed");


        return wert;
    }

    public static String ensureAnswerValid(String wert, String attribut, List<String> answers, boolean isOpen)
    {
        isNotNull(wert, attribut);
        isNotBlank(wert, attribut);
        isInRange(answers.size(), zero, maxAnswerListSize, "Amount of answers");
        if(isContainedList(wert, answers))
            throw new IllegalArgumentException("Antwort ist schon entahlten");

        if(!isOpen)
            throw new IllegalStateException("Voting is closed");


        return wert;
    }

    public static void ensurePrintResultValid(User user, String attribut, Map<User, Integer> map, User owner)
    {
        isNotNull(user, attribut);
        if(!map.containsKey(user) || user.equals(owner))
            throw new IllegalArgumentException("Man muss gevoted haben um das resultat zu sehen");
    }

    public static Case ensureCaseValid(Case aCase, String attribut)
    {
        isNotNull(aCase, attribut);
        return aCase;
    }

    public static boolean isOpenValid(boolean isOpen, Case thisCase, Case aCase)
    {
        if(!equals(thisCase, aCase))
            throw new IllegalArgumentException("Only the case for the Voting is allowed to set Open");

        return isOpen;
    }

    //---------------------------CASE ENSURERS----------------------------------------------------------

    public static User ensureUserValid(User user, List<User> users, User owner)
    {
        isNotNull(user,"User");
        if(isContainedList(user, users))
        {
            throw new IllegalArgumentException("User has already been added to the Case");
        }
        if (equals(user, owner))
        {
            throw new IllegalArgumentException("You can not add yourself to the Case");
        }
        return user;
    }

    public static User ensureOwnerValid(User user, List<User> users, User owner)
    {
        isNotNull(user,"User");
        if(isContainedList(user, users))
        {
            throw new IllegalArgumentException("Owner can not be a User of the Case");
        }
        return user;
    }

    //---------------------------Social ENSURERS----------------------------------------------------------

    public static void ensureUserNotInContacts(User user,User owner, Set<User> contacts){
        isNotNull(user,"User");

        if(isContainedSet(user,contacts)){
            throw new IllegalArgumentException("User has already been added to your contacts");
        }
        if(equals(user,owner)){
            throw new IllegalArgumentException("You cant add yourself to your contacts");
        }
    }
    public static void ensureNotNullNotBlank(User contact){
        isNotNull(contact,"Null");
    }
    public static void ensureContactIsRequesting(User user, Set<User> request){
        if(!request.contains(user))
            throw new IllegalArgumentException("This user didnt send a request");
    }
    //todo Ensurer for ownerNotNull
//    public static void ensureOwnerNotNull()
//    {
//        isNotNull(owner,"User");
//
//    }

    //---------------------------Section ENSURERS----------------------------------------------------------

    public static Section ensureSectionValid(Case c, Section s, User owner)
    {
        isNotNull(s, "Section");
        if(!c.isOpen())
        {
            throw  new IllegalStateException("Case is already closed");
        }
        return s;
    }

    //Textsection

    public static String ensureContentValid(String content)
    {
        isNotNull(content, "Content");
        isNotBlank(content, "Content");
        isAlphabetic(content, "Content");
        return content;
    }

    public static boolean ensurePositionValid(int position, ArrayList<String> toCheck)
    {
        if(position<=0||position>toCheck.size())
        {
            throw new ArrayIndexOutOfBoundsException("Keine gültige Position!");
        }
        return true;
    }

    //---------------------------Keyword ENSURERS----------------------------------------------------------

    public static Keywords ensureKeywordValid(Case c, Keywords k, User owner)
    {
        isNotNull(k, "Keyword");
        isNotBlank(k.getName(), "Keyword");
        isAlphabetic(k.getName(), "Keyword");
        return k;
    }

    //---------------------------DATABASE ENSURERS----------------------------------------------------------
    public static <T> Integer ensureKeyIsValid(Integer key , Map<Integer, T> map)
    {
        if (!map.containsKey(key))
        {
            throw new IllegalArgumentException("Key is not valid");
        }

        return key;
    }

    //---------------------------DataBaseGIdentifiers ENSURERS----------------------------------------------------------
    public static void ensureCaseNotClosed(Case c)
    {
        if(!c.isOpen())
        {
            throw new IllegalStateException("Case is already closed");
        }
    }

//------------------------------User ENSURERS------------------------------------------------
    public static String ensureValidMail(String email)
    {
        if (!email.matches("(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9]))\\.){3}(?:(2(5[0-5]|[0-4][0-9])|1[0-9][0-9]|[1-9]?[0-9])|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])"))
            throw new IllegalArgumentException(email + " ist keine valide Email");
        return email;
    }

    public static String ensureValidPassword(String password)
    {
        //Minimum eight characters, at least one letter and one number
        if(!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$"))
            throw new IllegalArgumentException(password + " ist kein valides Passwort");
        return password;
    }

//---------------------------Personal ENSURERS---------------------------------------------
    public static String ensureValidName(String name)
    {
        if(!name.matches("^[a-zA-Z]+(([',. -][a-zA-Z ])?[a-zA-Z]*)*$"))
            throw new IllegalArgumentException(name + " ist kein valider Name");
        return name;
    }

    public static LocalDate ensureValidBirthday(LocalDate birthday)
    {
        long diff = ChronoUnit.YEARS.between(birthday, LocalDate.now());
        if(diff < 20 || diff > 90)
            throw new IllegalArgumentException("Kein Valides Geburtsdatum");
        return birthday;
    }
}