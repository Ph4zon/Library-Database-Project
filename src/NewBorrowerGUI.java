import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class NewBorrowerGUI extends JFrame{
    private JPanel panel = new JPanel();
    private JLabel lblName = new JLabel("Name:*");
    private JTextField tfName = new JTextField();
    private JLabel lblSSN = new JLabel("SSN:*");
    private JTextField tfSSN = new JTextField();
    private JLabel lblAddress = new JLabel("Address:*");
    private JTextField tfAddress = new JTextField();
    private JLabel lblPhone = new JLabel("Phone:");
    private JTextField tfPhone = new JTextField();
    private JButton btnCreate = new JButton("Create");
    private JButton btnCancel = new JButton("Cancel");
    private JLabel lblNeed = new JLabel("* Indicates necessary fields");

    private String name;
    private String ssn;
    private String address;
    private String phone;

    public NewBorrowerGUI(String url, String user, String pass){
        super("Create New Borrower");
        lblName.setBounds(10, 10, 45, 14);
        panel.add(lblName);
        tfName.setBounds(10, 25, 270, 20);
        panel.add(tfName);

        lblSSN.setBounds(10, 55, 45, 14);
        panel.add(lblSSN);
        tfSSN.setBounds(10, 70, 270, 20);
        panel.add(tfSSN);

        lblAddress.setBounds(10, 100, 60, 14);
        panel.add(lblAddress);
        tfAddress.setBounds(10, 115, 270, 20);
        panel.add(tfAddress);

        lblPhone.setBounds(10, 145, 45, 14);
        panel.add(lblPhone);
        tfPhone.setBounds(10, 160, 270, 20);
        panel.add(tfPhone);

        lblNeed.setBounds(10, 185, 200, 14);
        panel.add(lblNeed);

        btnCreate.setBounds(50, 215, 90, 25);
        panel.add(btnCreate);
        btnCancel.setBounds(150, 215, 90, 25);
        panel.add(btnCancel);

        btnCreate.addMouseListener(new MouseAdapter(){ 
            @Override
            public void mouseClicked(MouseEvent e) {
                name = tfName.getText();
                ssn = tfSSN.getText();
                address = tfAddress.getText();
                phone = tfPhone.getText();
                if(name == null || name.isEmpty() || ssn == null || ssn.isEmpty() || address == null || address.isEmpty()){
                    showErrorNeed();
                    return;
                }
                if(!Pattern.matches("\\d{9}", ssn)){
                    showIncorrectFormat();
                    return;
                }
                try(Connection con = DriverManager.getConnection(url, user, pass)){
                    Statement stmt = con.createStatement();

                    // Check for duplicate SSN
                    String query = "SELECT ssn FROM borrower WHERE ssn = " + ssn +";";
                    ResultSet rs = stmt.executeQuery(query);
                    if(rs.next()){
                        showDupError();
                        cancelFrame();
                    }

                    // Retrieve largest ID
                    rs = stmt.executeQuery("SELECT MAX(card_id) FROM borrower");
                    rs.next();
                    int id = rs.getInt(1)+1;

                    // Add borrower to db
                    String ssnFormatted = ssn.substring(0, 3) + "-" + ssn.substring(3, 5) + "-" + ssn.substring(5);
                    String phoneFormatted;
                    if(phone.length()>=10){
                        phoneFormatted = "(" + phone.substring(0, 3) + ") " + phone.substring(3, 6) + "-" + phone.substring(6);
                    }else{
                        phoneFormatted = phone;
                    }
                    String addQuery = "INSERT INTO borrower VALUES(" + Integer.toString(id) + ",\"" + ssnFormatted + "\",\"" + name + "\",\"" + address + "\",\"" + phoneFormatted + "\");";
                    stmt.execute(addQuery);
                    showSuccess();
                    cancelFrame();
                } catch(SQLException x){
                    showException();
                    cancelFrame();
                }
            }
        });

        btnCancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                cancelFrame();
            }
        });

        panel.setLayout(null);
        this.getContentPane().add(panel, BorderLayout.CENTER);
    }

    public void cancelFrame(){
        this.dispose();
    }

    private void showSuccess(){
        JOptionPane.showMessageDialog(this, "User has been added!", "Create New Borrower", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showErrorNeed(){
        JOptionPane.showMessageDialog(this, "Missing necessary fields!", "Error", JOptionPane.WARNING_MESSAGE);
    }

    private void showIncorrectFormat(){
        JOptionPane. showMessageDialog(this, "SSN should be 10 digit numbers.", "Error", JOptionPane.WARNING_MESSAGE);
    }

    private void showException(){
        JOptionPane.showMessageDialog(this, "Unknown error has occured, try again later.", "Unknown Error", JOptionPane.ERROR_MESSAGE);
    }

    private void showDupError(){
        JOptionPane.showMessageDialog(this, "User already exist!", "Create New Borrower", JOptionPane.WARNING_MESSAGE);
    }
}