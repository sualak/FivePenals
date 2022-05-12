package main;

public class Location
{
    private String name;
    private LocationType type;

    public Location(String name, LocationType type)
    {
        this.name = name;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public LocationType getType()
    {
        return type;
    }
}

enum LocationType{

}
