
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
    "_content",
    "place_id",
    "woeid"
})
public class Region {

    @JsonProperty("_content")
    private String _content;
    @JsonProperty("place_id")
    private String place_id;
    @JsonProperty("woeid")
    private String woeid;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("_content")
    public String get_content() {
        return _content;
    }

    @JsonProperty("_content")
    public void set_content(String _content) {
        this._content = _content;
    }

    @JsonProperty("place_id")
    public String getPlace_id() {
        return place_id;
    }

    @JsonProperty("place_id")
    public void setPlace_id(String place_id) {
        this.place_id = place_id;
    }

    @JsonProperty("woeid")
    public String getWoeid() {
        return woeid;
    }

    @JsonProperty("woeid")
    public void setWoeid(String woeid) {
        this.woeid = woeid;
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
