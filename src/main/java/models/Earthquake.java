package models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class Earthquake {

    private String type;
    private String id;
    private Geometry geometry;
    private EarthquakeProperties properties;

    public Earthquake() {
    }

    public Earthquake(String type, String id, Geometry geometry, EarthquakeProperties properties) {
        this.type = type;
        this.id = id;
        this.geometry = geometry;
        this.properties = properties;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Geometry getGeometry() {
        return this.geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public EarthquakeProperties getProperties() {
        return this.properties;
    }

    public void setProperties(EarthquakeProperties properties) {
        this.properties = properties;
    }
    

    
}
