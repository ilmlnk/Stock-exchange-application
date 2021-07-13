package sample;

public class Lot {
    private String type;
    private String name;
    private double startValue;
    private int creationYear;

    public Lot(String type, String name, double startValue, int creationYear){
        this.type = type;
        this.name = name;
        this.startValue = startValue;
        this.creationYear = creationYear;
    }

    public void setType(String type) { this.type = type; }
    public String getType() { return type; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public void setStartValue(double startValue) { this.startValue = startValue; }
    public double getStartValue() { return startValue; }

    public void setCreationYear(int creationYear) { this.creationYear = creationYear; }
    public int getCreationYear() { return creationYear; }
}
