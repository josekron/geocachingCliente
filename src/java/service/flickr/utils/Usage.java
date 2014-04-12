
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
    "candownload",
    "canblog",
    "canprint",
    "canshare"
})
public class Usage {

    @JsonProperty("candownload")
    private Integer candownload;
    @JsonProperty("canblog")
    private Integer canblog;
    @JsonProperty("canprint")
    private Integer canprint;
    @JsonProperty("canshare")
    private Integer canshare;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("candownload")
    public Integer getCandownload() {
        return candownload;
    }

    @JsonProperty("candownload")
    public void setCandownload(Integer candownload) {
        this.candownload = candownload;
    }

    @JsonProperty("canblog")
    public Integer getCanblog() {
        return canblog;
    }

    @JsonProperty("canblog")
    public void setCanblog(Integer canblog) {
        this.canblog = canblog;
    }

    @JsonProperty("canprint")
    public Integer getCanprint() {
        return canprint;
    }

    @JsonProperty("canprint")
    public void setCanprint(Integer canprint) {
        this.canprint = canprint;
    }

    @JsonProperty("canshare")
    public Integer getCanshare() {
        return canshare;
    }

    @JsonProperty("canshare")
    public void setCanshare(Integer canshare) {
        this.canshare = canshare;
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
