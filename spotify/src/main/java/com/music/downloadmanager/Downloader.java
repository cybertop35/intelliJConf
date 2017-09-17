package com.music.downloadmanager;

import java.net.URL;
import java.util.Observable;
import java.util.UUID;

import com.music.downloadmanager.iface.ICallback;

public abstract class Downloader extends Observable implements Runnable {

	
		protected URL url;
		protected String outputFolder;
		protected int fileSize;
		protected Status state;
		protected int sizeDownloaded;
		protected int threadID;
		protected String fileName;
		protected Thread t;
		
	
		protected Downloader(URL url){
			this.url = url;
		}
		
		/**
		 * Constructor
		 * 
		 * @param url
		 * @param outputFolder
		 * @param threadId
		 */
		protected Downloader(URL url, String outputFolder,int threadId){
			this.url = url;
			this.outputFolder = outputFolder;
			fileSize = -1;
			state = Status.READY;
			sizeDownloaded = 0;
			this.threadID = threadId;
		}
		
		/**
		 * Constructor
		 * 
		 * @param fileURL
		 * @param outputFolder
		 */
		protected Downloader(URL url, String outputFolder) {
			 this(url, outputFolder, UUID.randomUUID().hashCode());
		}
				
		public int getThreadId(){
			return threadID;
		}
		
		/**
		 * Pause the downloader
		 */
		public void pause() {
			setState(Status.PAUSED);
		}
		
		/**
		 * Resume the downloader
		 */
		public void resume() {
			setState(Status.DOWNLOADING);
			//download();
		}
		
		/**
		 * Cancel the downloader
		 */
		public void cancel() {
			setState(Status.CANCELLED);
			interrupt();
		}
		
		/**
		 * Get the URL (in String)
		 */
		public String getURL() {
			return url.toString();
		}
		
		/**
		 * Get the downloaded file's size
		 */
		public int getFileSize() {
			return fileSize;
		}
		
		/**
		 * Get the current progress of the download
		 */
		public float getProgress() {
			return ((float)sizeDownloaded / fileSize) * 100;
		}
		
		/**
		 * Get current state of the downloader
		 */
		public Status getState() {
			return state;
		}
		
		public String getFileName(){
			return fileName;
		}
				
		/**
		 * Set the state of the downloader
		 */
		protected void setState(Status value) {
			state = value;
			stateChanged();
			
		}
		
		/**
		 * Start or resume download
		 */

		
		/**
		 * Increase the downloaded size
		 */
		protected void downloaded(long n) {
			sizeDownloaded += n;
			stateChanged();
		}
		
		/**
		 * Set the state has changed and notify the observers
		 */
		protected void stateChanged() {
			setChanged();
			notifyObservers();
		}
		
		protected void interrupt(){
			if(t != null)
				t.interrupt();
			else
				Thread.currentThread().interrupt();
		}

	}

