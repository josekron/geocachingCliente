
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
    "id",
    "secret",
    "server",
    "farm",
    "dateuploaded",
    "isfavorite",
    "license",
    "safety_level",
    "rotation",
    "owner",
    "title",
    "description",
    "visibility",
    "dates",
    "views",
    "editability",
    "publiceditability",
    "usage",
    "comments",
    "notes",
    "people",
    "tags",
    "location",
    "geoperms",
    "urls",
    "media"
})
public class Photo {

    @JsonProperty("id")
    private String id;
    @JsonProperty("secret")
    private String secret;
    @JsonProperty("server")
    private String server;
    @JsonProperty("farm")
    private Integer farm;
    @JsonProperty("dateuploaded")
    private String dateuploaded;
    @JsonProperty("isfavorite")
    private Integer isfavorite;
    @JsonProperty("license")
    private String license;
    @JsonProperty("safety_level")
    private String safety_level;
    @JsonProperty("rotation")
    private Integer rotation;
    @JsonProperty("owner")
    private Owner owner;
    @JsonProperty("title")
    private Title title;
    @JsonProperty("description")
    private Description description;
    @JsonProperty("visibility")
    private Visibility visibility;
    @JsonProperty("dates")
    private Dates dates;
    @JsonProperty("views")
    private String views;
    @JsonProperty("editability")
    private Editability editability;
    @JsonProperty("publiceditability")
    private Publiceditability publiceditability;
    @JsonProperty("usage")
    private Usage usage;
    @JsonProperty("comments")
    private Comments comments;
    @JsonProperty("notes")
    private Notes notes;
    @JsonProperty("people")
    private People people;
    @JsonProperty("tags")
    private Tags tags;
    @JsonProperty("location")
    private Location location;
    @JsonProperty("geoperms")
    private Geoperms geoperms;
    @JsonProperty("urls")
    private Urls urls;
    @JsonProperty("media")
    private String media;
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public String getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    @JsonProperty("secret")
    public String getSecret() {
        return secret;
    }

    @JsonProperty("secret")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @JsonProperty("server")
    public String getServer() {
        return server;
    }

    @JsonProperty("server")
    public void setServer(String server) {
        this.server = server;
    }

    @JsonProperty("farm")
    public Integer getFarm() {
        return farm;
    }

    @JsonProperty("farm")
    public void setFarm(Integer farm) {
        this.farm = farm;
    }

    @JsonProperty("dateuploaded")
    public String getDateuploaded() {
        return dateuploaded;
    }

    @JsonProperty("dateuploaded")
    public void setDateuploaded(String dateuploaded) {
        this.dateuploaded = dateuploaded;
    }

    @JsonProperty("isfavorite")
    public Integer getIsfavorite() {
        return isfavorite;
    }

    @JsonProperty("isfavorite")
    public void setIsfavorite(Integer isfavorite) {
        this.isfavorite = isfavorite;
    }

    @JsonProperty("license")
    public String getLicense() {
        return license;
    }

    @JsonProperty("license")
    public void setLicense(String license) {
        this.license = license;
    }

    @JsonProperty("safety_level")
    public String getSafety_level() {
        return safety_level;
    }

    @JsonProperty("safety_level")
    public void setSafety_level(String safety_level) {
        this.safety_level = safety_level;
    }

    @JsonProperty("rotation")
    public Integer getRotation() {
        return rotation;
    }

    @JsonProperty("rotation")
    public void setRotation(Integer rotation) {
        this.rotation = rotation;
    }

    @JsonProperty("owner")
    public Owner getOwner() {
        return owner;
    }

    @JsonProperty("owner")
    public void setOwner(Owner owner) {
        this.owner = owner;
    }

    @JsonProperty("title")
    public Title getTitle() {
        return title;
    }

    @JsonProperty("title")
    public void setTitle(Title title) {
        this.title = title;
    }

    @JsonProperty("description")
    public Description getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(Description description) {
        this.description = description;
    }

    @JsonProperty("visibility")
    public Visibility getVisibility() {
        return visibility;
    }

    @JsonProperty("visibility")
    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    @JsonProperty("dates")
    public Dates getDates() {
        return dates;
    }

    @JsonProperty("dates")
    public void setDates(Dates dates) {
        this.dates = dates;
    }

    @JsonProperty("views")
    public String getViews() {
        return views;
    }

    @JsonProperty("views")
    public void setViews(String views) {
        this.views = views;
    }

    @JsonProperty("editability")
    public Editability getEditability() {
        return editability;
    }

    @JsonProperty("editability")
    public void setEditability(Editability editability) {
        this.editability = editability;
    }

    @JsonProperty("publiceditability")
    public Publiceditability getPubliceditability() {
        return publiceditability;
    }

    @JsonProperty("publiceditability")
    public void setPubliceditability(Publiceditability publiceditability) {
        this.publiceditability = publiceditability;
    }

    @JsonProperty("usage")
    public Usage getUsage() {
        return usage;
    }

    @JsonProperty("usage")
    public void setUsage(Usage usage) {
        this.usage = usage;
    }

    @JsonProperty("comments")
    public Comments getComments() {
        return comments;
    }

    @JsonProperty("comments")
    public void setComments(Comments comments) {
        this.comments = comments;
    }

    @JsonProperty("notes")
    public Notes getNotes() {
        return notes;
    }

    @JsonProperty("notes")
    public void setNotes(Notes notes) {
        this.notes = notes;
    }

    @JsonProperty("people")
    public People getPeople() {
        return people;
    }

    @JsonProperty("people")
    public void setPeople(People people) {
        this.people = people;
    }

    @JsonProperty("tags")
    public Tags getTags() {
        return tags;
    }

    @JsonProperty("tags")
    public void setTags(Tags tags) {
        this.tags = tags;
    }

    @JsonProperty("location")
    public Location getLocation() {
        return location;
    }

    @JsonProperty("location")
    public void setLocation(Location location) {
        this.location = location;
    }

    @JsonProperty("geoperms")
    public Geoperms getGeoperms() {
        return geoperms;
    }

    @JsonProperty("geoperms")
    public void setGeoperms(Geoperms geoperms) {
        this.geoperms = geoperms;
    }

    @JsonProperty("urls")
    public Urls getUrls() {
        return urls;
    }

    @JsonProperty("urls")
    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    @JsonProperty("media")
    public String getMedia() {
        return media;
    }

    @JsonProperty("media")
    public void setMedia(String media) {
        this.media = media;
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
