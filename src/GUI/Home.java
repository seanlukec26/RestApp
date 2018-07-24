
package GUI;

import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

import myApp.MovieDao;
import myApp.UserDao;

public class Home extends JFrame 
{
	JButton addButton;
	JButton ViewButton;
	JButton UpdateButton;
	
	public Home()
	{
		setSize(350, 150);
		setLocationRelativeTo(null);
		setTitle("Home");
		setVisible(true);
		
		JPanel panel = new JPanel();
		add(panel);
		panelComponents(panel);
	}
	private void panelComponents(JPanel panel) 
	{
		createMenuBar();
		addButton = makeButton("Add Movie");
		panel.add(addButton);
		
		ViewButton = makeButton("View Movies");
		panel.add(ViewButton);
		
		UpdateButton = makeButton("Update Movie");
		panel.add(UpdateButton);
	
	}
	
	private void createMenuBar() {

        JMenuBar menubar = new JMenuBar();
        ImageIcon icon = new ImageIcon("signout.png");

        JMenu user = new JMenu(UserDao.User);
        MovieDao.instance.getKey(UserDao.User);
        user.setCursor(new Cursor(Cursor.HAND_CURSOR));
        //file.setMnemonic(KeyEvent.VK_F);

        JMenuItem eMenuItem = new JMenuItem("Logout", icon);
        eMenuItem.setCursor(new Cursor(Cursor.HAND_CURSOR));
        eMenuItem.setToolTipText("Logout");
        eMenuItem.addActionListener( new ActionListener() 
		{
		    @Override
		    public void actionPerformed( ActionEvent aActionEvent ) 
		    {
		      Login login = new Login();
		      login.setVisible(true);
		      dispose();
		    }
		  } );

        user.add(eMenuItem);

        menubar.add(user);

        setJMenuBar(menubar);
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
				if(event.getSource() == addButton)
				{
					try
					{
					addMovies add = new addMovies();
					add.setVisible(true);
					dispose(); //disposes of the last frame
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
				else if(event.getSource()==ViewButton)
				{
					viewMovies view = null;
					try {
						view = new viewMovies();
					} catch (Exception e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					view.setVisible(true);
					dispose(); //disposes of the last frame
				}
				else if(event.getSource()==UpdateButton)
				{
					try
					{
						updateMovie up = new updateMovie();
						up.setVisible(true);
						dispose(); //disposes of the last frame
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
				}
			}
		}
		
		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		return button;
	}
	
	public static void main(String[] args) 
	{
		JFrame frame = new Home();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}

}
