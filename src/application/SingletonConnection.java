package application;
	
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

	public class SingletonConnection {
		
		private static Connection connection;
		
		private SingletonConnection()
		{
			try {
				Class.forName("com.mysql.jdbc.Driver");
				connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/gestioncommandes", "root", "");
			} catch (ClassNotFoundException e) {
				
				e.printStackTrace();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}
			
		}
		public static Connection getConnection() {
			
			if(connection==null)
				new SingletonConnection();
			return connection;
			
		}

	}
