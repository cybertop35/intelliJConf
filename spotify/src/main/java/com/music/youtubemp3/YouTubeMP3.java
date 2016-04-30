/**
 *
 */
package com.music.youtubemp3;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.commons.io.output.ByteArrayOutputStream;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultRedirectStrategy;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import com.music.downloadmanager.iface.IDownloadFile;
import com.music.downloadmanager.iface.ProgressCallBack;
import com.music.util.Utils;

public class YouTubeMP3 implements IDownloadFile {

	/**
	 * The default buffer size to use.
	 */
	private static final int DEFAULT_BUFFER_SIZE = 1024 * 4;
	private static final String BASE_URL = "http://www.youtube-mp3.org";
	private static final String GET_URL = BASE_URL+"/get";
	private static final String ITEM_INFO_URL = BASE_URL+"/a/itemInfo/";
	private static final String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.1 (KHTML, like Gecko) Chrome/22.0.1207.1 Safari/537.1";
	private static final String ACCEPT_LOCATION = "*";
	private static final String REFERRER = "http://www.youtube-mp3.org";
	
	private HttpClient httpClient;
	private HttpClientBuilder clientBuilder;

	public static IDownloadFile create(){
		return new YouTubeMP3();
	}
	
	protected YouTubeMP3() {
		clientBuilder = HttpClientBuilder.create();
		clientBuilder.setUserAgent(USER_AGENT);
		clientBuilder.setRedirectStrategy(new DefaultRedirectStrategy());
		httpClient = clientBuilder.build();

	}

	@Override
	public byte[] getMP3ToByeArray(String id, ProgressCallBack callBack)
			throws ClientProtocolException, IOException,IllegalArgumentException {

		Iteminfo item = getItemInfo(id);
		if (item == null) {
			throw new IllegalArgumentException("Invalid Item Info");
		}
		return getItemStream(item, callBack);
	}

	@Override
	public byte[] getMP3ToByeArray(String id) throws ClientProtocolException,
			IOException,IllegalArgumentException {

		Iteminfo item = getItemInfo(id);
		if (item == null) {
			throw new IllegalArgumentException("Invalid Item Info");
		}

		return getItemStream(item);
	}

	/**************** Private Area *********************/
	
	private Iteminfo getItemInfo(String id) throws ClientProtocolException,
			IOException,IllegalArgumentException {
		Iteminfo iteminfo = null;

		String urlString = buildItemUrl(id);
		HttpGet httpStatusGet = new HttpGet(urlString);
		httpStatusGet.addHeader("Accept-Location", ACCEPT_LOCATION);
		httpStatusGet.addHeader("Referrer", REFERRER);

		HttpResponse secondResponse = httpClient.execute(httpStatusGet);
		HttpEntity secondEntity = secondResponse.getEntity();

		InputStream content = secondEntity.getContent();

		String json = IOUtils.toString(content);
		json = json.replaceAll("\\}.*", "}");
		json = json.replaceAll(".*?\\{", "{");

		httpStatusGet.releaseConnection();

		iteminfo = new Iteminfo(json, id);

		return iteminfo;
	}

	private byte[] getItemStream(Iteminfo iteminfo) throws IOException {
		return getItemStream(iteminfo, null);
	}

	private byte[] getItemStream(Iteminfo iteminfo, ProgressCallBack callBack)
			throws IOException {
		HttpGet httpStatusGet = null;
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		try {
			String urlString = builItemStreamUrl(iteminfo);

			httpStatusGet = new HttpGet(urlString);
			HttpResponse secondResponse = httpClient.execute(httpStatusGet);
			HttpEntity entity = secondResponse.getEntity();
			callBack.setSize(entity.getContentLength());
			BufferedInputStream input = new BufferedInputStream(
					entity.getContent());

			copyLarge(input, output, callBack);

		} catch (IOException e) {
			throw e;
		} finally {
			if (httpStatusGet != null)
				httpStatusGet.releaseConnection();
			if (output != null) {
				output.flush();
			}
		}

		return output.toByteArray();

	}

	private long copyLarge(InputStream input, OutputStream output,
			ProgressCallBack callBack) throws IOException {
		byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
		long count = 0;
		int n = 0;
		while (-1 != (n = input.read(buffer))) {
			output.write(buffer, 0, n);
			callBack.setProgressReaded(n);
			count += n;
		}
		return count;
	}

	private String builItemStreamUrl(Iteminfo iteminfo) throws UnsupportedEncodingException {
				
		Map<String, String> params = new LinkedHashMap<String, String>();		
		params.put("video_id",iteminfo.getId());
		params.put("ts_create",iteminfo.getTsCreate());
		params.put("r",iteminfo.getR());
		params.put("h2",iteminfo.geth2());

		String strParams = Utils.buildQueryString(params);	
		
		String s = Sign.sign(GET_URL+strParams);

		params.put("s" , s);
		strParams = Utils.buildQueryString(params);

		return GET_URL + strParams;
	}

	private String buildItemUrl(String id) throws UnsupportedEncodingException {

		Map<String, String> params = new LinkedHashMap<String, String>();
		params.put("video_id",id);
		params.put("ac","www");
		params.put("t","grp");
		params.put("r",String.valueOf(new Date().getTime()));

		String strParams = Utils.buildQueryString(params);	
		
		String s = Sign.sign(ITEM_INFO_URL+strParams);

		params.put("s" , s);
		strParams = Utils.buildQueryString(params);

		return ITEM_INFO_URL + strParams;
	}

	/****** PRIVATE CLASS ****************/

	private class Iteminfo {

		private Map<String, String> jsonTable;
		private String id;

		public Iteminfo(String json, String id) throws IllegalArgumentException {
			this.id = id;
			JSONObject jsonData = new JSONObject(json);
			JSONArray jsonArray = jsonData.names();
			JSONArray valArray = jsonData.toJSONArray(jsonArray);
			jsonTable = new HashMap<String, String>(jsonArray.length());
			for (int i = 0; i < jsonArray.length(); i++) {
				jsonTable.put(jsonArray.get(i).toString(), valArray.get(i)
						.toString());
			}
			if (!jsonTable.get("status").equalsIgnoreCase("serving"))
				throw new IllegalArgumentException("Invalid status: "
						+ jsonTable.get("status"));

		}

		public String getId() {
			return id;
		}

		public String getR() {
			return jsonTable.get("r");
		}

		public String getTsCreate() {
			return jsonTable.get("ts_create");
		}

		public String geth2() {
			return jsonTable.get("h2");
		}
	}

}