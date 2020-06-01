
package mall.order;

import java.sql.*;
import java.util.*;
import mall.util.*;

/**
 * �ֹ� �ڹٺ� 
 */	
public class OrderBean {
  	private int orderId = 0;				//�ֹ���ȣ
  	private int userId = 0;  				//����ڹ�ȣ
  	private double orderTotal = 0;  		//�ѱݾ�
	private int status = 1;					//����
	private java.sql.Date orderDate;		//�ֹ�����
	private String userName = "";			//����ڸ�
	
	public int getOrderId(){ return this.orderId;}
	public void setOrderId(int orderId) { this.orderId = orderId; }
	public int getUserId(){ return this.userId;}
	public void setUserId(int userId) { this.userId = userId; }	
	public double getOrderTotal(){return this.orderTotal;}  
	public void setOrderTotal(double orderTotal){this.orderTotal = orderTotal;}	
	public int getStatus(){ return this.status;}
	public void setStatus(int status) { this.status = status; }
	public java.sql.Date getOrderDate(){ return this.orderDate;}
	public void setOrderDate(java.sql.Date orderDate) { this.orderDate = orderDate; }
	public String getUserName(){ return this.userName;}
	public void setUserName(String userName) { this.userName = userName; }
	/** 1:�ֹ���ó��, 2:�ֹ�ó�� */   
   	public static final int NOT_COMPLETED = 1, COMPLETED = 2;     

    /** ������ */
	public OrderBean() {}		
  		
	/** �ֹ���ȣ�� ��ȸ
	 * @param �ֹ���ȣ int
	 * @return ��ȸ���
	 */
	public void init(int order_id) throws Exception {	    
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;		
	    try {     				
			//�����ͺ��̽� ����
			con = DBManager.getConnection();  	

			//SQL�� �ۼ�
			qry = new StringBuffer(1024);		    
			qry.append("SELECT * FROM orders o, users u ");
			qry.append("WHERE o.user_id = u.user_id and o.order_id = ? ");        	    	
		
	    	ps = con.prepareStatement(qry.toString());
			ps.setInt(1, order_id);
	      	//SQL�� ����
			rs = ps.executeQuery();	  
			
	      	//SQL�� �������� OrderBean ��ü�� ����
			while (rs.next()) {
	        	setValues(rs);				    
	     	}
	     	rs.close();
	     	ps.close();	      	 
	    }
	    catch(Exception e) {	      	
			throw e;
	    }
		finally	{			
			try { if (rs!=null) rs.close(); } catch(SQLException sqle) {}
			try { if (ps!=null) ps.close(); } catch(SQLException sqle) {}
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
		}
    }  
	
	/** �ű� �ֹ����� �߰�
	 * @return �ű��߰����
	 */
    public void add() throws Exception {
    	Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;
		try {		   			   	
		   	//�����ͺ��̽� ����
			con = DBManager.getConnection();

			//SQL�� �ۼ�
			qry = new StringBuffer(1024);			          
	      	qry.append("INSERT INTO orders (order_id, user_id, order_total, status, order_date) ");			
			qry.append("VALUES(order_id_seq.nextval, ?,?,?,?)");
			
			ps = con.prepareStatement(qry.toString());	
	      	setPS(ps, "add");       
	      	
			//SQL�� ����
	      	ps.executeUpdate();
	      	ps.close();
				
	      	//�ֹ���ȣ ��⸦ ���� SQL�� �ۼ�
			ps = con.prepareStatement("select max(order_id) orderId from orders ");            
            rs = ps.executeQuery();				
			
			//�ֹ���ȣ ���
			while (rs.next()) {				
				orderId = rs.getInt("orderId");	
			}
			     	
	      	rs.close();
	      	ps.close();	      	
    	} 
		catch (Exception e) {      		  		
			throw e;
    	}
		finally	{			
			try { if (rs!=null) rs.close(); } catch(SQLException sqle) {}
			try { if (ps!=null) ps.close(); } catch(SQLException sqle) {}
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
		}
  	}
	
    /** �ش� �ֹ���ȣ�� �ֹ����� ����
	 * @return �������
	 */
    public void modify() throws Exception {	
	    Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;		
		try {
			//�����ͺ��̽� ����
			con = DBManager.getConnection();		

			//SQL�� �ۼ�
			qry = new StringBuffer(1024);
	      	qry.append("UPDATE orders SET user_id = ?, order_total = ?, status = ?, order_date = ? ");
            qry.append("WHERE order_id = ?");
	
	      	ps = con.prepareStatement(qry.toString());			
	      	setPS(ps, "modify");		

			//SQL�� ����
	      	ps.executeUpdate();	
	      	ps.close();		  	   
	    } 
		catch (Exception e) {	      	
			throw e;
	    }
		finally	{			
			try { if (rs!=null) rs.close(); } catch(SQLException sqle) {}
			try { if (ps!=null) ps.close(); } catch(SQLException sqle) {}
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
		}			
	} 
	
	/** �ش� �ֹ���ȣ�� �ֹ����� ����
	 * @return �������
	 */
	public void remove() throws Exception {	 
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;			
		try {
	      	//�����ͺ��̽� ����
			con = DBManager.getConnection();	

			//SQL�� �ۼ�
			qry = new StringBuffer(1024);
	      	qry.append("DELETE FROM orders WHERE order_id = ?");	
	      	ps = con.prepareStatement(qry.toString());			
	      	ps.setInt(1, orderId);      
						
	      	//SQL�� ����
			ps.executeUpdate();	
	      	ps.close();		  	   
	    } 
		catch (Exception e) {	      	
			throw e;
	    }
		finally	{			
			try { if (ps!=null) ps.close(); } catch(SQLException sqle) {}
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
		}			
	} 	
	
	/** ��� �ֹ����� �˻�
	 * @return OrderBean �迭
	 */
	public OrderBean[] findAll() throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;
		OrderBean[] orders = null;
		try {					
			//�����ͺ��̽� ����
			con = DBManager.getConnection();		

			//SQL�� �ۼ�
			qry = new StringBuffer(1024);		
			qry.append("select * from orders o, users u where o.user_id = u.user_id");
			
            ps = con.prepareStatement(qry.toString());      
			
			//SQL�� ����
			rs = ps.executeQuery();

			//SQL�� �������� OrderBean ��ü �迭�� ����
			orders = setValuesForFind(rs);			
			
			ps.close();
			rs.close();			
			
        }
      	catch(Exception e) {      
			throw e;
		}
		finally	{			
			try { if (rs!=null) rs.close(); } catch(SQLException sqle) {}
			try { if (ps!=null) ps.close(); } catch(SQLException sqle) {}
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
		}
		return orders;
    }
    
	/** ���º� �ֹ����� �˻�
	 * @param ���� int
	 * @return OrderBean �迭
	 */
    public OrderBean[] findByStatus(int status) throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;
		OrderBean[] orders = null;
		try {				
			//�����ͺ��̽� ����
			con = DBManager.getConnection();		

			//SQL�� �ۼ�
			qry = new StringBuffer(1024);		
			qry.append("select * from orders o, users u ");
			qry.append("where o.user_id = u.user_id and o.status = ?");

            ps = con.prepareStatement(qry.toString());    
            ps.setInt(1, status);

			//SQL�� ����
			rs = ps.executeQuery();

			//SQL�� �������� OrderBean ��ü �迭�� ����
			orders = setValuesForFind(rs);			
			
			ps.close();
			rs.close();			
			
        }
      	catch(Exception e) {      
			throw e;
		}
		finally	{			
			try { if (rs!=null) rs.close(); } catch(SQLException sqle) {}
			try { if (ps!=null) ps.close(); } catch(SQLException sqle) {}
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
		}
		return orders;
    }
    
	/** ����ڹ�ȣ��, ���º� �ֹ����� �˻�
	 * @param ���� int
	 * @param �����Id int
	 * @return OrderBean �迭
	 */
    public OrderBean[] findByStatus(int status, int user_id) throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;
		OrderBean[] orders = null;
		try {				
			//�����ͺ��̽� ����
			con = DBManager.getConnection();		

			//SQL�� �ۼ�
			qry = new StringBuffer(1024);		
			qry.append("select * from orders o, users u ");
			qry.append("where o.user_id = u.user_id and o.status = ? and o.user_id = ?");
            ps = con.prepareStatement(qry.toString());    
            ps.setInt(1, status);
            ps.setInt(2, user_id);

			//SQL�� ����
			rs = ps.executeQuery();

			//SQL�� �������� OrderBean ��ü �迭�� ����
			orders = setValuesForFind(rs);			
			
			ps.close();
			rs.close();			
			
        }
      	catch(Exception e) {      
			throw e;
		}
		finally	{			
			try { if (rs!=null) rs.close(); } catch(SQLException sqle) {}
			try { if (ps!=null) ps.close(); } catch(SQLException sqle) {}
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
		}
		return orders;
    }
		
	/** ����ڹ�ȣ�� �ֹ����� �˻�
	 * @param �����Id int
	 * @return OrderBean �迭
	 */
	public OrderBean[] findByUserId(int user_id) throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;
		OrderBean[] orders = null;
		try {				
			//�����ͺ��̽� ����
			con = DBManager.getConnection();		

			//SQL�� �ۼ�
			qry = new StringBuffer(1024);		
			qry.append("select * from orders o, users u where o.user_id = u.user_id and o.user_id = ?");
			
            ps = con.prepareStatement(qry.toString());           	
            ps.setInt(1, user_id);

			//SQL�� ����
			rs = ps.executeQuery();

			//SQL�� �������� OrderBean ��ü �迭�� ����
			orders = setValuesForFind(rs);			
			
			ps.close();
			rs.close();						
        }
      	catch(Exception e) {      
			throw e;
		}
		finally	{			
			try { if (rs!=null) rs.close(); } catch(SQLException sqle) {}
			try { if (ps!=null) ps.close(); } catch(SQLException sqle) {}
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
		}
		return orders;
    }
    
	/** ResultSet ���� OrderBean �迭�� ����
	 * @param ResultSet
	 * @return OrderBean �迭
	 */
	private OrderBean[] setValuesForFind(ResultSet rs) throws Exception	{	    
		OrderBean[] orders; 
		OrderBean order;	
		LinkedList lkl = null;

		//ResultSet ���� LinkedList�� ����
		lkl = new LinkedList();			        		
        for(; rs.next(); lkl.add(order)) {                
	        order = new OrderBean();			
			order.setValues(rs);		
        }           
		//LinkedList�� OrderBean �迭�� ��ȯ
        orders = new OrderBean[lkl.size()];
        orders = (OrderBean[])lkl.toArray(orders);			
		return orders;
	}
	
	/** ResultSet ��ü�� ���� ��������� ����
	 * @param ResultSet
	 */
	private void setValues(ResultSet rs) throws Exception {     	        
        orderId = rs.getInt("order_id");
        userId = rs.getInt("user_id");        
        orderTotal = rs.getDouble("order_total");              
		status = rs.getInt("status");   		
		orderDate = rs.getDate("order_date");
		userName = rs.getString("name");		
    }
	
	/** ������� ���� PreparedStatement ��ü�� ����
	 * @param PreparedStatement
	 * @param Ÿ��(insert/modify)
	 */
	private void setPS(PreparedStatement ps, String type) throws Exception {
		ps.clearParameters();	   	
	   	ps.setInt(1, userId);
	   	ps.setDouble(2, orderTotal);	   	
	   	ps.setInt(3, status);	   	
	   	ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
		//�ֹ����ڸ� ���Ϸ� ����
		setOrderDate(new java.sql.Date(System.currentTimeMillis()));
	   	if (type.equals("modify")) {
			ps.setInt(5, orderId);             
		}
    }	
}