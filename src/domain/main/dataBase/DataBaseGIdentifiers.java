package main.dataBase;




import validation.Ensure;

import java.io.*;
import java.util.*;

import static java.lang.String.format;


public class DataBaseGIdentifiers implements Serializable
{
    private final Map<Integer, Professions> allProfessions = new HashMap<>();
    private final Map<Integer, Keywords> allKeywords = new HashMap<>();

    // constructor
    public DataBaseGIdentifiers(String FilePathProfessions, String FilePathKeywords)
    {
        initalize(FilePathProfessions, FilePathKeywords);
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
        return allProfessions.get(Ensure.ensureKeyIsValid(key, allProfessions));
    }

    public Keywords getKeyword(int key)
    {
        return allKeywords.get(Ensure.ensureKeyIsValid(key, allKeywords));
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
    private void initalize(String FilePathProfessions, String FilePathKeywords)
    {
        readInProfessions(Objects.requireNonNull(FilePathProfessions, "FilePathProfessions"));
        readInKeywords(Objects.requireNonNull(FilePathKeywords, "FilePathKeywords"));
    }

    private void readInProfessions(String FilePathProfessions)
    {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FilePathProfessions))) {
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
            allProfessions.put( i , new Professions(records.get(i).get(0), Professions.ProfessionType.valueOf(records.get(i).get(1))));
        }
    }

    private void readInKeywords(String FilePathKeywords)
    {
        List<List<String>> records = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FilePathKeywords))) {
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
            allKeywords.put(i , new Keywords(records.get(i).get(0), Keywords.KeywordType.valueOf(records.get(i).get(1))));
        }
    }

    public void serializeProfessions(String filename) throws DataBaseException
    {
        Objects.requireNonNull(filename, "filname");

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            out.writeObject(allProfessions);
            System.out.println("Succes");
        } catch (FileNotFoundException e)
        {
            throw new DataBaseException(format("Beim öffnen von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));

        } catch (IOException e)
        {
            throw new DataBaseException(format("Beim Serialisieren in %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        }
    }

    public void unSerializeProffesions(String filename) throws DataBaseException
    {
        Objects.requireNonNull(filename, "filname");

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename)))
        {

            Object o = in.readObject();
            Map<Integer, Professions> o1 = (Map<Integer, Professions>) o;
            allProfessions.putAll(o1);
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

    public void serializeKeywords(String filename) throws DataBaseException
    {
        Objects.requireNonNull(filename, "filname");

        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            out.writeObject(allKeywords);
            System.out.println("Succes");
        } catch (FileNotFoundException e)
        {
            throw new DataBaseException(format("Beim öffnen von %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));

        } catch (IOException e)
        {
            throw new DataBaseException(format("Beim Serialisieren in %s ist leider der Fehler %s aufgetreten", filename, e.getMessage()));
        }
    }

    public void unSerializeKeywords(String filename) throws DataBaseException
    {
        Objects.requireNonNull(filename, "filname");

        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename)))
        {
            Object o = in.readObject();
            Map<Integer, Keywords> o1 = (Map<Integer, Keywords>) o;
            allKeywords.putAll(o1);
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
        {
            DataBaseGIdentifiers o = (DataBaseGIdentifiers)in.readObject();
            allKeywords.putAll(o.getAllKeywords());
            allProfessions.putAll(o.getAllProfessions());
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
