package main.dataBase;

import validation.Ensure;

public class Location
{
    private final String name;
    private final LocationType type;

    public Location(String name, LocationType type)
    {
        this.name = Ensure.ensureNonNullNonBlankValid(name,"name");
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

    public enum LocationType{
        EUROPE,
        ASIA,
        AFRICA,
        NORTH_AMERICA,
        SOUTH_AMERICA,
        AUSTRALIA,
        ANTARCTICA
    }
}


