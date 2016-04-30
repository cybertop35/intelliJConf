package it.Marco.GUI;



import it.Marco.Common.XmlUtil;
import it.Marco.Controller.DatiObj;
import it.Marco.Controller.Http;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class Impostazioni extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="304,144"
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField jTextField = null;
	private JLabel jLabel2 = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField2 = null;
	private JButton jButton = null;
	private JCheckBox jCheckBox = null;
	private JLabel jLabel21 = null;
	private TextArea textArea = null;
	private DatiObj datiObj = null;
	
	public Impostazioni(DatiObj datiObj){
		super();
		this.datiObj = datiObj;
		initialize();
	}

	private void initialize() {
		// TODO Stub di metodo generato automaticamente
		this.setLayout(null);		
		this.setFont(new java.awt.Font("Tahoma", java.awt.Font.PLAIN, 11));
		this.add(getJPanel(), null);
		
	}

	/**
	 * This method initializes jPanel	
	 * 	
	 * @return javax.swing.JPanel	
	 */
	private JPanel getJPanel() {
		if (jPanel == null) {
			jLabel21 = new JLabel();
			jLabel21.setBounds(new Rectangle(30, 148, 118, 29));
			jLabel21.setText("Auto Rilevamento");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(29, 114, 247, 23));
			jLabel2.setText("Intervallo in minuti per il controllo indirizzo");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(28, 81, 122, 23));
			jLabel1.setText("IP rilevato");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(29, 44, 122, 23));
			jLabel.setText("URL Connection");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(7, 20, 465, 380);
			//jPanel.setForeground(java.awt.Color.white);
			//jPanel.setBackground(java.awt.Color.white);
			jPanel.setBorder(new TitledBorder("Impostazioni Sistema"));
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(getJTextField(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(getJTextField1(), null);
			jPanel.add(getJTextField2(), null);
			jPanel.add(getJButton(), null);
			jPanel.add(getJCheckBox(), null);
			jPanel.add(jLabel21, null);
			jPanel.add(getTextArea(), null);
		}
		return jPanel;
	}

	/**
	 * This method initializes jTextField	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField() {
		if (jTextField == null) {
			jTextField = new JTextField();
			jTextField.setBounds(new Rectangle(179, 44, 216, 23));
			jTextField.setToolTipText("Inserire il Nome del server SMTP");
			jTextField.setFont(new Font("Dialog", Font.PLAIN, 12));
			jTextField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jTextField.setBorder(BorderFactory.createLoweredBevelBorder());
			if(datiObj.getUrl().equals(""))
				datiObj.setUrl("http://checkip.dyndns.org/");
			jTextField.setText(datiObj.getUrl());
			
		}
		return jTextField;
	}

	/**
	 * This method initializes jTextField1	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField1() {
		if (jTextField1 == null) {
			jTextField1 = new JTextField();
			jTextField1.setBounds(new Rectangle(179, 79, 214, 23));
			jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
			jTextField1.setEnabled(false);
			jTextField1.setText(datiObj.getIpRilevato());
			
		}
		return jTextField1;
	}

	/**
	 * This method initializes jTextField2	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField2() {
		if (jTextField2 == null) {
			jTextField2 = new JTextField();
			jTextField2.setBounds(new Rectangle(281, 114, 53, 23));
			jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
			jTextField2.setText(datiObj.getTempo());
		}
		return jTextField2;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) {
			jButton = new JButton();
			jButton.setBounds(new Rectangle(168, 344, 95, 24));
			jButton.setText("Rileva IP");
			jButton.addActionListener(new ActionListener(){
		    	@SuppressWarnings("deprecation")
				public void actionPerformed(ActionEvent e) {

		    		XmlUtil uXML = new XmlUtil();
		    		Http http = new Http(jTextField.getText());
					Object risp[]=http.loadPage();
					Principale.setText((String)risp[1]);
					
					if(risp[0].equals("OK")){
						String ip=uXML.getFoglia((String)risp[1],"body" );
						jTextField1.setText(ip.substring(11, ip.length()));
					}

					
					textArea.setText((String)risp[1]);
		    	}
		    });
			
		}
		return jButton;
	}

	/**
	 * This method initializes jCheckBox	
	 * 	
	 * @return javax.swing.JCheckBox	
	 */
		
	private JCheckBox getJCheckBox() {
		if (jCheckBox == null) {
			jCheckBox = new JCheckBox();
			jCheckBox.setBounds(new Rectangle(160, 152, 21, 21));
			jCheckBox.setSelected(datiObj.isAutoRilevamento());
		}
		return jCheckBox;
	}

	/**
	 * This method initializes textArea	
	 * 	
	 * @return java.awt.TextArea	
	 */
	private TextArea getTextArea() {
		if (textArea == null) {
			textArea = new TextArea();
			textArea.setBounds(new Rectangle(10, 198, 440, 125));
			textArea.setText("Pagina rilevata");
			textArea.setEditable(false);
		}
		return textArea;
	}
	
	public void save(){
		datiObj.setUrl(jTextField.getText());
		datiObj.setIpRilevato(jTextField1.getText());
		datiObj.setTempo(jTextField2.getText());
		datiObj.setAutoRilevamento(jCheckBox.isSelected());
	}
}
