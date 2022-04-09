package models;
import java.util.List;

public class ApiResponse {
    private String typeooo;
    private List<Earthquake> features;
    private Metadata metadata;
    private List<Integer> bBox;

    public ApiResponse() {
    }

    public ApiResponse(String type, List<Earthquake> features, Metadata metadata, List<Integer> bBox) {
        this.typeooo = type;
        this.features = features;
        this.metadata = metadata;
        this.bBox = bBox;
    }


    public String getType() {
        return this.typeooo;
    }

    public void setType(String type) {
        this.typeooo = type;
    }

    public List<Earthquake> getFeatures() {
        return this.features;
    }

    public void setFeatures(List<Earthquake> features) {
        this.features = features;
    }

    public Metadata getMetadata() {
        return this.metadata;
    }

    public void setMetadata(Metadata metadata) {
        this.metadata = metadata;
    }

    public List<Integer> getBBox() {
        return this.bBox;
    }

    public void setBBox(List<Integer> bBox) {
        this.bBox = bBox;
    }

}

    
