
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
    "cancomment",
    "canaddmeta"
})
public class Editability {

    @JsonProperty("cancomment")
    private Integer cancomment;
    @JsonProperty("canaddmeta")
    private Integer canaddmeta;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("cancomment")
    public Integer getCancomment() {
        return cancomment;
    }

    @JsonProperty("cancomment")
    public void setCancomment(Integer cancomment) {
        this.cancomment = cancomment;
    }

    @JsonProperty("canaddmeta")
    public Integer getCanaddmeta() {
        return canaddmeta;
    }

    @JsonProperty("canaddmeta")
    public void setCanaddmeta(Integer canaddmeta) {
        this.canaddmeta = canaddmeta;
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
