package main.dataBase;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class DataBasePIdentifiers
{
    private final Map<Integer, Location> allLocations = new HashMap<>();
    private final Map<Integer, Language> allLanguages = new HashMap<>();

    // constructor
    public DataBasePIdentifiers() throws IOException
    {
        initalize();
    }

    //getter
    public Map<Integer, Location> getAllLocations()
    {
        return Collections.unmodifiableMap(allLocations);
    }

    public Map<Integer, Language> getAllLanguages()
    {
        return Collections.unmodifiableMap(allLanguages);
    }

    public Location getLocation(int key)
    {
        return allLocations.get(key);
    }

    public Language getLanguage(int key)
    {
        return allLanguages.get(key);
    }

    //to String
    public String allLocationsToString()
    {
        StringBuilder sb = new StringBuilder();
        allLocations.forEach((key, value) -> sb.append(key).append(" ")
                .append(value.getName()).append(" ")
                .append(value.getType()).append(" ")
                .append("\n"));
        return sb.toString();
    }

    public String allLanguagesToString()
    {
        StringBuilder sb = new StringBuilder();
        allLanguages.forEach((key, value) -> sb.append(key).append(" ")
                .append(value.getName()).append(" ")
                .append(value.getType()).append(" ")
                .append("\n"));
        return sb.toString();
    }


    //private
    private void initalize() throws IOException
    {
        readInLocations();
        readInLanguages();
    }

    private void readInLocations() throws IOException
    {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("C:\\Users\\suala\\Downloads\\Locations.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        }

        for (int i = 0; i < records.size(); i++)
        {
            allLocations.put( i , new Location(records.get(i).get(0), Location.LocationType.valueOf(records.get(i).get(1))));
        }
    }

    private void readInLanguages() throws IOException
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
            allLanguages.put( i , new Language(records.get(i).get(0), Language.LanguagType.valueOf(records.get(i).get(1))));
        }
    }
}
