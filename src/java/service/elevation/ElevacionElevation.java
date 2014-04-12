
package service.elevation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("com.googlecode.jsonschema2pojo")
public class ElevacionElevation {

    private List<ResultElevation> results = new ArrayList<ResultElevation>();
    private String status;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<ResultElevation> getResults() {
        return results;
    }

    public void setResults(List<ResultElevation> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
