package chess.unideb.hu.maven;

import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Players extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private static Logger logger = LoggerFactory.getLogger(Players.class);
	
	protected JButton okButton;
	private JTextField userNameField;
	private JTextField userNameField2;
	private JLabel info;
	private JLabel userNameLabel;
	private JLabel userNameLabel2;
	private GridBagLayout textFieldPanelLayout;
	private GridBagLayout buttonPanelLayout;
	private GridBagConstraints gbc;
	private JPanel textFieldPanel;
	private JPanel buttonPanel;
	private static String userName = "Gerikee";
	private static String userName2 = "Mateee";
    private boolean isOkay = true;
	
    public Players(){
    	initPlayers();
	}
	
	private void initPlayers() {
		setBounds(500,500,400,200);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Players");
		setLayout(new FlowLayout());
				
		setResizable(false);
		
		gbc = new GridBagConstraints();
		textFieldPanelLayout = new GridBagLayout();
		buttonPanelLayout = new GridBagLayout();
		
		info = new JLabel("Players: ");
		userNameLabel = new JLabel("Light player: ");
		userNameLabel2 = new JLabel("Dark player: ");
		textFieldPanel = new JPanel();
		buttonPanel = new JPanel();
		
		textFieldPanel.setLayout(textFieldPanelLayout);
		buttonPanel.setLayout(buttonPanelLayout);
		gbc.insets = new Insets(10, 5, 10, 5);
		add(info,gbc);
		add(textFieldPanel);
			
		add(buttonPanel);
		
		okButton = new JButton("Continue");
		okButton.addActionListener(this);
		
		
			gbc.insets = new Insets(15, 5, 5, 5);
			gbc.gridx = 1;
			gbc.gridy = 3;
		buttonPanel.add(okButton,gbc);
		
		
		userNameField = new JTextField(userName,20);
		gbc.insets = new Insets(3, 0, 0, 0);
			gbc.gridx = 0;
			gbc.gridy = 0;
			textFieldPanel.add(userNameLabel,gbc);
			gbc.gridx = 1;
			gbc.gridy = 0;
			textFieldPanel.add(userNameField,gbc);
		
			
		userNameField2 = new JTextField(userName2,20);
			gbc.gridx = 0;
			gbc.gridy = 1;
			textFieldPanel.add(userNameLabel2,gbc);
			gbc.gridx = 1;
			gbc.gridy = 1;
			textFieldPanel.add(userNameField2,gbc);

		info.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10) );

		setVisible(true);
	}

	
	public static String getUserName(){
		return userName;
	}
	public static String getUserName2(){
		return userName2;
	}
	
	public boolean getIsOkay(){
		return isOkay;
	}
	
	public void setIsOkay(boolean is){
		isOkay = is;
	}
	

	public void actionPerformed(ActionEvent e){
		JFrame frame = new JFrame();
		if(e.getSource() == okButton)
		{
			userName = userNameField.getText();
			userName2 = userNameField2.getText();
			if(userName.compareTo("") == 0 || userName2.compareTo("") == 0)
			{
				userName = null;
				JOptionPane.showMessageDialog(frame, "Do not leave any field empty!", "Warning", JOptionPane.WARNING_MESSAGE);	
			}else{
				logger.info("The two players are: " + userName + " and " + userName2);
				isOkay = false;
				setVisible(false);
			}			
		}
	}	
}
