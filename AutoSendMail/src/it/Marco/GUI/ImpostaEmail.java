package it.Marco.GUI;

import it.Marco.Controller.DatiObj;
import it.Marco.Controller.SendMail;

import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.Rectangle;
import java.awt.TextArea;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


public class ImpostaEmail extends javax.swing.JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jPanel = null;  //  @jve:decl-index=0:visual-constraint="304,144"
	private JLabel jLabel = null;
	private JLabel jLabel1 = null;
	private JTextField jTextField = null;
	private JLabel jLabel2 = null;
	private JLabel jLabel3 = null;
	private JLabel jLabel4 = null;
	private JLabel jLabel5 = null;
	private JLabel jLabel7 = null;
	private JTextField jTextField1 = null;
	private JTextField jTextField2 = null;
	private JTextField jTextField3 = null;
	private JTextField jTextField4 = null;
	private TextArea jTextArea = null;
	private JTextField jTextField5 = null;
	private JButton jButton = null;
	private DatiObj datiObj = null;
	
	public ImpostaEmail(DatiObj datiObj){
		super();
		this.datiObj = datiObj;
		initialize();
	}

	public ImpostaEmail(){}
	private void initialize() {
		// TODO Stub di metodo generato automaticamente
		this.setLayout(null);		
		this.setLocation((1000 - this.getWidth()) / 2,
				(750 - this.getHeight()) / 2);
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
			jLabel7 = new JLabel();
			jLabel7.setBounds(new Rectangle(29, 243, 122, 23));
			jLabel7.setDisplayedMnemonic(KeyEvent.VK_UNDEFINED);
			jLabel7.setText("Body");
			jLabel5 = new JLabel();
			jLabel5.setBounds(new Rectangle(29, 212, 122, 23));
			jLabel5.setText("Mail To");
			jLabel4 = new JLabel();
			jLabel4.setBounds(new Rectangle(30, 180, 122, 23));
			jLabel4.setText("Object");
			jLabel3 = new JLabel();
			jLabel3.setBounds(new Rectangle(29, 144, 122, 23));
			jLabel3.setText("Password");
			jLabel2 = new JLabel();
			jLabel2.setBounds(new Rectangle(29, 114, 122, 23));
			jLabel2.setText("UserName");
			jLabel1 = new JLabel();
			jLabel1.setBounds(new Rectangle(29, 72, 122, 23));
			jLabel1.setText("Server POP3");
			jLabel = new JLabel();
			jLabel.setBounds(new Rectangle(29, 44, 122, 23));
			jLabel.setText("Server SMTP");
			jPanel = new JPanel();
			jPanel.setLayout(null);
			jPanel.setBounds(7, 20, 465, 380);
			//jPanel.setForeground(java.awt.Color.white);
			//jPanel.setBackground(java.awt.Color.white);
			jPanel.setBorder(new TitledBorder("SMTP Configuration"));
			jPanel.add(jLabel, null);
			jPanel.add(jLabel1, null);
			jPanel.add(getJTextField(), null);
			jPanel.add(jLabel2, null);
			jPanel.add(jLabel3, null);
			jPanel.add(jLabel4, null);
			jPanel.add(jLabel5, null);
			jPanel.add(jLabel7, null);
			jPanel.add(getJTextField1(), null);
			jPanel.add(getJTextField2(), null);
			jPanel.add(getJTextField3(), null);
			jPanel.add(getJTextField4(), null);
			jPanel.add(getJTextArea(), null);
			jPanel.add(getJTextField5(), null);
			jPanel.add(getJButton(), null);
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
			jTextField.setBounds(new Rectangle(179, 44, 155, 23));
			jTextField.setToolTipText("Inserire il Nome del server SMTP");
			jTextField.setFont(new Font("Dialog", Font.PLAIN, 12));
			jTextField.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			jTextField.setBorder(BorderFactory.createLoweredBevelBorder());
			jTextField.setText(datiObj.getSmtp());
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
			jTextField1.setBounds(new Rectangle(179, 71, 155, 23));
			jTextField1.setBorder(BorderFactory.createLoweredBevelBorder());
			jTextField1.setText(datiObj.getPop3());
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
			jTextField2.setBounds(new Rectangle(179, 114, 155, 23));
			jTextField2.setBorder(BorderFactory.createLoweredBevelBorder());
			jTextField2.setText(datiObj.getUsername());
		}
		return jTextField2;
	}

	/**
	 * This method initializes jTextField3	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField3() {
		if (jTextField3 == null) {
			jTextField3 = new JTextField();
			jTextField3.setBounds(new Rectangle(179, 143, 155, 23));
			jTextField3.setBorder(BorderFactory.createLoweredBevelBorder());
			jTextField3.setText(datiObj.getPws());
		}
		return jTextField3;
	}

	/**
	 * This method initializes jTextField4	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField4() {
		if (jTextField4 == null) {
			jTextField4 = new JTextField();
			jTextField4.setBounds(new Rectangle(179, 181, 155, 23));
			jTextField4.setBorder(BorderFactory.createLoweredBevelBorder());
			jTextField4.setText(datiObj.getObject());
			}
		return jTextField4;
	}

	/**
	 * This method initializes jTextArea	
	 * 	
	 * @return javax.swing.JTextArea	
	 */
	private TextArea getJTextArea() {
		if (jTextArea == null) {
			jTextArea = new TextArea();
			jTextArea.setText(datiObj.getBody());
			jTextArea.setBounds(new Rectangle(178, 247, 236, 82));
			jTextArea.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
			
			
		}
		return jTextArea;
	}

	/**
	 * This method initializes jTextField5	
	 * 	
	 * @return javax.swing.JTextField	
	 */
	private JTextField getJTextField5() {
		if (jTextField5 == null) {
			jTextField5 = new JTextField();
			jTextField5.setBounds(new Rectangle(179, 212, 155, 23));
			jTextField5.setBorder(BorderFactory.createLoweredBevelBorder());
			jTextField5.setText(datiObj.getMailto());
		}
		return jTextField5;
	}

	/**
	 * This method initializes jButton	
	 * 	
	 * @return javax.swing.JButton	
	 */
	private JButton getJButton() {
		if (jButton == null) 
			jButton = new JButton();
			jButton.setBounds(new Rectangle(168, 344, 95, 24));
			jButton.setText("Test Email");
			jButton.addMouseListener(new java.awt.event.MouseAdapter() {
	            public void mouseClicked(java.awt.event.MouseEvent evt) {
	                CaricaMouseClicked(evt);
	            }

				private void CaricaMouseClicked(MouseEvent evt) {
					SendMail sm = new SendMail();
					
					sm.setSmtp(jTextField.getText());
					sm.setPop3(jTextField1.getText());
					sm.setUserName(jTextField2.getText());
					sm.setPws(jTextField3.getText());
					sm.setObject(jTextField4.getText());
					sm.setMailTo(jTextField5.getText());
					sm.setBody(jTextArea.getText());
					if(sm.send())
						javax.swing.JOptionPane.showMessageDialog(null,"Email inoltrata con successo");
					else
						javax.swing.JOptionPane.showMessageDialog(null,"Email non inoltrata con successo.Verificare i dati inseriti e roprovare");
					
				}
		});
	
		return jButton;
	}
	
	public  void save(){
		datiObj.setBody(jTextArea.getText());
		datiObj.setSmtp(jTextField.getText());
		datiObj.setPop3(jTextField1.getText());
		datiObj.setUsername(jTextField2.getText());
		datiObj.setPws(jTextField3.getText());
		datiObj.setObject(jTextField4.getText());
		datiObj.setMailto(jTextField5.getText());
	}
}
