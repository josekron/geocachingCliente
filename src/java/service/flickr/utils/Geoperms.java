
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
    "ispublic",
    "iscontact",
    "isfriend",
    "isfamily"
})
public class Geoperms {

    @JsonProperty("ispublic")
    private Integer ispublic;
    @JsonProperty("iscontact")
    private Integer iscontact;
    @JsonProperty("isfriend")
    private Integer isfriend;
    @JsonProperty("isfamily")
    private Integer isfamily;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("ispublic")
    public Integer getIspublic() {
        return ispublic;
    }

    @JsonProperty("ispublic")
    public void setIspublic(Integer ispublic) {
        this.ispublic = ispublic;
    }

    @JsonProperty("iscontact")
    public Integer getIscontact() {
        return iscontact;
    }

    @JsonProperty("iscontact")
    public void setIscontact(Integer iscontact) {
        this.iscontact = iscontact;
    }

    @JsonProperty("isfriend")
    public Integer getIsfriend() {
        return isfriend;
    }

    @JsonProperty("isfriend")
    public void setIsfriend(Integer isfriend) {
        this.isfriend = isfriend;
    }

    @JsonProperty("isfamily")
    public Integer getIsfamily() {
        return isfamily;
    }

    @JsonProperty("isfamily")
    public void setIsfamily(Integer isfamily) {
        this.isfamily = isfamily;
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
