package com.music.downloadmanager.iface;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

public interface IDownloadFile {

	public byte[] getMP3ToByeArray(String path, ProgressCallBack callBack) throws ClientProtocolException, IOException, IllegalArgumentException;
	public byte[] getMP3ToByeArray(String path) throws ClientProtocolException, IOException, IllegalArgumentException;
}
