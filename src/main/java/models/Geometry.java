package models;

import java.util.List;

public class Geometry{
    private String type;
    private List<Double> coordinates;

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Double> getCoordinates() {
        return this.coordinates;
    }

    public void setType(List<Double> coordinates) {
        this.coordinates = coordinates;
    }

} 
