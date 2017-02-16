/*
 * Principale.java
 *
 * Created on __DATE__, __TIME__
 */

package it.Marco.GUI;

import it.Marco.Controller.DatiObj;
import it.Marco.Controller.Save;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSeparator;
import javax.swing.event.AncestorListener;

/**
 *
 * @author  __USER__
 */
public class Principale extends javax.swing.JFrame {

	
	private static final long serialVersionUID = 1;
	/** Creates new form Principale */
	
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JButton jButton6;
	private javax.swing.JScrollPane jScrollPane1;
	private javax.swing.JToolBar.Separator jSeparator1;
	private javax.swing.JToolBar.Separator jSeparator2;
	private javax.swing.JTabbedPane jTabbedPane1;
	private static javax.swing.JTextArea jTextArea1;
	private javax.swing.JToolBar jToolBar1;
	private DatiObj datiObj = null;
	private ImpostaEmail impostaEmail = null;
	private Impostazioni impostazioni = null;
	
	public Principale(DatiObj datiObj) {
		super();
		this.datiObj = datiObj;
		initComponents();
		
	}
	
	
	private void initComponents() {

		jToolBar1 = new javax.swing.JToolBar();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();
		jSeparator1 = new javax.swing.JToolBar.Separator();
		jButton4 = new javax.swing.JButton();
		jButton5 = new javax.swing.JButton();
		jSeparator2 = new javax.swing.JToolBar.Separator();
		jButton6 = new javax.swing.JButton();
		jTabbedPane1 = new javax.swing.JTabbedPane();
		jScrollPane1 = new javax.swing.JScrollPane();
		jTextArea1 = new javax.swing.JTextArea();
		impostaEmail = new ImpostaEmail(datiObj);
		impostazioni = new Impostazioni(datiObj);
		setTitle("AutoSend Mail");
		//setDefaultCloseOperation(3);
		setResizable(false);
		//JFrame.
		jToolBar1.setBorderPainted(false);
		jToolBar1.setFocusable(false);
		jToolBar1.setRollover(true);
		jToolBar1.setDebugGraphicsOptions(javax.swing.DebugGraphics.NONE_OPTION);
		jToolBar1.setEnabled(false);

		jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/open.gif"))); // NOI18N
		jButton1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jButton1.setFocusable(false);
		jButton1.setHorizontalTextPosition(0);
		jButton1.setIconTextGap(5);
		jButton1.setLabel("Carica da file");
		jButton1.setMaximumSize(new java.awt.Dimension(100, 100));
		jButton1.setMinimumSize(new java.awt.Dimension(100, 100));
		jButton1.setOpaque(false);
		jButton1.setVerticalTextPosition(3);
		jToolBar1.add(jButton1);

		jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/save.gif"))); // NOI18N
		jButton2.setActionCommand("Salva");
		jButton2.setAlignmentX(0.6F);
		jButton2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jButton2.setFocusable(false);
		jButton2.setHorizontalTextPosition(0);
		jButton2.setIconTextGap(5);
		jButton2.setMargin(new java.awt.Insets(20, 1, 2, 14));
		jButton2.setMaximumSize(new java.awt.Dimension(100, 100));
		jButton2.setMinimumSize(new java.awt.Dimension(100, 100));
		jButton2.setText("Salva");
		jButton2.setVerticalTextPosition(3);
		jButton2.addActionListener(new ActionListener(){
	    	@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
	    		/**
	    		 * Costruisco l'oggetto dei dati da salvre costruisco
	    		 *  il file .Xml in cui salvare i dati per un 
	    		 *  successivo caricamento*
	    		 **/
	    		impostaEmail.save();
	    		impostazioni.save();
	    		Save save = new Save(datiObj);
	    		save.salva();
	    		
	    		
	    	
	    	}
	    });
		jToolBar1.add(jButton2);

		jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/run_tool.gif"))); // NOI18N
		jButton3.setActionCommand("Salva");
		jButton3.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jButton3.setFocusable(false);
		jButton3.setHorizontalTextPosition(0);
		jButton3.setIconTextGap(5);
		jButton3.setLabel("Avvia");
		jButton3.setMaximumSize(new java.awt.Dimension(100, 100));
		jButton3.setMinimumSize(new java.awt.Dimension(100, 100));
		jButton3.setVerticalTextPosition(3);
		jButton3.addActionListener(new ActionListener(){
	    	@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
	    		/**
	    		 * Avvia il concontrollo automatico del ip
	    		 * 
	    		 **/
	    		
	    		
	    		
	    	
	    	}
	    });
		jToolBar1.add(jButton3);
		jToolBar1.add(jSeparator1);

		jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/help.gif"))); // NOI18N
		jButton4.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jButton4.setFocusable(false);
		jButton4.setHorizontalTextPosition(0);
		jButton4.setIconTextGap(5);
		jButton4.setMaximumSize(new java.awt.Dimension(100, 120));
		jButton4.setMinimumSize(new java.awt.Dimension(100, 120));
		jButton4.setSelected(true);
		jButton4.setVerticalTextPosition(3);
		jToolBar1.add(jButton4);

		jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/aiuto.gif"))); // NOI18N
		jButton5.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jButton5.setFocusable(false);
		jButton5.setHorizontalTextPosition(0);
		jButton5.setMaximumSize(new java.awt.Dimension(100, 100));
		jButton5.setMinimumSize(new java.awt.Dimension(100, 100));
		jButton5.setVerticalTextPosition(3);
		jToolBar1.add(jButton5);
		jToolBar1.add(jSeparator2);

		jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/chiudi.gif"))); // NOI18N
		jButton6.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		jButton6.setFocusable(false);
		jButton6.setHorizontalTextPosition(0);
		jButton6.setMaximumSize(new java.awt.Dimension(100, 80));
		jButton6.setMinimumSize(new java.awt.Dimension(100, 80));
		jButton6.setVerticalTextPosition(3);
		jButton6.addActionListener(new ActionListener(){
	    	@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
	    		if ((JOptionPane.showConfirmDialog(null,"Chiudo l'applicazione?","Chiusura Applicazione",
	    											JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION))
	    	   		System.exit(1);
	    		
	    	
	    	}
	    });
		jToolBar1.add(jButton6);

		jScrollPane1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

		jTextArea1.setColumns(20);
		jTextArea1.setEditable(false);
		jTextArea1.setLineWrap(true);
		jTextArea1.setRows(5);
		jScrollPane1.setViewportView(jTextArea1);

		org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(
				org.jdesktop.layout.GroupLayout.LEADING).add(
						org.jdesktop.layout.GroupLayout.TRAILING,
						layout.createSequentialGroup().addContainerGap().add(
								layout.createParallelGroup(
								org.jdesktop.layout.GroupLayout.TRAILING).add(
								org.jdesktop.layout.GroupLayout.LEADING,
								jScrollPane1,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								500, Short.MAX_VALUE).add(
								org.jdesktop.layout.GroupLayout.LEADING,
								jTabbedPane1,
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								500, Short.MAX_VALUE).add(
								org.jdesktop.layout.GroupLayout.LEADING,
								jToolBar1,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								500, Short.MAX_VALUE)).addContainerGap()));
								layout.setVerticalGroup(layout.createParallelGroup(
								org.jdesktop.layout.GroupLayout.LEADING).add(
								layout.createSequentialGroup().addContainerGap().add(jToolBar1,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 53,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.RELATED).add(
								jTabbedPane1,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								443,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(
								org.jdesktop.layout.LayoutStyle.UNRELATED).add(
								jScrollPane1,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE,
								61,
								org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
								.addContainerGap(
								org.jdesktop.layout.GroupLayout.DEFAULT_SIZE,
								Short.MAX_VALUE)));

		getAccessibleContext().setAccessibleParent(this);
		jTabbedPane1.addTab("Imposta email",impostaEmail);
        jTabbedPane1.addTab("Impostazioni",impostazioni);
		pack();
	}// </editor-fold>
	
	public static void setText(String s){
		jTextArea1.append(s+"\n");
	}
	
	public static void main(String args[]){
		
		
	}

}