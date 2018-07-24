

package GUI;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import myApp.MovieDao;
import myApp.UserDao;

public class Login extends JFrame 
{
	private JButton LoginButton;
	private JButton RegisterButton;
	private JTextField usernameField;
	private JPasswordField passwordField;
	static JFrame frame;
	
	public Login()
	{
		setSize(350, 150);
		setLocationRelativeTo(null);
		setTitle("Login");
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

		usernameField = new JTextField(20);
		usernameField.setBounds(100, 10, 160, 25);
		panel.add(usernameField);

		JLabel passwordLabel = new JLabel("Password:");
		passwordLabel.setBounds(10, 40, 80, 25);
		panel.add(passwordLabel);

		passwordField = new JPasswordField(20);
		passwordField.setBounds(100, 40, 160, 25);
		panel.add(passwordField);

		LoginButton = makeButton("Login");
		panel.add(LoginButton);
		
		RegisterButton = makeButton("Register");
		panel.add(RegisterButton);
	
	}
	public JButton makeButton(String name)
	{
		JButton button = new JButton(name);
		button.setCursor(new Cursor(Cursor.HAND_CURSOR));
		button.setBounds(180, 80, 80, 25);
		
		class ButtonListener implements ActionListener
		{
			public void actionPerformed(ActionEvent event)
			{
				if(event.getSource() == LoginButton)
				{
					MovieDao.instance.getKey(usernameField.getText());
					UserDao.checkUser(usernameField.getText(), passwordField.getText());
					dispose();
				}
				else if(event.getSource()==RegisterButton)
				{
					Register view = new Register();
					view.setVisible(true);
					dispose(); //disposes of the last frame
				}
			}
		}
		
		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		return button;
	}
	
	public static void main(String[] args) 
	{
		frame = new Login();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public final static JFrame getFrame()
	{
        return frame;
    }

}

