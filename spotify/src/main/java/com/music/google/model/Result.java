
package com.music.google.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    "GsearchResultClass",
    "title",
    "titleNoFormatting",
    "published",
    "content",
    "publisher",
    "tbUrl",
    "tbWidth",
    "tbHeight",
    "videoType",
    "url",
    "playUrl",
    "duration",
    
    "unescapedUrl",
    "visibleUrl",
    "cacheUrl",
    
    "clusterUrl",
    "location",
    "publishedDate",
    "signedRedirectUrl",
    "language",
    "image",
    "relatedStories",
    
    "originalContextUrl"
})
public class Result {

    @JsonProperty("GsearchResultClass")
    private String GsearchResultClass;
    @JsonProperty("title")
    private String title;
    @JsonProperty("titleNoFormatting")
    private String titleNoFormatting;
    @JsonProperty("published")
    private String published;
    @JsonProperty("content")
    private String content;
    @JsonProperty("publisher")
    private String publisher;
    @JsonProperty("tbUrl")
    private String tbUrl;
    @JsonProperty("tbWidth")
    private String tbWidth;
    @JsonProperty("tbHeight")
    private String tbHeight;
    @JsonProperty("videoType")
    private String videoType;
    @JsonProperty("url")
    private String url;
    @JsonProperty("playUrl")
    private String playUrl;
    @JsonProperty("duration")
    private String duration;
    
    
    @JsonProperty("unescapedUrl")
    private String unescapedUrl;
    @JsonProperty("visibleUrl")
    private String visibleUrl;
    @JsonProperty("cacheUrl")
    private String cacheUrl;
    
    @JsonProperty("clusterUrl")
    private String clusterUrl;
    @JsonProperty("location")
    private String location;
    @JsonProperty("publishedDate")
    private String publishedDate;
    @JsonProperty("signedRedirectUrl")
    private String signedRedirectUrl;
    @JsonProperty("language")
    private String language;
    @JsonProperty("image")
    private Image image;
    @JsonProperty("relatedStories")
    private List<RelatedStory> relatedStories = new ArrayList<RelatedStory>();
    
    @JsonProperty("originalContextUrl")
    private String originalContextUrl;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The GsearchResultClass
     */
    @JsonProperty("GsearchResultClass")
    public String getGsearchResultClass() {
        return GsearchResultClass;
    }

    /**
     * 
     * @param GsearchResultClass
     *     The GsearchResultClass
     */
    @JsonProperty("GsearchResultClass")
    public void setGsearchResultClass(String GsearchResultClass) {
        this.GsearchResultClass = GsearchResultClass;
    }

    /**
     * 
     * @return
     *     The title
     */
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }

    /**
     * 
     * @param title
     *     The title
     */
    @JsonProperty("title")
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 
     * @return
     *     The titleNoFormatting
     */
    @JsonProperty("titleNoFormatting")
    public String getTitleNoFormatting() {
        return titleNoFormatting;
    }

    /**
     * 
     * @param titleNoFormatting
     *     The titleNoFormatting
     */
    @JsonProperty("titleNoFormatting")
    public void setTitleNoFormatting(String titleNoFormatting) {
        this.titleNoFormatting = titleNoFormatting;
    }

    /**
     * 
     * @return
     *     The published
     */
    @JsonProperty("published")
    public String getPublished() {
        return published;
    }

    /**
     * 
     * @param published
     *     The published
     */
    @JsonProperty("published")
    public void setPublished(String published) {
        this.published = published;
    }

    /**
     * 
     * @return
     *     The content
     */
    @JsonProperty("content")
    public String getContent() {
        return content;
    }

    /**
     * 
     * @param content
     *     The content
     */
    @JsonProperty("content")
    public void setContent(String content) {
        this.content = content;
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
     *     The tbWidth
     */
    @JsonProperty("tbWidth")
    public String getTbWidth() {
        return tbWidth;
    }

    /**
     * 
     * @param tbWidth
     *     The tbWidth
     */
    @JsonProperty("tbWidth")
    public void setTbWidth(String tbWidth) {
        this.tbWidth = tbWidth;
    }

    /**
     * 
     * @return
     *     The tbHeight
     */
    @JsonProperty("tbHeight")
    public String getTbHeight() {
        return tbHeight;
    }

    /**
     * 
     * @param tbHeight
     *     The tbHeight
     */
    @JsonProperty("tbHeight")
    public void setTbHeight(String tbHeight) {
        this.tbHeight = tbHeight;
    }

    /**
     * 
     * @return
     *     The videoType
     */
    @JsonProperty("videoType")
    public String getVideoType() {
        return videoType;
    }

    /**
     * 
     * @param videoType
     *     The videoType
     */
    @JsonProperty("videoType")
    public void setVideoType(String videoType) {
        this.videoType = videoType;
    }

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
     *     The playUrl
     */
    @JsonProperty("playUrl")
    public String getPlayUrl() {
        return playUrl;
    }

    /**
     * 
     * @param playUrl
     *     The playUrl
     */
    @JsonProperty("playUrl")
    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    /**
     * 
     * @return
     *     The duration
     */
    @JsonProperty("duration")
    public String getDuration() {
        return duration;
    }

    /**
     * 
     * @param duration
     *     The duration
     */
    @JsonProperty("duration")
    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getUnescapedUrl() {
		return unescapedUrl;
	}

	public void setUnescapedUrl(String unescapedUrl) {
		this.unescapedUrl = unescapedUrl;
	}

	public String getVisibleUrl() {
		return visibleUrl;
	}

	public void setVisibleUrl(String visibleUrl) {
		this.visibleUrl = visibleUrl;
	}

	public String getCacheUrl() {
		return cacheUrl;
	}

	public void setCacheUrl(String cacheUrl) {
		this.cacheUrl = cacheUrl;
	}
	
	public String getClusterUrl() {
		return clusterUrl;
	}

	public void setClusterUrl(String clusterUrl) {
		this.clusterUrl = clusterUrl;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getPublishedDate() {
		return publishedDate;
	}

	public void setPublishedDate(String publishedDate) {
		this.publishedDate = publishedDate;
	}

	public String getSignedRedirectUrl() {
		return signedRedirectUrl;
	}

	public void setSignedRedirectUrl(String signedRedirectUrl) {
		this.signedRedirectUrl = signedRedirectUrl;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Image getImage() {
		return image;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public List<RelatedStory> getRelatedStories() {
		return relatedStories;
	}

	public void setRelatedStories(List<RelatedStory> relatedStories) {
		this.relatedStories = relatedStories;
	}
	
	public String getOriginalContextUrl() {
		return originalContextUrl;
	}

	public void setOriginalContextUrl(String originalContextUrl) {
		this.originalContextUrl = originalContextUrl;
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
