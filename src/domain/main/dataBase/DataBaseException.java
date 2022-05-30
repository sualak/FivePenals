package main.dataBase;

public class DataBaseException extends Exception
{
    public DataBaseException(String massage)
    {
        super(massage);
    }

    public DataBaseException(String massage, Throwable couse)
    {
        super(massage);
    }
}
