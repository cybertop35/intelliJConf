package com.music.google;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.google.http.GzipRequestInterceptor;
import com.music.google.http.GzipResponseInterceptor;
import com.music.google.model.Response;
import com.music.google.model.Result;
import com.music.util.Utils;

public class GoogleApi {

	private static final String NEWS_SEARCH_ENDPOINT 	= "http://ajax.googleapis.com/ajax/services/search/news";	
	private static final String WEB_SEARCH_ENDPOINT 	= "http://ajax.googleapis.com/ajax/services/search/web";
	private static final String BOOK_SEARCH_ENDPOINT 	= "http://ajax.googleapis.com/ajax/services/search/books";
	private static final String IMAGE_SEARCH_ENDPOINT 	= "http://ajax.googleapis.com/ajax/services/search/images";
	private static final String VIDEO_SEARCH_ENDPOINT 	= "http://ajax.googleapis.com/ajax/services/search/video";
	private static final String BLOG_SEARCH_ENDPOINT 	= "http://ajax.googleapis.com/ajax/services/search/blogs";
	private static final String UUSER_AGENT 			= "Mozilla/5.0 (Java) Gecko/20081007 gsearch-java-client";
	
	private ObjectMapper jsonMapper;
	private HttpClient httpClient;
	private HttpClientBuilder clientBuilder;
	private RequestConfig.Builder requestBuilder;
	private boolean compressionEnabled = false;	
	private int connectionTimeout = 10000;
	private int socketTimeout = 10000;
	
	
	public static GoogleApi createWithCompression(){
		return new GoogleApi(true);
	}
	
	public static GoogleApi createWithNoCompression(){
		return new GoogleApi(false);
	}
	
	/**
	 * 
	 * @param compressionEnabled
	 */
	protected GoogleApi(boolean compressionEnabled) {

		this.compressionEnabled = compressionEnabled;
		jsonMapper = new ObjectMapper();
		init();
	}


	public void setUserAgent(String ua) {
		init();
	}

	public void setConnectionTimeout(int milliseconds) {
		connectionTimeout = milliseconds;
		init();
	}

	public void setSocketTimeout(int milliseconds) {
		socketTimeout = milliseconds;
		init();
	}

	public boolean isCompressionEnabled() {
		return compressionEnabled;
	}

	public void setCompressionEnabled(boolean b) {
		this.compressionEnabled = b;
	}

	/**
	 * 
	 * 
	 * send HTTP GET
	 * 
	 * This method can be used to retrieve images (JPEG, PNG, GIF) or any other
	 * file type
	 * 
	 * @return byte array
	 * 
	 */
	public byte[] getBytesFromUrl(String url) {
		try {
			HttpGet get = new HttpGet(url);

			HttpResponse response = httpClient.execute(get);

			HttpEntity entity = response.getEntity();

			if (entity == null) {
				throw new RuntimeException("response body was empty");
			}

			return EntityUtils.toByteArray(entity);
		} catch (RuntimeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	/**
	 * 
	 * send HTTP GET
	 * 
	 */
	public String get(String url) {
		try {
			HttpGet get = new HttpGet(url);

			HttpResponse response = httpClient.execute(get);

			HttpEntity entity = response.getEntity();

			if (entity == null) {
				throw new RuntimeException("response body was empty");
			}

			return EntityUtils.toString(entity);
		} catch (RuntimeException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}
	
	public List<Result> searchNews(NewsTopic topic) throws JsonParseException,
			JsonMappingException, IOException {
		return searchNews(null, null, topic);
	}

	public List<Result> searchWeb(String query) throws JsonParseException,
			JsonMappingException, IOException {
		Map<String, String> params = new LinkedHashMap<String, String>();

		params.put("q", query);

		Response r = sendWebSearchRequest(params);

		return r.getResponseData().getResults();
	}

	public List<Result> searchBooks(String query) throws JsonParseException,
			JsonMappingException, IOException {
		Map<String, String> params = new LinkedHashMap<String, String>();

		params.put("q", query);

		Response r = sendBookSearchRequest(params);

		return r.getResponseData().getResults();
	}

	public List<Result> searchImages(String query) throws JsonParseException,
			JsonMappingException, IOException {
		Map<String, String> params = new LinkedHashMap<String, String>();

		params.put("q", query);

		Response r = sendImageSearchRequest(params);

		return r.getResponseData().getResults();
	}

	public List<Result> searchVideos(String query, OrderBy order)
			throws JsonParseException, JsonMappingException, IOException {
		Map<String, String> params = new LinkedHashMap<String, String>();

		params.put("q", query);

		if (order == null) {
			order = OrderBy.RELEVANCE;
		}

		params.put("orderBy", order.getValue());

		Response r = sendVideoSearchRequest(params);

		return r.getResponseData().getResults();
	}

	public List<Result> searchBlogs(String query) throws JsonParseException,
		JsonMappingException, IOException {
		Map<String, String> params = new LinkedHashMap<String, String>();
		
		params.put("q", query);
		
		Response r = sendBlogSearchRequest(params);
		
		return r.getResponseData().getResults();
	}
	
	/**
	 * 
	 * @param location
	 *            use "city, state" (example: "Miami, FL") or zip code ("97202")
	 *            or country ("Singapore")
	 * 
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * 
	 */
	public List<Result> searchNewsByLocation(String location)
			throws JsonParseException, JsonMappingException, IOException {
		return searchNews(null, location, null);
	}
	
	/**
	 * 
	 * @param query
	 *            may be null
	 * @param location
	 *            use "city, state" (example: "Miami, FL") or zip code ("97202")
	 *            or country ("Singapore")
	 * @param topic
	 *            may be null
	 * 
	 * @return
	 * @throws IOException
	 * @throws JsonMappingException
	 * @throws JsonParseException
	 * 
	 */
	public List<Result> searchNews(String query, String location,
			NewsTopic topic) throws JsonParseException, JsonMappingException,
			IOException {
		Map<String, String> params = new LinkedHashMap<String, String>();

		if ((query != null) && (query.trim().length() > 0)) {
			params.put("q", query);
		}

		if (location != null) {
			params.put("geo", location);
		}

		if (topic != null) {
			params.put("topic", topic.getCode());
		}

		Response r = sendNewsSearchRequest(params);

		return r.getResponseData().getResults();
	}
	
	/*************************** Protected Area ***********************/
	
	protected Response sendNewsSearchRequest(Map<String, String> params)
			throws JsonParseException, JsonMappingException, IOException {
		return sendSearchRequest(NEWS_SEARCH_ENDPOINT, params);
	}

	protected Response sendWebSearchRequest(Map<String, String> params)
			throws JsonParseException, JsonMappingException, IOException {
		return sendSearchRequest(WEB_SEARCH_ENDPOINT, params);
	}

	protected Response sendBookSearchRequest(Map<String, String> params)
			throws JsonParseException, JsonMappingException, IOException {
		return sendSearchRequest(BOOK_SEARCH_ENDPOINT, params);
	}

	protected Response sendImageSearchRequest(Map<String, String> params)
			throws JsonParseException, JsonMappingException, IOException {
		return sendSearchRequest(IMAGE_SEARCH_ENDPOINT, params);
	}

	protected Response sendVideoSearchRequest(Map<String, String> params)
			throws JsonParseException, JsonMappingException, IOException {
		return sendSearchRequest(VIDEO_SEARCH_ENDPOINT, params);
	}

	protected Response sendBlogSearchRequest(Map<String, String> params)
			throws JsonParseException, JsonMappingException, IOException {
		return sendSearchRequest(BLOG_SEARCH_ENDPOINT, params);
	}

	protected Response sendSearchRequest(String url, Map<String, String> params)
			throws JsonParseException, JsonMappingException, IOException {

		if (params.get("v") == null) {
			params.put("v", "1.0");
		}

		String json = sendGetHttpRequest(url, params);

		Response r = fromJson(json);

		return r;
	}

	protected String sendGetHttpRequest(String url,	Map<String, String> params) throws IOException {

		HttpGet request = null;
		String queryString = Utils.buildQueryString(params);
		url = url + queryString;
		//System.out.println(url);
		
		request = new HttpGet(url);	

		HttpResponse response = null;
		HttpEntity entity = null;

		try {
			response = httpClient.execute(request);

			int statusCode = response.getStatusLine().getStatusCode();

			if (statusCode != HttpStatus.SC_OK) {
				throw new HttpResponseException(statusCode,"unexpected HTTP response status code = " + statusCode);
			}

			entity = response.getEntity();

			return EntityUtils.toString(entity);
			
		} catch (ClientProtocolException e) {
			throw e;
		} catch (IOException e) {
			throw e;
		} finally {
			if(request != null)
				request.releaseConnection();				
		}

	}
	
	protected Response fromJson(String json) throws JsonParseException,
			JsonMappingException, IOException {
		

		Response r = jsonMapper.readValue(json, Response.class);

		return r;
	}

	
	/****************************** Private Area *************************/
	
	private void init() {
		clientBuilder = HttpClientBuilder.create();
		
		if (compressionEnabled) {
			clientBuilder.addInterceptorFirst(new GzipRequestInterceptor());
			clientBuilder.addInterceptorFirst(new GzipResponseInterceptor());
		}

		clientBuilder.setUserAgent(UUSER_AGENT);

		requestBuilder = RequestConfig.custom();
		requestBuilder.setConnectTimeout(connectionTimeout);
		requestBuilder.setSocketTimeout(socketTimeout);

		clientBuilder.setDefaultRequestConfig(requestBuilder.build());
		httpClient = clientBuilder.build();
	}
}