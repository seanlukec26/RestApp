
package GUI;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import myApp.MoviesClient;

public class addMovies extends JFrame 
{
	JButton aButton;
	JButton bButton;
	JTextField titleField;
	JTextField ageField;
	JTextField directorField;
	JTextField castField;
	static MoviesClient client;
	
	public addMovies() throws Exception
	{
		setSize(274, 300);
		setLocationRelativeTo(null);
		setTitle("Add Movies");
		
		client = new MoviesClient();
		
		JPanel panel = new JPanel();
		add(panel);
		panelComponents(panel);
		
	}
	private void panelComponents(JPanel panel) 
	{
		JLabel titleLabel = new JLabel("Title:");
		titleLabel.setBounds(10, 10, 80, 25);
		panel.add(titleLabel);

		titleField = new JTextField(20);
		titleField.setBounds(100, 10, 160, 25);
		panel.add(titleField);

		JLabel ageLabel = new JLabel("Age:");
		ageLabel.setBounds(10, 40, 80, 25);
		panel.add(ageLabel);

		ageField = new JTextField(20);
		ageField.setBounds(100, 40, 160, 25);
		panel.add(ageField);
		
		JLabel directorLabel = new JLabel("Director:");
		directorLabel.setBounds(10, 40, 80, 25);
		panel.add(directorLabel);

		directorField = new JTextField(20);
		directorField.setBounds(100, 40, 160, 25);
		panel.add(directorField);
		
		JLabel castLabel = new JLabel("Cast:");
		castLabel.setBounds(10, 40, 80, 25);
		panel.add(castLabel);

		castField = new JTextField(20);
		castField.setBounds(100, 40, 160, 25);
		panel.add(castField);
		
		panel.add(Box.createVerticalStrut(50));
		
		aButton = makeButton("Add");
		panel.add(aButton);
		
		bButton = makeButton("Back");
		panel.add(bButton);
	
	}
	public void add() throws Exception
	{
		int id = 0;
	    client.addMovie(id,titleField.getText(),Integer.parseInt(ageField.getText()),directorField.getText(),castField.getText());

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
				if(event.getSource() == bButton)
				{
					Home home = new Home();
					home.setVisible(true);
					dispose(); //disposes of the last frame
				}
				else if(event.getSource()==aButton)
				{
					try
					{
						add();
						JOptionPane.showMessageDialog(null, "Movie Added");
					    titleField.setText("");ageField.setText("");directorField.setText("");castField.setText("");
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, "Please Try Again");
						e.printStackTrace();
					}
				}
			}
		}
		
		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		return button;
	}
	
	public static void main(String[] args) throws Exception
	{
		JFrame frame = new addMovies();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
