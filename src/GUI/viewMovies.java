package GUI;

import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import myApp.MovieDao;
import myApp.MoviesClient;
import myApp.UserDao;

public class viewMovies extends JFrame
{
	static DefaultTableModel tableModel;
	static JTextField searchField;
	JButton search;
	JButton back;
	JButton delete;
	static MoviesClient client;
	
	public viewMovies() throws Exception
	{
		setSize(600, 430);
		setLocationRelativeTo(null);
		setTitle("View Movies");
		client = new MoviesClient();
		JPanel panel = new JPanel();
		add(panel);
		panelComponents(panel);
	}
	private void panelComponents(JPanel panel) 
	{
		String col[] = {"ID","Title","Age","Director", "Cast"};

		tableModel = new DefaultTableModel(col, 0);
		
		JTable table = new JTable(tableModel);
		table.setRowHeight(50);
		TableColumnModel columnModel = table.getColumnModel();
		columnModel.getColumn(0).setPreferredWidth(40);
		columnModel.getColumn(1).setPreferredWidth(300);
		columnModel.getColumn(2).setPreferredWidth(40);
		columnModel.getColumn(3).setPreferredWidth(200);
		columnModel.getColumn(4).setPreferredWidth(300);
		
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment( JLabel.CENTER );
		table.getColumnModel().getColumn(0).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(1).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(2).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(3).setCellRenderer( centerRenderer );
		table.getColumnModel().getColumn(4).setCellRenderer( centerRenderer );
		
		JScrollPane scrollpane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scrollpane.setPreferredSize(new Dimension(this.getWidth()-50, this.getHeight()-90));
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		panel.add(scrollpane);
		
		JLabel label = new JLabel("Enter ID ");
		panel.add(label);
		searchField = new JTextField(10);
		searchField.setBounds(100, 40, 160, 25);
		panel.add(searchField);
		
		moviesTable();
		
		search = makeButton("Search");
		panel.add(search);
		
		/*delete = makeButton("Delete");
		panel.add(delete);
		*/
		
		panel.add(Box.createHorizontalStrut(80));
		
		back = makeButton("Back");
		panel.add(back);
			
	}
	
	public static void moviesTable()
	{
		try
		{
			tableModel.setRowCount(0);
			for (int i = 0; i < client.getMovies().size(); i++)
			{
				   int id = client.getMovies().get(i).getId();
				   String title = client.getMovies().get(i).getTitle();
				   int Age = client.getMovies().get(i).getAge();
				   String director = client.getMovies().get(i).getDirector();
				   String Cast = client.getMovies().get(i).getCast();

				   Object[] data = {id,title, Age, director, Cast};

				   tableModel.addRow(data);

			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void search()
	{
		try
		{	
		   tableModel.setRowCount(0);
			
		   int id = client.getMovie(Integer.parseInt(searchField.getText())).getId();	
		   String title = client.getMovie(Integer.parseInt(searchField.getText())).getTitle();
		   int Age = client.getMovie(Integer.parseInt(searchField.getText())).getAge();
		   String director = client.getMovie(Integer.parseInt(searchField.getText())).getDirector();
		   String Cast = client.getMovie(Integer.parseInt(searchField.getText())).getCast();

		   Object[] data = {id,title, Age, director, Cast};

		   tableModel.addRow(data);
				
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
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
				if(event.getSource() == search)
				{
					if(searchField.getText().equals(""))
					{
						moviesTable();
					}
					else
					{
						search();
					}
				}
				else if(event.getSource()==back)
				{
					Home home = new Home();
					home.setVisible(true);
					dispose(); //disposes of the last frame
				}
				/*else if(event.getSource()==delete)
				{
					try
					{
						MoviesClient.deleteMovie(Integer.parseInt(searchField.getText()));
					}
					catch(Exception e)
					{
						e.printStackTrace();
					}
					finally
					{
						moviesTable();
					}
				}*/
			}
		}
		
		ButtonListener listener = new ButtonListener();
		button.addActionListener(listener);
		return button;
	}

	
	public static void main(String[] args) throws Exception 
	{
		JFrame frame = new viewMovies();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		
	}
}
