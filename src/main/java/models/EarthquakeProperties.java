package models;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class EarthquakeProperties {
    private String place;
    private String mag;
    private Long time;
    private String country;
    private String date;
    private String clock;

    public EarthquakeProperties() {
    }

    public EarthquakeProperties(String place, String mag, Long time) {
        this.place = place;
        this.mag = mag;
        this.time = time;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getMag() {
        return this.mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public Long getTime() {
        return this.time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getClock() {
        return this.clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
