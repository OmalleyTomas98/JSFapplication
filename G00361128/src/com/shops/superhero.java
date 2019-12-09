package com.shops;



import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;

public class superhero {
	public static void main(String[] args) {
		MysqlDataSource mysqlDS = new MysqlDataSource();

		mysqlDS.setURL("jdbc:mysql://localhost:3306/shops");
		mysqlDS.setUser("root");
		mysqlDS.setPassword("");

		try {
			Connection conn = mysqlDS.getConnection();
			Statement myStmt = conn.createStatement();
			String query = "select * from store";
			ResultSet rs = myStmt.executeQuery(query);
			while( rs.next() ) {
				String thename = rs.getString("name");
				String theid = rs.getString("Id");
				String tehfounded = rs.getString("founded");

				
			
				System.out.println(thename+ theid + theid +tehfounded );
			}
			conn.close();
			myStmt.close();
			rs.close();
		} catch( SQLException se ) {
            System.out.println(se.getMessage());
        }
	}
}