package mall.util;

import java.util.*;
import java.sql.*;

/**
 * DBMS 연결 클래스
 *
 * @since 2002/2/9
 * @version 1.0
 */	
public class DBManager{
	
	/** DBMS 연결 - 데이터베이스를 지정하지 않은 경우
	 * @return Connection 객체
	 */
	public static Connection getConnection() throws Exception{		
		return getConnection("mall");
	}
	
	/** DBMS 연결 - 데이터베이스를 지정한 경우
	 * @param 데이터베이스명
	 * @return Connection 객체
	 */
	public static Connection getConnection(String db) throws Exception{		
		Connection con = null;
		ResourceBundle rb = null;		
		String driver = null;
		String url = null;
		String user = null;
		String password = null;
		try {
			//클래스패스에 db.properties 파일을 둔다.
			rb = ResourceBundle.getBundle("db");
			driver = rb.getString("driver").trim();
			url = rb.getString("url").trim();
			user = rb.getString("user").trim();
			password = rb.getString("password").trim();

			Properties prop = new Properties();
			prop.put("user", user);
			prop.put("password", password);
			prop.put("databasename", db);

			Class.forName(driver).newInstance();
			con = DriverManager.getConnection(url, prop);
        }
		catch(Exception ex) {
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
			throw ex;
        }
		return con;
	}     
}
