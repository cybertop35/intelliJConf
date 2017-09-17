package com.music;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import com.music.downloadmanager.DownloadManager;
import com.music.downloadmanager.Downloader;
import com.music.downloadmanager.iface.ICallback;
import com.music.spotify.SpotifyDownloaderTrack;
import com.music.spotify.model.Track;
import com.music.util.Utils;
import com.music.youtubemp3.YoutubeDownloader;

/**
 * Hello world!
 *
 */
public class App implements Observer {

	//private static String DEFAULT_FOLDER = "downloaded";
	private List<String> lines ;
	private int counter = 0; 
	
	public static void main(String[] args) {

		App app = new App();
		app.execute();
	}	
	
	public void execute(){
		lines = readLargeFileLines("/data/spotify.txt");

		final Path dir = FileSystems.getDefault().getPath(".", "/data/download");
		final DownloadManager downloadManager = DownloadManager.getInstance();
		final Observer o = this;
		
		try {
			Files.createDirectory(dir);
		} catch (IOException e1) {

		}	
		int limitDownload = 0;
		int timeToSleep = 60000 ;//6 min
		for (String line : lines) {
			limitDownload ++;
			try {
				SpotifyDownloaderTrack spt = new SpotifyDownloaderTrack(new URL(line), new ICallback<Track>() {
					
					@Override
					public void callBack(Track t) {
						File f = new File(dir.toAbsolutePath().toString()+ "/" + Utils.escapeStringAsFilename(t.getName() + " - "+ t.getArtists().get(0).getName())+".mp3");
						if(!f.exists()){
							Downloader u = new YoutubeDownloader(dir.toAbsolutePath().toString(), t);
							u.addObserver(o);
							downloadManager.addDownloader(u);
						}
						else
						{
							System.out.println("Skype "+Utils.escapeStringAsFilename(t.getName() + " - "+ t.getArtists()));
						}
					}
				});

				downloadManager.addDownloader(spt);
				
//				if(limitDownload% 100 == 0){
//					System.out.println("Pausa "+timeToSleep/1000+" minuti");
//					Thread.sleep(timeToSleep);
//				}
	
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				downloadManager.shotdown();
			}
			
			
		}
	
	}

	
	@Override
	public synchronized void update(Observable arg0, Object arg1) {
		Downloader downloader = (Downloader) arg0;
		//Utils.clearConsole();
		switch (downloader.getState()) {
		case DOWNLOADING:
			System.out.println("Download started: " + downloader.getFileName()
					+ " Thread ID:" + downloader.getThreadId());
			break;
		case PAUSED:
			System.out.println("Download paused: " + downloader.getFileName()
					+ " Thread ID:" + downloader.getThreadId());
			break;
		case COMPLETE:
			System.out.println("Download Complete: " + downloader.getFileName()
					+ " Size MB: " + downloader.getFileSize() / 1024 / 1024);
			counter++;
			System.out.println("Download "+lines.size()+"/"+counter);
			break;
		case CANCELLED:
			System.out.println("Download cancelled: "
					+ downloader.getFileName() + " Thread ID:"
					+ downloader.getThreadId());
			break;
//		case ERROR:
//			System.out.println("Download error: " + downloader.getFileName()
//					+ " Skipped. Error Message:" + downloader.getThreadId());
//			break;
		case IN_PROGRESS:
			break;
		default:
			break;
		}

	}

	private List<String> readLargeFileLines(String name) {
		try {
			BufferedReader reader = Files.newBufferedReader(FileSystems
					.getDefault().getPath(".", name), Charset.defaultCharset());

			List<String> lines = new ArrayList<>();
			String line = null;
			while ((line = reader.readLine()) != null)
				lines.add(line);

			return lines;
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		return null;
	}
}
