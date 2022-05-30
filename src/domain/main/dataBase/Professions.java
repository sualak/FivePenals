package main.dataBase;

public class Professions
{
    private final String name;
    private final ProfessionType type;

    public Professions(String name, ProfessionType type)
    {
        this.name = name;
        this.type = type;
    }

    public String getName()
    {
        return name;
    }

    public ProfessionType getType()
    {
        return type;
    }

    public enum ProfessionType{
        IMMUNOLOGIST,
        ANESTHESIOLOGIST,
        CARDIOLOGIST,
        COLON_AND_RECTAL_SURGEON,
        DERMATOLOGIST,
        ENDOCRINOLOGIST,
        EMERGENCY_MEDICINE_SPECIALIST,
        GASTROENTEROLOGIST,
        GERIATRIC_MEDICINE_SPECIALIST,
        HEMATOLOGIST,
        HOSPICE_AND_PALLIATIVE_MEDICINE_SPECIALIST,
        INFECTIOUS_DISEASE_SPECIALIST,
        INTERNIST,
        MEDICAL_GENETICIST,
        NEPHROLOGIST,
        NEUROLOGIST,
        OBSTETRICIAN_AND_GYNECOLOGIST,
        ONCOLOGIST,
        OPHTALMOLOGIST,
        OSTEPATH,
        OTOLARYNOLOGIST,
        PATHOLOGIST,
        PEDIATRICAN,
        PHYSIATRIST,
        PLSASTIC_SURGEON,
        PODIATRIST,
        PREVENTIVE_MEDICINE_SPECIALIST,
        PSYCHIATRIST,
        PULMONOLOGIST,
        RHEUMATOLOGIST,
        SLEEP_MEDICINE_SPECIALIST,
        SPORTS_MEDICINE_SPECIALIST,
        GENERAL_SURGEON,
        UROLOGIST;
    }
}


