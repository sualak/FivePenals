package validation;

import main.Case.Case;
import main.user.User;

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
        isAlphabetic(wert, attribut);
        isInRange(answers.size(), zero, maxAnswerListSize, "Amount of answers");
        if(isContainedList(wert, answers))
            throw new IllegalArgumentException("Antwort ist schon entahlten");

        if(!isOpen)
            throw new IllegalStateException("Voting is closed");


        return wert;
    }

    public static void ensurePrintResultValid(User user, String attribut, Map<User, Integer> map)
    {
        isNotNull(user, attribut);
        if(!map.containsKey(user))
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

//    public static Section ensureSectionValid(Case c, Section s, User owner)
//    {
//        //
//        isNotNull(s, "Section");
//        if(!c.isOpen())
//        {
//
//        }
//
//    }



    //---------------------------Social ENSURERS----------------------------------------------------------

    public static void ensureUserNotInContacts(User sender, User receiver, Set<User> contacts){
        isNotNull(sender,"Sender");
        isNotNull(receiver,"Receiver");
        if(isContainedSet(receiver,contacts)){
            throw new IllegalArgumentException("User has already been added to your contacts");
        }
        if(equals(sender,receiver)){
            throw new IllegalArgumentException("You cant add yourself to your contacts");
        }
    }
    public static User ensureNotNullNotBlank(User contact){
        isNotNull(contact,"Null");
        return contact;
    }
}