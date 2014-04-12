
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
    "latitude",
    "longitude",
    "accuracy",
    "context",
    "locality",
    "county",
    "region",
    "country",
    "place_id",
    "woeid"
})
public class Location {

    @JsonProperty("latitude")
    private Double latitude;
    @JsonProperty("longitude")
    private Double longitude;
    @JsonProperty("accuracy")
    private String accuracy;
    @JsonProperty("context")
    private String context;
    @JsonProperty("locality")
    private Locality locality;
    @JsonProperty("county")
    private County county;
    @JsonProperty("region")
    private Region region;
    @JsonProperty("country")
    private Country country;
    @JsonProperty("place_id")
    private String place_id;
    @JsonProperty("woeid")
    private String woeid;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("latitude")
    public Double getLatitude() {
        return latitude;
    }

    @JsonProperty("latitude")
    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    @JsonProperty("longitude")
    public Double getLongitude() {
        return longitude;
    }

    @JsonProperty("longitude")
    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    @JsonProperty("accuracy")
    public String getAccuracy() {
        return accuracy;
    }

    @JsonProperty("accuracy")
    public void setAccuracy(String accuracy) {
        this.accuracy = accuracy;
    }

    @JsonProperty("context")
    public String getContext() {
        return context;
    }

    @JsonProperty("context")
    public void setContext(String context) {
        this.context = context;
    }

    @JsonProperty("locality")
    public Locality getLocality() {
        return locality;
    }

    @JsonProperty("locality")
    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    @JsonProperty("county")
    public County getCounty() {
        return county;
    }

    @JsonProperty("county")
    public void setCounty(County county) {
        this.county = county;
    }

    @JsonProperty("region")
    public Region getRegion() {
        return region;
    }

    @JsonProperty("region")
    public void setRegion(Region region) {
        this.region = region;
    }

    @JsonProperty("country")
    public Country getCountry() {
        return country;
    }

    @JsonProperty("country")
    public void setCountry(Country country) {
        this.country = country;
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
