
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
    "pages",
    "estimatedResultCount",
    "currentPageIndex",
    "moreResultsUrl",
    
    "resultCount",
    "searchResultTime"
})
public class Cursor {

    @JsonProperty("pages")
    private List<Page> pages = new ArrayList<Page>();
    @JsonProperty("estimatedResultCount")
    private String estimatedResultCount;
    @JsonProperty("currentPageIndex")
    private int currentPageIndex;
    @JsonProperty("moreResultsUrl")
    private String moreResultsUrl;
    
    @JsonProperty("resultCount")
    private String resultCount;
    @JsonProperty("searchResultTime")
    private String searchResultTime;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The pages
     */
    @JsonProperty("pages")
    public List<Page> getPages() {
        return pages;
    }

    /**
     * 
     * @param pages
     *     The pages
     */
    @JsonProperty("pages")
    public void setPages(List<Page> pages) {
        this.pages = pages;
    }

    /**
     * 
     * @return
     *     The estimatedResultCount
     */
    @JsonProperty("estimatedResultCount")
    public String getEstimatedResultCount() {
        return estimatedResultCount;
    }

    /**
     * 
     * @param estimatedResultCount
     *     The estimatedResultCount
     */
    @JsonProperty("estimatedResultCount")
    public void setEstimatedResultCount(String estimatedResultCount) {
        this.estimatedResultCount = estimatedResultCount;
    }

    /**
     * 
     * @return
     *     The currentPageIndex
     */
    @JsonProperty("currentPageIndex")
    public int getCurrentPageIndex() {
        return currentPageIndex;
    }

    /**
     * 
     * @param currentPageIndex
     *     The currentPageIndex
     */
    @JsonProperty("currentPageIndex")
    public void setCurrentPageIndex(int currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }

    /**
     * 
     * @return
     *     The moreResultsUrl
     */
    @JsonProperty("moreResultsUrl")
    public String getMoreResultsUrl() {
        return moreResultsUrl;
    }

    /**
     * 
     * @param moreResultsUrl
     *     The moreResultsUrl
     */
    @JsonProperty("moreResultsUrl")
    public void setMoreResultsUrl(String moreResultsUrl) {
        this.moreResultsUrl = moreResultsUrl;
    }   
    
    public String getResultCount() {
		return resultCount;
	}

	public void setResultCount(String resultCount) {
		this.resultCount = resultCount;
	}

	public String getSearchResultTime() {
		return searchResultTime;
	}

	public void setSearchResultTime(String searchResultTime) {
		this.searchResultTime = searchResultTime;
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
