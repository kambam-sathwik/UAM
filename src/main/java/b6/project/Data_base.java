package b6.project;

import java.sql.Connection;
import java.sql.DriverManager;

public class Data_base {
	public static Connection connect() throws Exception {
		String driver="com.mysql.cj.jdbc.Driver",url="jdbc:mysql://localhost:3306/uam_project",username="root",password="1023";
		Class.forName(driver);
		Connection c=DriverManager.getConnection(url, username, password);
		return c;
	}
}
