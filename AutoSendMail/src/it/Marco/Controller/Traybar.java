package it.Marco.Controller;

import it.Marco.GUI.Principale;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.TrayIcon.MessageType;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import javax.swing.JFrame;
import javax.swing.JToolBar.Separator;

public class Traybar {

	
	private SystemTray st = null;
	private File file = null;
	private Image image = null;
	private TrayIcon trayIcon = null;
	private JFrame principale = null;
	
	public Traybar (JFrame principale){
		this.principale = principale;
	}
	
	public void trayIcon(){
	
		try {
			
			//verifico se il troyBar è supportato dal sistema
			if(st.isSupported()){
				
				//System.out.println("Il sistema supporta la TroyBar");
		
				//recupero il troyBar di sistema
				st = SystemTray.getSystemTray();
				file= new File(System.getProperty("user.dir")+File.separator+"src"+File.separator+"images"+File.separator+"trayIcon.png");
			
				image = Toolkit.getDefaultToolkit().getImage(file.getAbsolutePath());
			
				trayIcon = new TrayIcon(image, "AutoSend Mail!");
			
				st.add(trayIcon);
				
				PopupMenu menu = new PopupMenu();
				MenuItem exitItem = new MenuItem("Esci");
				exitItem.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						System.exit(0);
						}
					});
				MenuItem separetor =  new MenuItem("-------");
				separetor.disable();
				
				MenuItem openItem = new MenuItem("Apri");
				openItem.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						principale.setVisible(true);
					}
				});
				
				MenuItem startItem = new MenuItem("Avvia");
				startItem.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
					}
				});
				
				MenuItem stopItem = new MenuItem("Stop");
				stopItem.addActionListener(new ActionListener(){
					public void actionPerformed(ActionEvent e){
						
					}
				});
				
				
				menu.add(stopItem);
				menu.add(startItem);
				menu.add(separetor);
				menu.add(openItem);
				menu.add(exitItem);
				
				trayIcon.setPopupMenu(menu);
			
			
			
			trayIcon.addMouseListener(new MouseListener() {

				public void mouseClicked(MouseEvent e) {
					if (e.getClickCount() == 2) {
						trayIcon.displayMessage("info Applicazione",
								"Il sistema Funnziona correttamente.!",
								MessageType.INFO);
					}
				}

				public void mouseEntered(MouseEvent e) {}

				public void mouseExited(MouseEvent e) {}

				public void mousePressed(MouseEvent e) {}

				public void mouseReleased(MouseEvent e) {}
				
			});
		}
		else
			System.out.println("Il sistema non supporta il SystemTray");
	
			} catch (AWTException e) {
			// TODO Auto-generated catch block
				Principale.setText("Eccezione rilevata nella TrayIcon. " +
									"Riprovare riavviando l'applicazione");
			}
	}	
}
