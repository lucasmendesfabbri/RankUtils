package br.com.lucas.api.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.bukkit.Bukkit;

public class Database {

	public Connection connection;
	
	private String host, database, username, password;
	private int port = 3306;
	
    public Database() {
    	try {
    		
    		this.host = "localhost";this.database="api";this.username="root";this.password="";
			
    		if(this.connection!=null)this.connection.close();
			Class.forName("com.mysql.jdbc.Driver");
			String LINK = "jdbc:mysql://"+this.host+":"+this.port+"/"+this.database;
			this.connection = DriverManager.getConnection(LINK, "root", "");
			Bukkit.getLogger().info("[LOG] MYSQL ONLINE");
    		
    	}catch (Exception e) {
			e.printStackTrace();
		}
    }
    public void closeConnection() {
    	try {
			this.connection.close();
    		System.out.println("CONNECT CLOSE MYSQL");
		} catch (SQLException e) {
			e.printStackTrace();
		}
    }
	
	public String getHost() {
		return host;
	}

	public String getDatabase() {
		return database;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}
	
	
}
