package util;

import java.sql.*;
import java.io.*;

public class DBUtil {
	private static final long serialVersionUID = 1L;
	static String driver="org.sqlite.JDBC";
	static String conStr="jdbc:sqlite:db/SRSDB.db";
	public static Connection getSqliteConnection(){
		
		
		Connection conn=null;
		try{
			Class.forName(driver);
			conn = DriverManager.getConnection(conStr);
		}catch(Exception e){
			e.printStackTrace();
		}
		return conn;			
	}

	public static Connection open() {
		try {	
			Class.forName(driver);
		try {
			return DriverManager.getConnection(conStr);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
	}
	return null;
}
	}
	

