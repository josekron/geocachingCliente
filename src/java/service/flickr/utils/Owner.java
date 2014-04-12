
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
    "nsid",
    "username",
    "realname",
    "location",
    "iconserver",
    "iconfarm",
    "path_alias"
})
public class Owner {

    @JsonProperty("nsid")
    private String nsid;
    @JsonProperty("username")
    private String username;
    @JsonProperty("realname")
    private String realname;
    @JsonProperty("location")
    private String location;
    @JsonProperty("iconserver")
    private String iconserver;
    @JsonProperty("iconfarm")
    private Integer iconfarm;
    @JsonProperty("path_alias")
    private String path_alias;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("nsid")
    public String getNsid() {
        return nsid;
    }

    @JsonProperty("nsid")
    public void setNsid(String nsid) {
        this.nsid = nsid;
    }

    @JsonProperty("username")
    public String getUsername() {
        return username;
    }

    @JsonProperty("username")
    public void setUsername(String username) {
        this.username = username;
    }

    @JsonProperty("realname")
    public String getRealname() {
        return realname;
    }

    @JsonProperty("realname")
    public void setRealname(String realname) {
        this.realname = realname;
    }

    @JsonProperty("location")
    public String getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(String location) {
        this.location = location;
    }

    @JsonProperty("iconserver")
    public String getIconserver() {
        return iconserver;
    }

    @JsonProperty("iconserver")
    public void setIconserver(String iconserver) {
        this.iconserver = iconserver;
    }

    @JsonProperty("iconfarm")
    public Integer getIconfarm() {
        return iconfarm;
    }

    @JsonProperty("iconfarm")
    public void setIconfarm(Integer iconfarm) {
        this.iconfarm = iconfarm;
    }

    @JsonProperty("path_alias")
    public String getPath_alias() {
        return path_alias;
    }

    @JsonProperty("path_alias")
    public void setPath_alias(String path_alias) {
        this.path_alias = path_alias;
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
