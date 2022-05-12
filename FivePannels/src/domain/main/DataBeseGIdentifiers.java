package main;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;


public class DataBeseGIdentifiers
{
    private final Map<Integer, Professions> allProfessions = new HashMap<>();
    private final Map<Integer, Keywords> allKeywords = new HashMap<>();

    // constructor
    public DataBeseGIdentifiers() throws IOException
    {
        initalize();
    }


    //getter
    public Map<Integer, Professions> getAllProfessions()
    {
        return Collections.unmodifiableMap(allProfessions);
    }

    public Map<Integer, Keywords> getAllKeywords()
    {
        return Collections.unmodifiableMap(allKeywords);
    }

    public Professions getProfession(int key)
    {
        return allProfessions.get(key);
    }

    public Keywords getKeyword(int key)
    {
        return allKeywords.get(key);
    }

    //to String
    public String allProfessionsToString()
    {
        StringBuilder sb = new StringBuilder();
        allProfessions.forEach((key, value) -> sb.append(key).append(" ")
                .append(value.getName()).append(" ")
                .append(value.getType()).append(" ")
                .append("\n"));
        return sb.toString();
    }

    public String allKeywordsToString()
    {
        StringBuilder sb = new StringBuilder();
        allProfessions.forEach((key, value) -> sb.append(key).append(" ")
                .append(value.getName()).append(" ")
                .append(value.getType()).append(" ")
                .append("\n"));
        return sb.toString();
    }


    //private
    private void initalize() throws IOException
    {
        readInProfessions();
        readInKeywords();
    }

    private void readInProfessions() throws IOException
    {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\suala\\Downloads\\Professions.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        }

        for (int i = 0; i < records.size(); i++)
        {
            allProfessions.put( i , new Professions(records.get(i).get(0), ProfessionType.valueOf(records.get(i).get(1))));
        }
    }

    private void readInKeywords() throws IOException
    {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\suala\\Downloads\\Keywords.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        }

        for (int i = 0; i < records.size(); i++)
        {
            allKeywords.put( i , new Keywords(records.get(i).get(0), KeywordType.valueOf(records.get(i).get(1))));
        }
    }
}
