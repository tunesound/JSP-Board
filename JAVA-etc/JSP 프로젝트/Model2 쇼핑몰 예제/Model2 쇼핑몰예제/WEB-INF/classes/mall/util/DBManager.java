package mall.util;

import java.util.*;
import java.sql.*;

/**
 * DBMS ���� Ŭ����
 *
 * @since 2002/2/9
 * @version 1.0
 */	
public class DBManager{
	
	/** DBMS ���� - �����ͺ��̽��� �������� ���� ���
	 * @return Connection ��ü
	 */
	public static Connection getConnection() throws Exception{		
		return getConnection("mall");
	}
	
	/** DBMS ���� - �����ͺ��̽��� ������ ���
	 * @param �����ͺ��̽���
	 * @return Connection ��ü
	 */
	public static Connection getConnection(String db) throws Exception{		
		Connection con = null;
		ResourceBundle rb = null;		
		String driver = null;
		String url = null;
		String user = null;
		String password = null;
		try {
			//Ŭ�����н��� db.properties ������ �д�.
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
