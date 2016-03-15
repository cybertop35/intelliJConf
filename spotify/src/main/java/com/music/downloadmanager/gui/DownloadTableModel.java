package com.music.downloadmanager.gui;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JProgressBar;
import javax.swing.table.AbstractTableModel;

import com.music.downloadmanager.DownloadManager;
import com.music.downloadmanager.Downloader;

public class DownloadTableModel extends AbstractTableModel implements Observer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7845261628402842217L;
	
	private static final Object[] columnNames = {"", "Title", "Size","Status","Progress"};
	private static final Class[] columnClasses = {Boolean.class,String.class,Integer.class, String.class,JProgressBar.class};

	private List<Downloader> downloaderList;

	public DownloadTableModel (){
		downloaderList = new LinkedList<Downloader>();
	}
	
	/**
     *  Remove a download from the list.
     */
    public void clearDownload(int row) {        
        // Fire table row deletion notification to table.
        fireTableRowsDeleted(row, row);
    }

	public void addNewDownload(Downloader download) {
        // Register to be notified when the download changes.
        download.addObserver(this);
        downloaderList.add(download);
        // Fire table row insertion notification to table.
        fireTableRowsInserted(getRowCount() - 1, getRowCount() - 1);
    }
    /**
     *  Get a column's name.
     */
    public String getColumnName(int col) {
        return columnNames[col].toString();
    }
	
    @Override
	public int getColumnCount() {
		return columnNames.length;
	}

	@Override
	public int getRowCount() {
		return downloaderList.size();
	}
	
    @Override
    public Class getColumnClass(int col) {
        return columnClasses[col];
    }
    
	@Override
	public Object getValueAt(int row, int col) {
		
		//{"", "Title", "Size","Status","Progress"};
		Downloader download = downloaderList.get(row);
		
		switch (col) {
        case 0: // URL
            return false;
        case 1:
        	return download.getFileName();
        case 2: // Size
            int size = download.getFileSize();
            return (size == -1) ? "" : (Integer.toString(size/1000));
        case 3: // Progress
            return download.getState().name();
        case 4: // Status
            return new Float(download.getProgress());
     }
    return "";

	}

	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub
		//fireTableRowsUpdated(index, index);
	}

}
