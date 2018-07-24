package GUI;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Security.GenerateSecretKey;
import myApp.UserDao;

public class Register extends JFrame
{
	public Register()
	{
		setSize(350, 180);
		setLocationRelativeTo(null);
		setTitle("Home");
		setVisible(true);
		
		JPanel panel = new JPanel();
		add(panel);
		panelComponents(panel);
		
	}
	private void panelComponents(JPanel panel) 
	{
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(10, 10, 80, 25);
		panel.add(usernameLabel);

		JTextField usernameField = new JTextField(20);
		usernameField.setBounds(100, 10, 160, 25);
		panel.add(usernameField);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		JPasswordField passwordField = new JPasswordField(20);
		passwordField.setBounds(100, 40, 160, 25);
		panel.add(passwordField);
		
		JLabel addressLabel = new JLabel("Address:");
		addressLabel.setBounds(10, 40, 80, 25);
		panel.add(addressLabel);

		JTextField addressField = new JTextField(20);
		addressField.setBounds(100, 40, 160, 25);
		panel.add(addressField);
		
		JButton rButton = new JButton("Register");
		rButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		rButton.setBounds(180, 80, 80, 25);
		panel.add(rButton);
		rButton.addActionListener( new ActionListener() 
		{
		    @Override
		    public void actionPerformed(ActionEvent aActionEvent) 
		    {
		    	try {
					GenerateSecretKey.GenerateKey(usernameField.getText());
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		    	UserDao.createUser(usernameField.getText(), passwordField.getText(), addressField.getText());
		    	Login login = new Login();
				login.setVisible(true);
				dispose(); //disposes of the last frame
		    }
		  } );
		
		JButton bButton = new JButton("Back");
		bButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		panel.add(bButton);
		bButton.addActionListener( new ActionListener() 
		{
		    @Override
		    public void actionPerformed( ActionEvent aActionEvent ) 
		    {
		    	Login login = new Login();
				login.setVisible(true);
				dispose(); 
		    }
		  } );
	}
	
	public static void main(String[] args) 
	{
		JFrame frame = new Register();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

}
