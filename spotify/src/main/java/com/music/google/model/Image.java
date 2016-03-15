package com.music.google.model;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "url",
    "tbUrl",
    "originalContextUrl",
    "publisher",
    "tbWidth",
    "tbHeight"
})
public class Image {

    @JsonProperty("url")
    private String url;
    @JsonProperty("tbUrl")
    private String tbUrl;
    @JsonProperty("originalContextUrl")
    private String originalContextUrl;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("tbWidth")
    private int tbWidth;
    @JsonProperty("tbHeight")
    private int tbHeight;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The url
     */
    @JsonProperty("url")
    public String getUrl() {
        return url;
    }

    /**
     * 
     * @param url
     *     The url
     */
    @JsonProperty("url")
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * 
     * @return
     *     The tbUrl
     */
    @JsonProperty("tbUrl")
    public String getTbUrl() {
        return tbUrl;
    }

    /**
     * 
     * @param tbUrl
     *     The tbUrl
     */
    @JsonProperty("tbUrl")
    public void setTbUrl(String tbUrl) {
        this.tbUrl = tbUrl;
    }

    /**
     * 
     * @return
     *     The originalContextUrl
     */
    @JsonProperty("originalContextUrl")
    public String getOriginalContextUrl() {
        return originalContextUrl;
    }

    /**
     * 
     * @param originalContextUrl
     *     The originalContextUrl
     */
    @JsonProperty("originalContextUrl")
    public void setOriginalContextUrl(String originalContextUrl) {
        this.originalContextUrl = originalContextUrl;
    }

    /**
     * 
     * @return
     *     The publisher
     */
    @JsonProperty("publisher")
    public String getPublisher() {
        return publisher;
    }

    /**
     * 
     * @param publisher
     *     The publisher
     */
    @JsonProperty("publisher")
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * 
     * @return
     *     The tbWidth
     */
    @JsonProperty("tbWidth")
    public int getTbWidth() {
        return tbWidth;
    }

    /**
     * 
     * @param tbWidth
     *     The tbWidth
     */
    @JsonProperty("tbWidth")
    public void setTbWidth(int tbWidth) {
        this.tbWidth = tbWidth;
    }

    /**
     * 
     * @return
     *     The tbHeight
     */
    @JsonProperty("tbHeight")
    public int getTbHeight() {
        return tbHeight;
    }

    /**
     * 
     * @param tbHeight
     *     The tbHeight
     */
    @JsonProperty("tbHeight")
    public void setTbHeight(int tbHeight) {
        this.tbHeight = tbHeight;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
