package com.music.spotify;

import java.net.URL;

import com.music.downloadmanager.DownloaderCallback;
import com.music.downloadmanager.Status;
import com.music.downloadmanager.iface.ICallback;
import com.music.spotify.model.Track;

public class SpotifyDownloaderTrack extends DownloaderCallback<Track> {

	private SpotifyApi spotifyApi;

	public SpotifyDownloaderTrack(URL url, ICallback<Track> callback) {
		super(url, callback);

		spotifyApi = SpotifyApi.create();

	}

	private void error(String msg) {
		System.out.println("ERROR: " + msg);
		setState(Status.ERROR);
		interrupt();
	}

	@Override
	public void run() {

		try {

			if (state == Status.READY) {
				String id = url.getPath().substring(url.getPath().lastIndexOf("/") + 1);

				Track track = spotifyApi.getTrackInfo(id);

				if (track == null) {
					error("Id non trovato su spotify.");
					return;
				}

				callback.callBack(track);

				if (state == Status.IN_PROGRESS) {
					setState(Status.COMPLETE);
				}

			}
		} catch (Exception e) {
			error(fileName + " - " + e.getMessage());
		}
	}

}