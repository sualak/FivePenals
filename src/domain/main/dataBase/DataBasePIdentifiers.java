package main.dataBase;


import validation.Ensure;

import java.io.*;
import java.util.*;

import static java.lang.String.format;

public class DataBasePIdentifiers
{
    private final Map<Integer, Location> allLocations = new HashMap<>();
    private final Map<Integer, Language> allLanguages = new HashMap<>();

    // constructor
    public DataBasePIdentifiers(String FilePathLocations, String FilePathLanguages)
    {
        initalize(FilePathLocations, FilePathLanguages);
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
        return allLocations.get(Ensure.ensureKeyIsValid(key, allLocations));
    }

    public Language getLanguage(int key)
    {
        return allLanguages.get(Ensure.ensureKeyIsValid(key, allLanguages));
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
    private void initalize(String FilePathLocations, String FilePathLanguages)
    {
        readInLocations(Objects.requireNonNull(FilePathLocations, "FilePathLocations"));
        readInLanguages(Objects.requireNonNull(FilePathLanguages, "FilePathLanguages"));
    }

    private void readInLocations(String FilePathLocations)
    {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FilePathLocations))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            //intentianlay ignored
        }

        for (int i = 0; i < records.size(); i++)
        {
            allLocations.put( i , new Location(records.get(i).get(0), Location.LocationType.valueOf(records.get(i).get(1))));
        }
    }

    private void readInLanguages(String FilePathLanguages)
    {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FilePathLanguages))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                records.add(Arrays.asList(values));
            }
        }
        catch (Exception e)
        {
            System.err.println(e.getMessage());
            //intentianlay ignored
        }

        for (int i = 0; i < records.size(); i++)
        {
            allLanguages.put( i , new Language(records.get(i).get(0), Language.LanguagType.valueOf(records.get(i).get(1))));
        }
    }

    public void serializeLanguages(String filename) throws DataBaseException
    {
        Objects.requireNonNull(filename, "filname");

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            out.writeObject(allLanguages);
            System.out.println("Succes");
        } catch (FileNotFoundException e)
        {
            throw new DataBaseException(format("Beim öffnen von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));

        } catch (IOException e)
        {
            throw new DataBaseException(format("Beim Serialisieren in %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        }
    }

    public void unSerializeLanguages(String filename) throws DataBaseException
    {
        Objects.requireNonNull(filename, "filname");

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename)))
        {
            Object o = in.readObject();
            Map<Integer, Language> o1 = (Map<Integer, Language>) o;
            allLanguages.putAll(o1);
            System.out.println("Deserialized Data: \n" + in.readObject().toString());
            System.out.println("Succes");
        } catch (FileNotFoundException e) {
            throw new DataBaseException(format("Beim öffnen von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        } catch (IOException e)
        {
            throw new DataBaseException(format("Beim Deserialisieren von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        } catch (ClassNotFoundException e)
        {
            throw new DataBaseException(format("Bei der suche der Classe von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        }
    }

    public void serializeLocations(String filename) throws DataBaseException
    {
        Objects.requireNonNull(filename, "filname");

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            out.writeObject(allLocations);
            System.out.println("Succes");
        } catch (FileNotFoundException e)
        {
            throw new DataBaseException(format("Beim öffnen von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));

        } catch (IOException e)
        {
            throw new DataBaseException(format("Beim Serialisieren in %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        }
    }

    public void unSerializeLocations(String filename) throws DataBaseException
    {
        Objects.requireNonNull(filename, "filname");

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename)))
        {   Object o = in.readObject();
            Map<Integer, Location> o1 = (Map<Integer, Location>) o;
            allLocations.putAll(o1);
            System.out.println("Deserialized Data: \n" + in.readObject().toString());
            System.out.println("Succes");
        } catch (FileNotFoundException e) {
            throw new DataBaseException(format("Beim öffnen von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        } catch (IOException e)
        {
            throw new DataBaseException(format("Beim Deserialisieren von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        } catch (ClassNotFoundException e)
        {
            throw new DataBaseException(format("Bei der suche der Classe von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        }
    }

    public void serialize(String filename) throws DataBaseException
    {
        Objects.requireNonNull(filename, "filname");

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            out.writeObject(this);
            System.out.println("Succes");
        } catch (FileNotFoundException e)
        {
            throw new DataBaseException(format("Beim öffnen von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));

        } catch (IOException e)
        {
            throw new DataBaseException(format("Beim Serialisieren in %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        }
    }

    public void unSerialize(String filename) throws DataBaseException
    {
        Objects.requireNonNull(filename, "filname");

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename)))
        {   DataBasePIdentifiers o = (DataBasePIdentifiers) in.readObject();
            allLanguages.putAll(o.getAllLanguages());
            allLocations.putAll(o.getAllLocations());
            System.out.println("Deserialized Data: \n" + in.readObject().toString());
            System.out.println("Succes");
        } catch (FileNotFoundException e) {
            throw new DataBaseException(format("Beim öffnen von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        } catch (IOException e)
        {
            throw new DataBaseException(format("Beim Deserialisieren von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        } catch (ClassNotFoundException e)
        {
            throw new DataBaseException(format("Bei der suche der Classe von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        }
    }
}
