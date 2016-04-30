package com.music.downloadmanager;

import java.net.URL;

import com.music.downloadmanager.iface.ICallback;

public abstract class DownloaderCallback<T> extends Downloader {
	
	protected ICallback<T> callback;

	protected DownloaderCallback(URL url, ICallback<T> callback) {
		super(url);
		this.callback = callback;

	}

	
}
