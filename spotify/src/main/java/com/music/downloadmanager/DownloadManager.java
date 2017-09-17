package com.music.downloadmanager;

import java.net.URL;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.music.spotify.SpotifyDownloaderTrack;

public class DownloadManager {

	// The unique instance of this class
	private static DownloadManager sInstance = null;

	protected int corePoolSize = 2;
	protected int maximumPoolSize = 4;
	protected long keepAliveTime = 500;
	private ThreadPoolExecutor executor;
	private BlockingQueue<Runnable> queue;

	/** Protected constructor */
	protected DownloadManager() {
		init();
	}

	public int getCorePoolSize() {
		return corePoolSize;
	}

	public void setCorePoolSize(int corePoolSize) {
		this.corePoolSize = corePoolSize;
		executor.setCorePoolSize(corePoolSize);
	}

	public int getMaximumPoolSize() {
		return maximumPoolSize;
	}

	public void setMaximumPoolSize(int maximumPoolSize) {
		this.maximumPoolSize = maximumPoolSize;
		executor.setMaximumPoolSize(maximumPoolSize);
	}

	public long getKeepAliveTime() {
		return keepAliveTime;
	}

	public void setKeepAliveTimeInSecond(long keepAliveTime) {
		this.keepAliveTime = keepAliveTime;
		executor.setKeepAliveTime(keepAliveTime, TimeUnit.SECONDS);
	}

	public int getWaitigTask(int index) {
		return queue.size();
	}

	public long getCompletedTaskCount() {
		return executor.getCompletedTaskCount();
	}

	public void addDownloader(Downloader downloader) {

		if (executor.isShutdown())
			init();

		downloader.setState(Status.READY);
		executor.execute(downloader);
	}

	public void shotdown() {
		executor.shutdown();
	}

	/**
	 * Get the unique instance of this class
	 * 
	 * @return the instance of this class
	 */
	public static DownloadManager getInstance() {
		if (sInstance == null)
			sInstance = new DownloadManager();

		return sInstance;
	}

	private void init() {
		queue = new LinkedBlockingQueue<Runnable>();
		executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize,
				keepAliveTime, TimeUnit.SECONDS, queue);
	}
}