package com.music.downloadmanager.iface;

public interface ProgressCallBack {

	void setSize(long contentLength);

	void setProgressReaded(int n);

}
