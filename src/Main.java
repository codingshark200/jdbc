import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

class DbSettings{
	public static final String DB_URL = "jdbc:mysql://localhost:3306/testdatenbank";
	public static final String USER_NAME = "root";
	public static final String PASSWORD = "";
	public static final String PORT = "3306";
}

class User{
	public int id;
	public String fname, lname;
	public int age;

	public User(int id, String fname, String lname, int age) {
		this.id = id;
		this.fname = fname;
		this.lname = lname;
		this.age = age;
	}
}

public class Main {

	public static void main(String[] args) {
		
		ArrayList<User> users = new ArrayList<>();
		Connection con = null;
		
		try {
			con = DriverManager.getConnection(DbSettings.DB_URL, DbSettings.USER_NAME, DbSettings.PASSWORD);
			String sql = "INSERT INTO user( fname, lname, age) VALUES (?, ?, ?)";
			
			PreparedStatement pstmt = con.prepareStatement(sql);
			pstmt.setString(1, "Peter");
			pstmt.setString(2, "Hans");
			pstmt.setInt(3, 45);
			
			pstmt.execute();
			pstmt = con.prepareStatement("SELECT * FROM user");
			
			ResultSet rs = pstmt.executeQuery();
			
			while(rs.next()) {
				users.add(new User(
						rs.getInt(1),
						rs.getString(2),
						rs.getString(3),
						rs.getInt(4)
				));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		for(var user : users) {
			System.out.println(user.id);
			System.out.println(user.fname);
			System.out.println(user.lname);
			System.out.println(user.age);
			System.out.println("-----------------------");
		}

	}

}
