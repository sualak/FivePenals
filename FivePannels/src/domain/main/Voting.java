package main;

import java.util.*;

import static validation.Ensure.*;

public class Voting
{
    private final List<Double> result = new ArrayList<>();
    private final List<String> answers = new ArrayList<>();
    private final String question;
    private final Map<User, Integer> voted = new HashMap<>();
    private final Case aCase;
    private int cAnswer = noCAnswer;
    private boolean isOpen = true;

    //constants
    private static final int noCAnswer = -1;
    private static final int scoreAddforVote = 20;
    private static final int scoreAddforRightAnswer = 20;
    private static final int scoreAddforWrongAnswer = -5;
    private static final int zero = 0;
    private static final int toPerzent = 100;


    //constructor
    public Voting(String quiestion, Case aCase)
    {
        this.question = ensureStringValid(quiestion, "Question");
        this.aCase = ensureCaseValid(aCase, "Case");
    }


    //getter
    public List<Double> getResult()
    {
        return Collections.unmodifiableList(result);
    }

    public List<String> getAnswers()
    {
        return Collections.unmodifiableList(answers);
    }

    public String getQuestion()
    {
        return question;
    }

    public String getcAnswer()
    {
        if (cAnswer == noCAnswer)
            return "Es gibt noch keine richtige antwort";

        return answers.get(cAnswer);
    }

    public Map<User, Integer> getVoted()
    {
        return Collections.unmodifiableMap(voted);
    }


    //setter
    public void isOpen(Case aCase, boolean isOpen)
    {
        this.isOpen = isOpenValid(isOpen, aCase, this.aCase);
    }

    public void setAnswers(String answer)
    {
        answers.add(ensureAnswerValid(answer, "Answer", Collections.unmodifiableList(answers), isOpen));
        result.add((double)zero);
    }

    public void giveCAnswer(int cAnswer)
    {
        this.cAnswer = ensureGiveCAnswerValid(cAnswer, isOpen, answers);
        String titel = aCase.getTitel();
        List<User> entriesRight = voted.entrySet().stream().filter((entry) -> entry.getValue().equals(cAnswer)).map(Map.Entry::getKey).toList();
        entriesRight.forEach((User u) -> u.getScore().seteScoer(titel, "right answer", scoreAddforRightAnswer));
        List<User> entriesWrong = voted.entrySet().stream().filter((entry) -> !entry.getValue().equals(cAnswer)).map(Map.Entry::getKey).toList();
        entriesWrong.forEach((User u) -> u.getScore().seteScoer(titel, "wrong answer", scoreAddforWrongAnswer));
    }

    public void addVote(int vote, User user)
    {
        voted.put(user, ensureAddVoteValid(vote, answers.size(), user, isOpen, getVoted(), aCase.getOwner()));
        user.getScore().setaScore(aCase.getTitel(), "Voted", scoreAddforVote);
        calcResult(vote,user);
    }


    //print
    public void printResult(User user)
    {
        ensurePrintResultValid(user, "user", getVoted());
        StringBuilder sb = new StringBuilder();
        double sum = zero;
        for (double wert : result)
        {
            sum += wert;
        }
        for (int i = 0; i < answers.size(); i++)
        {
            double antwortSumme = result.get(i);
            if (antwortSumme != zero)
                sb.append(String.format("%.2f",(antwortSumme / sum) * toPerzent)).append("% ").append(answers.get(i)).append("\n");
            else
                sb.append(0 + "% ").append(answers.get(i)).append("\n");
        }
        System.out.println(sb);
    }


    //private
    private int calcUserResult(User user, Set<Professions> professions, Set<Keywords> keywords)
    {
        int sum = zero;
        if (professions.contains(user.getProfession()))
            sum = 1;

        sum += (int) user.getKeywords().stream().filter(keywords::contains).count();
        return sum;
    }

    private void calcResult(int vote, User user)
    {
        int size = aCase.getKeywords().size() + aCase.getProfessions().size();
        if (size != zero)
        {
            double currentValue = result.get(vote);
            int sumOfMatchedKeywords = calcUserResult(user, aCase.getProfessions(), aCase.getKeywords());
            if (sumOfMatchedKeywords != zero)
                currentValue += (double) sumOfMatchedKeywords / size;

            result.set(vote, currentValue);
        }
    }

    //veralted
//    private void calcResult()
//    {
//        int size = aCase.getKeywords().size() + aCase.getProfessions().size();
//        if (size != zero)
//        {
//            for (int i = 0; i < answers.size(); i++)
//            {
//                double currentValue = zero;
//                Integer a = i;
//                List<User> entries = voted.entrySet().stream().filter((entry) -> entry.getValue().equals(a)).map(Map.Entry::getKey).toList();
//                for (User user : entries)
//                {
//                    int sumOfMatchedKeywords = calcUserResult(user, aCase.getProfessions(), aCase.getKeywords());
//                    if (sumOfMatchedKeywords != zero)
//                        currentValue += (double) sumOfMatchedKeywords / size;
//                }
//                result.set(i, currentValue);
//            }
//        }
//    }
}
