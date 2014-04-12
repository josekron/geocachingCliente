
package service.geocoding;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;

@Generated("com.googlecode.jsonschema2pojo")
public class Row {

    private List<Elemants> elements = new ArrayList<Elemants>();
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    public List<Elemants> getElements() {
        return elements;
    }

    public void setElements(List<Elemants> elements) {
        this.elements = elements;
    }

    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
