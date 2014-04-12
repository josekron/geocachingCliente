
package service.elevation;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;

@Generated("com.googlecode.jsonschema2pojo")
public class ResultElevation {

    private Double elevation;
    private LocationElevation location;
    private Double resolution;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public Double getElevation() {
        return elevation;
    }

    public void setElevation(Double elevation) {
        this.elevation = elevation;
    }

    public LocationElevation getLocation() {
        return location;
    }

    public void setLocation(LocationElevation location) {
        this.location = location;
    }

    public Double getResolution() {
        return resolution;
    }

    public void setResolution(Double resolution) {
        this.resolution = resolution;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
