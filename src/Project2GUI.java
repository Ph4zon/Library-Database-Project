import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class Project2GUI {

	private static JFrame frame;
	/**
	 * @wbp.nonvisual location=-153,359
	 */
	private final JPanel panel = new JPanel();
	private final JLabel lblSearch = new JLabel("Search:");
	private final JTextField textFieldSearchISBN = new JTextField();
	private final JButton btnSearch = new JButton("Search");
	private final JLabel lblIsbn = new JLabel("ISBN:");
	private final JLabel lblTitle = new JLabel("Title:");
	private final JTextField textFieldSearchTitle = new JTextField();
	private final JLabel lblAuthor = new JLabel("Author:");
	private final JTextField textFieldSearchAuthor = new JTextField();
	
	// Borrower section
	private final JLabel lblBorrowerCheckin = new JLabel("Borrower Check-In:--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	private final JPanel panel_1 = new JPanel();
	private JTextField textFieldBorrowerISBN;
	private JTextField textFieldBorrowerID;
	private JTextField textFieldBorrowerName;
	private JButton btnCreateBorrower = new JButton("Create New Borrower");
	
	// Fines section
	private final JLabel lblFinesSection = new JLabel("Fines:-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
	private final JPanel finePanel = new JPanel();
	private JButton btnRefreshFine = new JButton("View/Refresh");
	private JButton btnPayFine = new JButton("Pay Fine");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				Project2GUI window = new Project2GUI();
				Project2GUI.frame.setVisible(true);
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Project2GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		textFieldSearchAuthor.setBounds(20, 113, 305, 20);
		textFieldSearchAuthor.setColumns(10);
		textFieldSearchTitle.setBounds(20, 75, 305, 20);
		textFieldSearchTitle.setColumns(10);
		try {
			//Input username and password to access mysql
//			Scanner in = new Scanner(System.in);
			String username;
			String password;
//			password = in.nextLine();
			
			username = JOptionPane.showInputDialog(frame, "Enter Username for Database Access:");
			if (username == null) 
				System.exit(0);
			
			password = JOptionPane.showInputDialog(frame, "Enter Password for Database Access:");
			if (password == null)
				System.exit(0);
				
//			System.out.println(password);
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/library";
			
			// Test Login
			Connection con = DriverManager.getConnection(url, username, password);
			con.close();

			textFieldSearchISBN.setToolTipText("Press ENTER to submit");
			textFieldSearchISBN.setBounds(20, 39, 305, 20);
			textFieldSearchISBN.setColumns(10);
			frame = new JFrame();
			frame.setBounds(100, 100, 1034, 660);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			panel.setLayout(null);
			lblSearch.setBounds(10, 14, 65, 14);
			
			panel.add(lblSearch);
			
			panel.add(textFieldSearchISBN);
			
//			ButtonGroup updateButtons = new ButtonGroup();
			btnSearch.setBounds(335, 112, 89, 23);
			
			panel.add(btnSearch);
			lblIsbn.setBounds(20, 26, 46, 14);
			
			panel.add(lblIsbn);
			lblTitle.setBounds(20, 63, 46, 14);
			
			panel.add(lblTitle);
			
			panel.add(textFieldSearchTitle);
			lblAuthor.setBounds(20, 101, 46, 14);
			
			panel.add(lblAuthor);
			
			panel.add(textFieldSearchAuthor);

			lblBorrowerCheckin.setBounds(10, 197, 894, 14);
			panel.add(lblBorrowerCheckin);
			
			DefaultListModel<String> model = new DefaultListModel<String>();
			panel_1.setBounds(449, 14, 559, 131);
			
			panel.add(panel_1);
			panel_1.setLayout(new BorderLayout(0, 0));
			JList<String> bookList = new JList<String>(model);
			panel_1.add(bookList, BorderLayout.CENTER);
			
			JButton btnCheckOut = new JButton("Check Out");
			btnCheckOut.setBounds(669, 156, 147, 23);
			panel.add(btnCheckOut);
			
			JLabel lblInfo = new JLabel("Info:");
			lblInfo.setBounds(10, 222, 46, 14);
			panel.add(lblInfo);
			
			JLabel lblBorrowerISBN = new JLabel("ISBN:");
			lblBorrowerISBN.setBounds(20, 236, 46, 14);
			panel.add(lblBorrowerISBN);
			
			textFieldBorrowerISBN = new JTextField();
			textFieldBorrowerISBN.setBounds(20, 251, 305, 20);
			panel.add(textFieldBorrowerISBN);
			textFieldBorrowerISBN.setColumns(10);
			
			JLabel lblCardId = new JLabel("Card ID:");
			lblCardId.setBounds(20, 274, 46, 14);
			panel.add(lblCardId);
			
			textFieldBorrowerID = new JTextField();
			textFieldBorrowerID.setBounds(20, 287, 305, 20);
			panel.add(textFieldBorrowerID);
			textFieldBorrowerID.setColumns(10);
			
			JLabel lblName = new JLabel("Name:");
			lblName.setBounds(20, 312, 46, 14);
			panel.add(lblName);
			
			textFieldBorrowerName = new JTextField();
			textFieldBorrowerName.setBounds(20, 325, 305, 20);
			panel.add(textFieldBorrowerName);
			textFieldBorrowerName.setColumns(10);
			
			JButton btnBorrowerSearch = new JButton("Search");
			btnBorrowerSearch.setBounds(335, 324, 89, 23);
			panel.add(btnBorrowerSearch);
			
			JPanel panel_2 = new JPanel();
			panel_2.setBounds(449, 222, 559, 131);
			panel.add(panel_2);
			panel_2.setLayout(new BorderLayout(0, 0));
			
			DefaultListModel<String> borrowerModel = new DefaultListModel<String>();
			
			JList<String> borrowerList = new JList<String>(borrowerModel);
			panel_2.add(borrowerList, BorderLayout.CENTER);
			
			JButton btnCheckIn = new JButton("Check In");
			btnCheckIn.setBounds(669, 364, 147, 23);
			panel.add(btnCheckIn);

			btnCreateBorrower.setBounds(20, 370, 175, 23);
			panel.add(btnCreateBorrower);
			
			lblFinesSection.setBounds(10, 415, 894, 14);
			panel.add(lblFinesSection);

			finePanel.setBounds(449, 440, 559, 131);
			panel.add(finePanel);
			finePanel.setLayout(new BorderLayout(0,0));

			DefaultListModel<String> fineModel = new DefaultListModel<String>();
			JList<String> fineList = new JList<String>(fineModel);
			finePanel.add(fineList, BorderLayout.CENTER);

			btnRefreshFine.setBounds(449, 581, 157, 23);
			panel.add(btnRefreshFine);

			btnPayFine.setBounds(861, 581, 147, 23);
			btnPayFine.setVisible(false);
			panel.add(btnPayFine);

			//MouseListener for the search button
			btnSearch.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
				//	System.out.println(textFieldSearch.getText().charAt(1));
					ResultSet rs;
					String searchQuery;
					model.clear();
					panel_1.removeAll();
					panel_1.revalidate();
					panel_1.repaint();
//						System.out.println(textFieldSearch.getText().charAt(1));{
						if (textFieldSearchTitle.getText().equals("") && textFieldSearchISBN.getText().equals("") && textFieldSearchAuthor.getText().equals(""))
							JOptionPane.showMessageDialog(frame, "Please Enter an Item to Search", "Search", JOptionPane.WARNING_MESSAGE);
						else {
						//Query to search for book
							try (Connection con = DriverManager.getConnection(url, username, password)){
								Statement stmt = con.createStatement();
								searchQuery = GeneralSearch.querySearch(textFieldSearchTitle.getText().trim(), textFieldSearchISBN.getText().trim(), textFieldSearchAuthor.getText().trim());
								//////////////////////////////////////////////////////////////////////////////////////////////
//								System.out.println(searchQuery);
								rs = stmt.executeQuery(searchQuery); 
								int count = 0;
								ArrayList<String> list = new ArrayList<String>();
								//if nothing is entered into the search field
								if (!rs.next())
									JOptionPane.showMessageDialog(frame, "Book Is Not In This Library", "Search", JOptionPane.WARNING_MESSAGE);
								
								//else if there is something in the search field
								else {
									count++;
//									System.out.println("test");
									list.add(rs.getString(2) + "   " + rs.getString(3) + "   " + rs.getString(4));
									while (rs.next()) {
										list.add(rs.getString(2) + "   " + rs.getString(3) + "   " + rs.getString(4));
										count++;
										// TODO get availability to print
//										if (stmt.executeQuery("SELECT * FROM book_loans WHERE isbn='" + rs.getString(2) + "';").next())
//											System.out.println("Unavailable");
//										else
//											System.out.println("Available");
									}
									for (int i = 0; i < count; i++) {
										String temp = list.get(i);
										list.remove(i);
										if (stmt.executeQuery("SELECT * FROM book_loans WHERE isbn='" + temp.substring(0,10) + "';").next())
											list.add(i, "Unavailable   " + temp);
										else
											list.add(i, "Available       " + temp);
									}	
										
									for (int i = 0; i < count; i++) {
										model.addElement(list.get(i));
									}
									
									list.clear();
									
									JScrollPane menuScrollPane = new JScrollPane(bookList);
									menuScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
									menuScrollPane.setBounds(31, 173, 100, 100);
									panel_1.add(menuScrollPane);
								}
								
								
							} catch (SQLException e1) {
								
								System.out.println(e1);
							}
						}
				}
			});
			
			btnCheckOut.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int checkOut;
					int cardNo;
					int numCheckedOut = 0;
					String isbn;
					ResultSet rs;
					//get isbn from the result set of the search
					String line = bookList.getSelectedValue();
					if(line != null){
						checkOut = JOptionPane.showConfirmDialog(frame, "Would You Like To Check This Book Out?", "Check Out", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
						if (checkOut == 0)	{
							try(Connection con = DriverManager.getConnection(url, username, password)) {
								Statement stmt = con.createStatement();
								String[] tokenize = line.split("\\s+");
								isbn = tokenize[1];
	//							System.out.println(isbn);
								rs = stmt.executeQuery("SELECT * FROM book_loans WHERE isbn='" + isbn + "';");
								//if the book is available
								if (!rs.next())
								{
									cardNo = Integer.parseInt(JOptionPane.showInputDialog(frame, "Please Enter Your Card ID Number:"));
									rs = stmt.executeQuery("SELECT * FROM borrower WHERE card_id=" + cardNo + ";");
									//if the card number exists
									if (rs.next()) {
										rs = stmt.executeQuery("SELECT * FROM book_loans WHERE card_id=" + cardNo + " AND date_in IS null;");
										while (rs.next())
											numCheckedOut++;
										//if number of checked out books is less than 3 then allow for check out
										if (numCheckedOut < 3) {
											Date currentDate = new Date();
											DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
											currentDate = Calendar.getInstance().getTime();
											UUID uuid = UUID.randomUUID();
											
											stmt.execute("INSERT INTO book_loans VALUES ('" + uuid + "', '" + isbn + "', " + cardNo + ", '" 
															+ format.format(currentDate) + "', '" + (format.format(currentDate.getTime() + 14 * 86400000)) + "', NULL);");
											JOptionPane.showMessageDialog(frame, "Book checked out", "Check Out", JOptionPane.INFORMATION_MESSAGE);
										} else //too many books are checked out
											JOptionPane.showMessageDialog(frame, "You Already Have 3 Books Checked Out", "Check Out", JOptionPane.WARNING_MESSAGE);
									} else //card number entered does not exist
										JOptionPane.showMessageDialog(frame, "The Card ID Number That You Entered Does Not Exist", "Check Out", JOptionPane.WARNING_MESSAGE);
								} else //the book is unavailable
									JOptionPane.showMessageDialog(frame, "The Book You Selected Is Currently Unavailable", "Check Out", JOptionPane.WARNING_MESSAGE);
							}catch (Exception e1) {
								e1.printStackTrace();
							}
						}
					}else{
						JOptionPane.showMessageDialog(frame, "Please select a book to check-out", "Check Out", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			
			btnBorrowerSearch.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					ResultSet rs;
					String searchQuery;
					borrowerModel.clear();
					if (textFieldBorrowerISBN.getText().equals("") && textFieldBorrowerID.getText().equals("") && textFieldBorrowerName.getText().equals(""))
						JOptionPane.showMessageDialog(frame, "Please Enter An Item To Search For A Borrower", "Check In", JOptionPane.WARNING_MESSAGE);
					else {
						try(Connection con = DriverManager.getConnection(url, username, password)) {
							Statement stmt = con.createStatement();
							searchQuery = BorrowerSearch.querySearch(textFieldBorrowerISBN.getText().trim(), textFieldBorrowerID.getText().trim(), textFieldBorrowerName.getText().trim());
							rs = stmt.executeQuery(searchQuery);
							ArrayList<String> list = new ArrayList<String>();
	//						 System.out.println("test2");
							if (!rs.next())
								JOptionPane.showMessageDialog(frame, "The Borrower You Are Searhing For Does Not Have A Book Checked Out", "Check In", JOptionPane.WARNING_MESSAGE);
							else {
								list.add(rs.getString(2) + "   " + rs.getString(1) + "   " + rs.getString(3) + "   " + rs.getString(5) + "   " + rs.getString(6) + "   " + rs.getString(8));
								while (rs.next()) 
									list.add(rs.getString(2) + "   " + rs.getString(1) + "   " + rs.getString(3) + "   " + rs.getString(5) + "   " + rs.getString(6) + "   " + rs.getString(8));
								
								for (int i = 0; i < list.size(); i++)
									borrowerModel.addElement(list.get(i));
								
								panel_2.removeAll();
								panel_2.revalidate();
								panel_2.repaint();
								JScrollPane borrowerScrollPane = new JScrollPane(borrowerList);
								borrowerScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
								panel_2.add(borrowerScrollPane);
							}
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
					}
				}
			});
			
			btnCheckIn.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					int choice;
					String line = borrowerList.getSelectedValue();
					if(line != null){
						String loan_id = line.toString().substring(0, 36);
						choice = JOptionPane.showConfirmDialog(frame, "Turn In Book?", "Check In", JOptionPane.YES_NO_OPTION);
						if (choice == 0)
						{
							Date currentDate = new Date();
							DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
							currentDate = Calendar.getInstance().getTime();
	//						System.out.println(format.format(currentDate));
							try(Connection con = DriverManager.getConnection(url, username, password)) {
								Statement stmt = con.createStatement();
								int check = stmt.executeUpdate("UPDATE book_loans SET date_in = '" + format.format(currentDate) + "' WHERE loan_id ='" + loan_id + "' AND date_in IS NULL;");
								if(check == 0){
									JOptionPane.showMessageDialog(frame, "Book has already been checked in.", "Check-In", JOptionPane.WARNING_MESSAGE);
								}else{
									JOptionPane.showMessageDialog(frame, "Book has been checked in!", "Check-In", JOptionPane.INFORMATION_MESSAGE);
								}
							} catch (SQLException e1) {
								e1.printStackTrace();
							}
						}
					} else{
						JOptionPane.showMessageDialog(frame, "Please select a book to check-in.", "Check In", JOptionPane.WARNING_MESSAGE);
					}
				}
			});
			
			btnCreateBorrower.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					NewBorrowerGUI newBorrower = new NewBorrowerGUI(url, username, password);
					newBorrower.setSize(300, 300);
					newBorrower.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					newBorrower.setLocationRelativeTo(null);
					newBorrower.setVisible(true);
				}
			});

			/*
			On top of viewing the fines, this button:
				1. Adds new fines
				2. Updates all fine_amt depending on the current date.
			*/
			btnRefreshFine.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					// Update Fines Table
					finePanel.removeAll();
					finePanel.revalidate();
					finePanel.repaint();
					try(Connection con = DriverManager.getConnection(url, username, password)){
						Statement stmt = con.createStatement();
						fineModel.clear();
						ResultSet rs;

						// Insert new fines if it does not exist
						ArrayList<String> loanLateList = new ArrayList<String>();
						ArrayList<String> fineLateList = new ArrayList<String>();
						rs = stmt.executeQuery("SELECT loan_id, DATEDIFF(CURDATE(), due_date) FROM book_loans WHERE DATEDIFF(CURDATE(), due_date) > 0;");
						while(rs.next()){
							loanLateList.add(rs.getString(1));
							fineLateList.add(Float.toString(rs.getInt(2)*(float)0.25));
						}

						for(int i = 0; i < loanLateList.size(); i++){
							rs = stmt.executeQuery("SELECT * FROM fines WHERE loan_id = \"" + loanLateList.get(i) + "\";");
							if(!rs.next()){
								stmt.execute("INSERT INTO fines (loan_id, fine_amt, paid) SELECT loan_id, " + fineLateList.get(i) + ", 0 FROM book_loans WHERE loan_id = \"" + loanLateList.get(i) + "\";");
							}
						}

						// Update all, old unpaid fines
						stmt.executeUpdate("UPDATE fines JOIN book_loans ON fines.loan_id = book_loans.loan_id SET fine_amt = DATEDIFF(CURDATE(), due_date) * 0.25 WHERE paid = 0;");

						// Print Fine View List
						String viewQuery;
						ArrayList<String> list = new ArrayList<String>();
						viewQuery = "SELECT card_id, bname, phone, SUM(fine_amt) FROM borrower STRAIGHT_JOIN book_loans USING (card_id) STRAIGHT_JOIN fines ON book_loans.loan_id = fines.loan_id WHERE paid = 0 GROUP BY card_id ORDER BY card_id;";
						rs = stmt.executeQuery(viewQuery);
						while(rs.next()){
							String fine_amt = String.format("%.2f", rs.getFloat(4));
							list.add(rs.getString(1) + "   " + rs.getString(2) + "   " + rs.getString(3) + "   $" + fine_amt);
						}
						for(int i = 0; i < list.size(); i++)
							fineModel.addElement(list.get(i));
						JScrollPane fineScrollPane = new JScrollPane(fineList);
						fineScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
						finePanel.add(fineScrollPane);
						if(list.size()>0)
							btnPayFine.setVisible(true);
					}catch(SQLException x){
						x.printStackTrace();
					}
				}
			});

			btnPayFine.addMouseListener(new MouseAdapter(){
				@Override
				public void mouseClicked(MouseEvent e){
					String line = fineList.getSelectedValue();	// list is in form "card_id bname phone SUM(fine_amt)"
					if(line != null){
						String[] tokenized = line.split("\\s+");	// split by space
						String card_id = tokenized[0];				// obtain card_id
						int choice = JOptionPane.showConfirmDialog(frame, "Fine paid?", "Pay Fine", JOptionPane.YES_NO_OPTION);
						if(choice == 0){
							try(Connection con = DriverManager.getConnection(url, username, password)) {
								Statement stmt = con.createStatement();
								int check = stmt.executeUpdate("UPDATE fines JOIN book_loans ON fines.loan_id = book_loans.loan_id SET paid = 1 WHERE card_id = " + card_id + " AND date_in IS NOT NULL AND paid = 0;");
								if(check == 0){
									JOptionPane.showMessageDialog(frame, "Book(s) have not yet been returned.", "Pay Fine", JOptionPane.ERROR_MESSAGE);
								}else{
									JOptionPane.showMessageDialog(frame, "Payment has been made.", "Pay Fine", JOptionPane.INFORMATION_MESSAGE);
								}
							}catch(SQLException x){
								x.printStackTrace();
							}
						}
					}else{
						JOptionPane.showMessageDialog(frame, "Please select a fine to pay.", "Pay Fine", JOptionPane.WARNING_MESSAGE);
					}
				}
			});	
		} catch (SQLException e) {
			System.out.println("Nick is bad at SQL :P");
			JOptionPane.showMessageDialog(frame, "Cannot access database:\n - Check user login and password\n - Make sure database is open for connection\n Exiting Program", "Error", JOptionPane.ERROR_MESSAGE);
			System.exit(1);
				
		} catch (ClassNotFoundException e) {
			System.out.println(e + ": Cannot locate driver!");
		}
	}
}