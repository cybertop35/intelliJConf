package com.music.spotify;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.music.spotify.model.Track;

public class SpotifyApi {
	
	private final static String TRACKS_URL ="https://api.spotify.com/v1/tracks/";
	
	private ObjectMapper mapper;
		
	public static SpotifyApi create(){
		return new SpotifyApi();
	}
	
	protected SpotifyApi(){
		super();
		mapper = new ObjectMapper();
	}
	/**
	 * Recive Track Info
	 * 
	 * @param id - Spotify Id
	 * @return
	 * @throws ClientProtocolException
	 * @throws IOException
	 */
	public Track getTrackInfo(String id) throws ClientProtocolException, IOException{
		Content content = Request	.Get(TRACKS_URL + id).execute().returnContent();
		String result = content.asString();
		Track t = mapper.readValue(result, Track.class);		
		return t;
		
	}
}
