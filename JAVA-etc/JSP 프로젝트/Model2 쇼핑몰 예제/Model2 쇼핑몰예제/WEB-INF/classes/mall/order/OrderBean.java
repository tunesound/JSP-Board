
package mall.order;

import java.sql.*;
import java.util.*;
import mall.util.*;

/**
 * 주문 자바빈 
 */	
public class OrderBean {
  	private int orderId = 0;				//주문번호
  	private int userId = 0;  				//사용자번호
  	private double orderTotal = 0;  		//총금액
	private int status = 1;					//상태
	private java.sql.Date orderDate;		//주문일자
	private String userName = "";			//사용자명
	
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
	/** 1:주문미처리, 2:주문처리 */   
   	public static final int NOT_COMPLETED = 1, COMPLETED = 2;     

    /** 생성자 */
	public OrderBean() {}		
  		
	/** 주문번호로 조회
	 * @param 주문번호 int
	 * @return 조회결과
	 */
	public void init(int order_id) throws Exception {	    
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;		
	    try {     				
			//데이터베이스 연결
			con = DBManager.getConnection();  	

			//SQL문 작성
			qry = new StringBuffer(1024);		    
			qry.append("SELECT * FROM orders o, users u ");
			qry.append("WHERE o.user_id = u.user_id and o.order_id = ? ");        	    	
		
	    	ps = con.prepareStatement(qry.toString());
			ps.setInt(1, order_id);
	      	//SQL문 실행
			rs = ps.executeQuery();	  
			
	      	//SQL문 실행결과를 OrderBean 객체에 저장
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
	
	/** 신규 주문내역 추가
	 * @return 신규추가결과
	 */
    public void add() throws Exception {
    	Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;
		try {		   			   	
		   	//데이터베이스 연결
			con = DBManager.getConnection();

			//SQL문 작성
			qry = new StringBuffer(1024);			          
	      	qry.append("INSERT INTO orders (order_id, user_id, order_total, status, order_date) ");			
			qry.append("VALUES(order_id_seq.nextval, ?,?,?,?)");
			
			ps = con.prepareStatement(qry.toString());	
	      	setPS(ps, "add");       
	      	
			//SQL문 실행
	      	ps.executeUpdate();
	      	ps.close();
				
	      	//주문번호 얻기를 위한 SQL문 작성
			ps = con.prepareStatement("select max(order_id) orderId from orders ");            
            rs = ps.executeQuery();				
			
			//주문번호 얻기
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
	
    /** 해당 주문번호의 주문내역 수정
	 * @return 수정결과
	 */
    public void modify() throws Exception {	
	    Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;		
		try {
			//데이터베이스 연결
			con = DBManager.getConnection();		

			//SQL문 작성
			qry = new StringBuffer(1024);
	      	qry.append("UPDATE orders SET user_id = ?, order_total = ?, status = ?, order_date = ? ");
            qry.append("WHERE order_id = ?");
	
	      	ps = con.prepareStatement(qry.toString());			
	      	setPS(ps, "modify");		

			//SQL문 실행
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
	
	/** 해당 주문번호의 주문내역 삭제
	 * @return 삭제결과
	 */
	public void remove() throws Exception {	 
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;			
		try {
	      	//데이터베이스 연결
			con = DBManager.getConnection();	

			//SQL문 작성
			qry = new StringBuffer(1024);
	      	qry.append("DELETE FROM orders WHERE order_id = ?");	
	      	ps = con.prepareStatement(qry.toString());			
	      	ps.setInt(1, orderId);      
						
	      	//SQL문 실행
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
	
	/** 모든 주문내역 검색
	 * @return OrderBean 배열
	 */
	public OrderBean[] findAll() throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;
		OrderBean[] orders = null;
		try {					
			//데이터베이스 연결
			con = DBManager.getConnection();		

			//SQL문 작성
			qry = new StringBuffer(1024);		
			qry.append("select * from orders o, users u where o.user_id = u.user_id");
			
            ps = con.prepareStatement(qry.toString());      
			
			//SQL문 실행
			rs = ps.executeQuery();

			//SQL문 실행결과를 OrderBean 객체 배열에 저장
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
    
	/** 상태별 주문내역 검색
	 * @param 상태 int
	 * @return OrderBean 배열
	 */
    public OrderBean[] findByStatus(int status) throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;
		OrderBean[] orders = null;
		try {				
			//데이터베이스 연결
			con = DBManager.getConnection();		

			//SQL문 작성
			qry = new StringBuffer(1024);		
			qry.append("select * from orders o, users u ");
			qry.append("where o.user_id = u.user_id and o.status = ?");

            ps = con.prepareStatement(qry.toString());    
            ps.setInt(1, status);

			//SQL문 실행
			rs = ps.executeQuery();

			//SQL문 실행결과를 OrderBean 객체 배열에 저장
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
    
	/** 사용자번호별, 상태별 주문내역 검색
	 * @param 상태 int
	 * @param 사용자Id int
	 * @return OrderBean 배열
	 */
    public OrderBean[] findByStatus(int status, int user_id) throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;
		OrderBean[] orders = null;
		try {				
			//데이터베이스 연결
			con = DBManager.getConnection();		

			//SQL문 작성
			qry = new StringBuffer(1024);		
			qry.append("select * from orders o, users u ");
			qry.append("where o.user_id = u.user_id and o.status = ? and o.user_id = ?");
            ps = con.prepareStatement(qry.toString());    
            ps.setInt(1, status);
            ps.setInt(2, user_id);

			//SQL문 실행
			rs = ps.executeQuery();

			//SQL문 실행결과를 OrderBean 객체 배열에 저장
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
		
	/** 사용자번호별 주문내역 검색
	 * @param 사용자Id int
	 * @return OrderBean 배열
	 */
	public OrderBean[] findByUserId(int user_id) throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;
		OrderBean[] orders = null;
		try {				
			//데이터베이스 연결
			con = DBManager.getConnection();		

			//SQL문 작성
			qry = new StringBuffer(1024);		
			qry.append("select * from orders o, users u where o.user_id = u.user_id and o.user_id = ?");
			
            ps = con.prepareStatement(qry.toString());           	
            ps.setInt(1, user_id);

			//SQL문 실행
			rs = ps.executeQuery();

			//SQL문 실행결과를 OrderBean 객체 배열에 저장
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
    
	/** ResultSet 값을 OrderBean 배열에 저장
	 * @param ResultSet
	 * @return OrderBean 배열
	 */
	private OrderBean[] setValuesForFind(ResultSet rs) throws Exception	{	    
		OrderBean[] orders; 
		OrderBean order;	
		LinkedList lkl = null;

		//ResultSet 값을 LinkedList에 저장
		lkl = new LinkedList();			        		
        for(; rs.next(); lkl.add(order)) {                
	        order = new OrderBean();			
			order.setValues(rs);		
        }           
		//LinkedList를 OrderBean 배열로 변환
        orders = new OrderBean[lkl.size()];
        orders = (OrderBean[])lkl.toArray(orders);			
		return orders;
	}
	
	/** ResultSet 객체에 값을 멤버변수에 저장
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
	
	/** 멤버변수 값을 PreparedStatement 객체에 설정
	 * @param PreparedStatement
	 * @param 타입(insert/modify)
	 */
	private void setPS(PreparedStatement ps, String type) throws Exception {
		ps.clearParameters();	   	
	   	ps.setInt(1, userId);
	   	ps.setDouble(2, orderTotal);	   	
	   	ps.setInt(3, status);	   	
	   	ps.setDate(4, new java.sql.Date(System.currentTimeMillis()));
		//주문일자를 금일로 설정
		setOrderDate(new java.sql.Date(System.currentTimeMillis()));
	   	if (type.equals("modify")) {
			ps.setInt(5, orderId);             
		}
    }	
}