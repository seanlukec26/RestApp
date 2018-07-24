
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

import myApp.Movie;
import myApp.MovieDao;
import myApp.MoviesClient;

public class updateMovie extends JFrame 
{
	JButton aButton;
	JButton bButton;
	JButton search;
	JTextField searchField;
	JTextField newTitleField;
	JTextField newAgeField;
	JTextField newDirectorField;
	JTextField newCastField;
	static MoviesClient client;
	
	public updateMovie() throws Exception
	{
		setSize(274, 330);
		setLocationRelativeTo(null);
		setTitle("Update Movie");
		
		client = new MoviesClient();
		
		JPanel panel = new JPanel();
		add(panel);
		panelComponents(panel);
		
	}
	private void panelComponents(JPanel panel) 
	{
		JLabel label = new JLabel("Enter ID ");
		panel.add(label);
		searchField = new JTextField(10);
		searchField.setBounds(100, 40, 160, 25);
		panel.add(searchField);
		
		search = makeButton("Search");
		panel.add(search);
		
		panel.add(Box.createVerticalStrut(50));
		
		JLabel newTitleLabel = new JLabel("Title:");
		newTitleLabel.setBounds(10, 10, 80, 25);
		panel.add(newTitleLabel);

		newTitleField = new JTextField(20);
		newTitleField.setBounds(100, 10, 160, 25);
		panel.add(newTitleField);

		JLabel ageLabel = new JLabel("Age:");
		ageLabel.setBounds(10, 40, 80, 25);
		panel.add(ageLabel);

		newAgeField = new JTextField(20);
		newAgeField.setBounds(100, 40, 160, 25);
		panel.add(newAgeField);
		
		JLabel directorLabel = new JLabel("Director:");
		directorLabel.setBounds(10, 40, 80, 25);
		panel.add(directorLabel);

		newDirectorField = new JTextField(20);
		newDirectorField.setBounds(100, 40, 160, 25);
		panel.add(newDirectorField);
		
		JLabel castLabel = new JLabel("Cast:");
		castLabel.setBounds(10, 40, 80, 25);
		panel.add(castLabel);

		newCastField = new JTextField(20);
		newCastField.setBounds(100, 40, 160, 25);
		panel.add(newCastField);
		
		panel.add(Box.createVerticalStrut(30));
		
		aButton = makeButton("Update");
		panel.add(aButton);
		
		bButton = makeButton("Back");
		panel.add(bButton);
	
	}
	public void search() throws Exception
	{
		newTitleField.setText(client.getMovie(Integer.parseInt(searchField.getText())).getTitle());
		newAgeField.setText(Integer.toString(client.getMovie(Integer.parseInt(searchField.getText())).getAge()));
		newDirectorField.setText(client.getMovie(Integer.parseInt(searchField.getText())).getDirector());
		newCastField.setText(client.getMovie(Integer.parseInt(searchField.getText())).getCast());
	
	}
	public void update() throws Exception
	{
		client.updateMovie(Integer.parseInt(searchField.getText()), newTitleField.getText(), Integer.parseInt(newAgeField.getText()), newDirectorField.getText(), newCastField.getText());

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
						update();
						JOptionPane.showMessageDialog(null, "Movie Updated");
						searchField.setText("");newTitleField.setText("");newAgeField.setText("");newDirectorField.setText("");newCastField.setText("");
					}
					catch(Exception e)
					{
						JOptionPane.showMessageDialog(null, "Please Try Again. Fill in all fields");
						e.printStackTrace();
					}
				}
				else if(event.getSource()==search)
				{
					try 
					{
						search();
					}
					catch (Exception e) 
					{
						// TODO Auto-generated catch block
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
		JFrame frame = new updateMovie();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
