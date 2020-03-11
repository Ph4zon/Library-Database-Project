import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.Date;

public class TestSQL {
	public static void main(String[] args)
	{
//		try{  
//			Class.forName("com.mysql.cj.jdbc.Driver");  
//			Connection con=DriverManager.getConnection(  
//			"jdbc:mysql://localhost:3306/company","root","Metroid2");  
//			//here sonoo is database name, root is username and password  
//			Statement stmt=con.createStatement();  
//			ResultSet rs=stmt.executeQuery("");  
//			System.out.println(stmt.execute("INSERT INTO employee VALUES ('Nicholas', 'C', 'Rhein',"
//					+ "'696969696', '1998-09-02', '7201 Cloverleaf Drive, Plano, TX', 'M', 90000.00, NULL, 1);"));
//			System.out.println(rs);
//			while(rs.next())  
//				//System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
//			con.close();  
//			} catch(SQLException e) { 
//				System.out.println(e);
//			} catch(ClassNotFoundException e) { 
//				System.out.println(e);
//			}  
		
		//Get current date and the day 14 days from then
		DateFormat format = new SimpleDateFormat("MM-dd-yyy");
		Date test = new Date();
		test = Calendar.getInstance().getTime();
	//	test.setTime(test.getTime() + 14 * 86400000);
		System.out.println("current date is: " + format.format(test));
		System.out.println("The date 14 days from now is: " + (format.format(test.getTime() + 14 * 86400000)));
		
	}  
}

//INSERT INTO employee VALUES ("Nicholas", "C", "Rhein", '696969696', 09/02/98, "7201 Cloverleaf Drive, Plano, TX", 'M', 90000.00, , 1);