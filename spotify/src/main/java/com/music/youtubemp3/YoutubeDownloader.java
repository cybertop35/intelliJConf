package com.music.youtubemp3;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;

import org.apache.commons.io.IOUtils;

import com.music.downloadmanager.Downloader;
import com.music.downloadmanager.Status;
import com.music.downloadmanager.iface.IDownloadFile;
import com.music.downloadmanager.iface.ProgressCallBack;
import com.music.google.GoogleApi;
import com.music.google.OrderBy;
import com.music.google.model.Result;
import com.music.spotify.model.Track;
import com.music.util.Utils;

public class YoutubeDownloader extends Downloader {

	private final static String EXTENSION = "mp3";
	private GoogleApi googleApi;
	private IDownloadFile youTubeMP3;
	private Track track;
	
	public YoutubeDownloader(String outputFolder, Track track) {
		super(null, outputFolder);
		this.track = track;
		youTubeMP3 = YouTubeMP3.create();
		googleApi = GoogleApi.createWithNoCompression();
		fileName = track.getName() + " - "+ track.getArtists().get(0).getName();
	}
	
	public YoutubeDownloader(URL url, String outputFolder,String fileName){
		super(url, outputFolder);		
		youTubeMP3 = YouTubeMP3.create();
		googleApi = GoogleApi.createWithNoCompression();
		this.fileName = fileName;
	}

	@Override
	public void run() {

		FileOutputStream out = null;
		try {
			if (state == Status.READY) {
								
				String file = outputFolder + "/" + Utils.escapeStringAsFilename(fileName) + "." + EXTENSION;
				
				File f = new File(file);
				
				if (f.exists()) {
					setState(Status.COMPLETE);
					return;
				}

				setState(Status.DOWNLOADING);
				String youTubeId = null;
				
				if(url == null){
					List<Result> googleresult = googleApi.searchVideos(fileName,OrderBy.RELEVANCE);
					youTubeId = getYouTubeId(googleresult, track);
				}
				else{
					youTubeId = extractYouTubeId(url.getPath());
				}
				
				if (youTubeId == null) {
					error(fileName + " Non trovato su youtube.");
					return;
				}

				setState(Status.IN_PROGRESS);
				byte result[] = youTubeMP3.getMP3ToByeArray(youTubeId,
						new ProgressCallBack() {
							@Override
							public void setSize(long contentLength) {
								fileSize = new Long(contentLength).intValue();
							}

							@Override
							public void setProgressReaded(int n) {
								downloaded(n);
							}

						});

				out = new FileOutputStream(f);
				IOUtils.write(result, out);

				// check the current state again
				if (state == Status.IN_PROGRESS) {
					setState(Status.COMPLETE);
				}
			}
		} catch (Exception e) {
			error(fileName + " - " + e.getMessage());
		} finally {
			if (out != null) {
				try {
					out.flush();
					out.close();
				} catch (IOException e) {
						out= null;
				}
			}
		}

	}

	private void error(String msg) {
		System.out.println("ERROR: " + msg);
		setState(Status.ERROR);
		interrupt();
	}

	private String getYouTubeId(List<Result> googleresult, Track track) {

		if (googleresult == null || googleresult.size() == 0)
			return null;

		int mindif = 0;
		Result tmp = null;

		for (Result r : googleresult) {
			int youTubeDuration = Integer.parseInt(r.getDuration());
			int spotifyDuration = track.getDurationMs() / 1000;
			if (tmp == null) {
				tmp = r;
				mindif = Math.abs(youTubeDuration - spotifyDuration);
			} else {
				if (mindif > Math.abs(youTubeDuration - spotifyDuration)) {
					tmp = r;
					mindif = Math.abs(youTubeDuration - spotifyDuration);
				}
			}

		}

		if (tmp == null)
			tmp = googleresult.get(0);

		return extractYouTubeId(tmp.getUrl()); 	
	}

	private String extractYouTubeId(String url){
		return url.substring(url.lastIndexOf("=") + 1);

	}
}
