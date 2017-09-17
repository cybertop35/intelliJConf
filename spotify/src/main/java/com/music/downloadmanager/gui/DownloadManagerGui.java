package com.music.downloadmanager.gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;

import com.music.downloadmanager.DownloadManager;
import com.music.downloadmanager.Downloader;
import com.music.downloadmanager.iface.ICallback;
import com.music.spotify.SpotifyDownloaderTrack;
import com.music.spotify.model.Track;
import com.music.youtubemp3.YoutubeDownloader;

public class DownloadManagerGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9007513632670368991L;
	private static final String TITLE = "Download Mandager";
	
	private DownloadTableModel tableModel;
	
	/* Components */
	private JProgressBar progressBar;
	private JTable jtbDownload;
	private JScrollPane jScrollTable; 
	private JButton btDownload;
	private JButton btStop;
	private JButton btPaste;
	/**
	 * Create the panel.
	 */
	public DownloadManagerGui() {
		tableModel = new DownloadTableModel();
        initComponents();
	}

	
	
	private void initComponents() {
		this.setTitle(TITLE);
		this.setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
		this.setSize(673, 415);
		Container pane = this.getContentPane();
		pane.setLayout(new BorderLayout(5, 5));
		
		progressBar = new JProgressBar(0);
		progressBar.setValue(10);
		progressBar.setMaximum(20);		
		pane.add(progressBar,BorderLayout.PAGE_START);
		createTable();
		pane.add(jScrollTable,BorderLayout.CENTER);
		
		Box boxLayout = Box.createHorizontalBox();
	
		btDownload = new JButton();
		btDownload.setText("Download");
		btDownload.setAlignmentX(Component.CENTER_ALIGNMENT);
		boxLayout.add(btDownload);
		
		boxLayout.add(Box.createRigidArea(new Dimension(10,0)));
		
		btStop = new JButton();
		btStop.setAlignmentX(Component.CENTER_ALIGNMENT);
		btStop.setText("Pause");
		boxLayout.add(btStop);

		boxLayout.add(Box.createRigidArea(new Dimension(10,0)));

		btPaste = new JButton();
		btPaste.setText("Paste");
		boxLayout.add(btPaste);
		btPaste.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String clipBoard = getClipboardContents();
				System.out.println(clipBoard);
				init(clipBoard);
			}
		});
		pane.add(boxLayout,BorderLayout.PAGE_END);

		this.pack();
		
	}

	private void createTable(){
		ProgressRenderer renderer = new ProgressRenderer(0, 100);
		renderer.setStringPainted(true);
		
		jScrollTable = new JScrollPane();
		jtbDownload = new JTable();
		jtbDownload.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		jtbDownload.setDefaultRenderer(JProgressBar.class, renderer);
        jtbDownload.setRowHeight((int) renderer.getPreferredSize().getHeight());
		jtbDownload.setModel(tableModel);
		jScrollTable.setViewportView(jtbDownload);
		//jtbDownload.addMouseListener(new ContextMenuMouseListener());
	}

	
	private void init(String clipBoard){
		String []lines = clipBoard.split("\n");
		for (String line : lines) {
			try {
				SpotifyDownloaderTrack spt = new SpotifyDownloaderTrack(new URL(line), new ICallback<Track>() {
					
					@Override
					public void callBack(Track t) {
						Downloader download = new YoutubeDownloader(defaultDirectory(),t);
						tableModel.addNewDownload(download);						
					}
				});

				DownloadManager.getInstance().addDownloader(spt);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}
	
	private String defaultDirectory(){
		Path dir = FileSystems.getDefault().getPath(".", "/data/download");

		try {
			Files.createDirectory(dir);
		} catch (IOException e1) {

		}
		
		return dir.toString();
	}
	
	public String getClipboardContents() {
	    String result = "";
	    Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
	    //odd: the Object param of getContents is not currently used
	    Transferable contents = clipboard.getContents(null);
	    boolean hasTransferableText =
	      (contents != null) &&
	      contents.isDataFlavorSupported(DataFlavor.stringFlavor)
	    ;
	    if (hasTransferableText) {
	      try {
	        result = (String)contents.getTransferData(DataFlavor.stringFlavor);
	      }
	      catch (UnsupportedFlavorException | IOException ex){
	        System.err.println(ex);
	      }
	    }
	    return result;
	  }

	
	
	
	/**
	    * @param args the command line arguments
	    */
	    public static void main(String args[]) {
	    	// set to user's look and feel
	    	try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
			}
			
	        java.awt.EventQueue.invokeLater(new Runnable() {
	            public void run() {
	                new DownloadManagerGui().setVisible(true);
	            }
	        });
	    }
}
