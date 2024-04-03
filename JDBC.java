//jdbc_1.java
import java.sql.*;
import java.util.*;
class jdbc_1{
	public static void main(String args[]) throws Exception {
	// loading jdbc driver
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	//Establish connection to database 
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE", "pat", "pat");
	
	Statement stmt=con.createStatement();
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter FirstName");
	String fname=sc.next();
	System.out.println("Enter LastName");
	String lname=sc.next();
	System.out.println("Enter Email");
	String email=sc.next();
	System.out.println("Enter Pass");
	String pass=sc.next();
	System.out.println("Enter Mobile");
	long mobile =sc.nextLong();
	sc.nextLine();
	System.out.println("enter Address");
	String address= sc.next();
	
	
	int regid=0;
	ResultSet rs= stmt.executeQuery("Select max(regid) from register");
	if (rs.next()) {
		regid=rs.getInt(1);
	}
	regid++;
	
	PreparedStatement pstmt=con.prepareStatement("Insert Into Register values (?,?,?,?,?,?,?)");
	
	pstmt.setInt(1, regid);
	pstmt.setString(2,fname);
	pstmt.setString(3,lname);
	pstmt.setString(4,email);
	pstmt.setString(5,pass);
	pstmt.setLong(6,mobile);
	pstmt.setString(7,address);
	
	//String sql="INsert into register values("+regid+", '"+fname+"','"+name+"','"+email+"', 
	int i=pstmt.executeUpdate();
	if(i==1)
		System.out.println(i+"record inserted");
	pstmt.close(); rs.close(); stmt.close();con.close();
	}
}
