
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
    "posted",
    "taken",
    "takengranularity",
    "lastupdate"
})
public class Dates {

    @JsonProperty("posted")
    private String posted;
    @JsonProperty("taken")
    private String taken;
    @JsonProperty("takengranularity")
    private String takengranularity;
    @JsonProperty("lastupdate")
    private String lastupdate;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("posted")
    public String getPosted() {
        return posted;
    }

    @JsonProperty("posted")
    public void setPosted(String posted) {
        this.posted = posted;
    }

    @JsonProperty("taken")
    public String getTaken() {
        return taken;
    }

    @JsonProperty("taken")
    public void setTaken(String taken) {
        this.taken = taken;
    }

    @JsonProperty("takengranularity")
    public String getTakengranularity() {
        return takengranularity;
    }

    @JsonProperty("takengranularity")
    public void setTakengranularity(String takengranularity) {
        this.takengranularity = takengranularity;
    }

    @JsonProperty("lastupdate")
    public String getLastupdate() {
        return lastupdate;
    }

    @JsonProperty("lastupdate")
    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
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
