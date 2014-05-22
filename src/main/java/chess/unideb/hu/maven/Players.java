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

/**
 * 
 * @author gergo0720
 * Class of Players.
 */
public class Players extends JFrame implements ActionListener{

	/**
	 * serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Logger to debug, log information and warnings.
	 */
	private static Logger logger = LoggerFactory.getLogger(Players.class);
	
	/**
	 * Button to finish entering player names.
	 */
	protected JButton okButton;
	
	/**
	 * Textfield for the name of the light player.
	 */
	private JTextField userNameField;
	
	/**
	 * Textfield for the name of the dark player.
	 */
	private JTextField userNameField2;
	
	/**
	 * Label to players' name.
	 */
	private JLabel info;
	
	/**
	 * Label to light player's name.
	 */
	private JLabel userNameLabel;
	
	/**
	 * Label to dark player's name.
	 */
	private JLabel userNameLabel2;
	
	/**
	 * Layout for pop up window.
	 */
	private GridBagLayout textFieldPanelLayout;
	
	/**
	 * Layout for pop up window.
	 */
	private GridBagLayout buttonPanelLayout;
	
	/**
	 * Set constraints for {@code buttonPanelLayout}.
	 */
	private GridBagConstraints gbc;
	
	/**
	 * Panel for textfields.
	 */
	private JPanel textFieldPanel;
	
	/**
	 * Panel for button.
	 */
	private JPanel buttonPanel;

	/**
	 * It shows the game can start or not.
	 */
    private boolean isOkay = true;
	
    /**
     * Name of the light player.
     */
    private static String userName;
    
    /**
     * Name of the dark player.
     */
    private static String userName2;
    
    /**
     * Constructor of Players.
     */
    public Players(){
    	initPlayers();
	}
	
    /**
     * Initialize the welcome pop up window.
     */
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

	/**
	 * Get the name of the light player.
	 * @return light player's name.
	 */
	public static String getUserName(){
		return userName;
	}
	
	/**
	 * Get the name of the dark player.
	 * @return dark player's name.
	 */
	public static String getUserName2(){
		return userName2;
	}
	
	/**
	 * Check textfields are filled or empty.
	 * @return true if they are filled, false they are empty.
	 */
	public boolean getIsOkay(){
		return isOkay;
	}
	
	/**
	 * Set textfields are filled or they are empty.
	 * @param is tells textfields are filled or empty.
	 */
	public void setIsOkay(boolean is){
		isOkay = is;
	}
	
	/**
	 * Handling the button press. If it is pressed the game will start.
	 * @param e event handling.
	 */
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
