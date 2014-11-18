package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class Database {

	private static Connection connection;
	private Statement statement;
	private ResultSet resultSet;
	private static String host;
	private static String user;
	private static String pass;
	private static Database database;
	private Database(){
		
	}
	public static synchronized Database create(){
		if(database == null){
				
				try {
					database = new Database();
					connect();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		return database;
	}
	private static void connect() throws ClassNotFoundException, SQLException {
		host = "jdbc:mysql://localhost:3306/cnpm";
		user = "root";
		pass = "123456";
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(host, user, pass);
		System.out.println("connecting...");
	}

	public ResultSet execute(String sql) throws SQLException {
		statement = connection.createStatement();
		resultSet = statement.executeQuery(sql);
		return resultSet;
	}

	public int update(String sql) throws SQLException {
		statement = connection.createStatement();
		int leg = statement.executeUpdate(sql);
		statement.close();
		return leg;
	}


}
