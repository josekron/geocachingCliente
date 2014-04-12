
package service.flickr.utils;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("com.googlecode.jsonschema2pojo")
@JsonPropertyOrder({
    "haspeople"
})
public class People {

    @JsonProperty("haspeople")
    private Integer haspeople;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("haspeople")
    public Integer getHaspeople() {
        return haspeople;
    }

    @JsonProperty("haspeople")
    public void setHaspeople(Integer haspeople) {
        this.haspeople = haspeople;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperties(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
