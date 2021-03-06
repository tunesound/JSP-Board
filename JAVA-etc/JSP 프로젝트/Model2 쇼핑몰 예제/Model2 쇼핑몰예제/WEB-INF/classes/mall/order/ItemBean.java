package mall.order;
import java.sql.*;
import java.util.*;
import mall.util.*;
/**
 * 주문의 상품들에 대한 자바빈 
 */	
public class ItemBean {
  	private int itemId = 0;			//아이템번호
  	private int productId = 0;  	//상품번호
  	private int orderId = 0;  		//주문번호
	private int qty = 0;			//수량
	
	public int getItemId(){ return this.itemId;}
	public void setItemId(int itemId) { this.itemId = itemId; }
	public int getProductId(){ return this.productId;}
	public void setProductId(int productId) { this.productId = productId; }	
	public int getOrderId(){ return this.orderId;}
	public void setOrderId(int orderId) { this.orderId = orderId; }	
	public int getQty(){ return this.qty;}
	public void setQty(int qty) { this.qty = qty; }
	
    /** 생성자 */
	public ItemBean() {}		
  	
	/** Item번호로 조회
	 * @param Item번호 int
	 * @return 조회결과 여부
	 */
	public void init(int item_id) throws Exception {
	    Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;		 	
	    try {     				
			//데이터베이스 연결
			con = DBManager.getConnection(); 

			//SQL문 작성
			qry = new StringBuffer(1024);		    
			qry.append("SELECT * FROM items WHERE item_id = ? ");        	    	
		
	    	ps  = con.prepareStatement(qry.toString());
			ps.setInt(1, item_id);

			//SQL문 실행
	      	rs = ps.executeQuery();	   
			
			//SQL문 실행결과를 ItemBean 객체에 저장
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
  
    /** 신규 Item내역 추가
	 * @return 신규추가결과 여부
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
	      	qry.append("INSERT INTO items (item_id, product_id, order_id, qty) ");			
			qry.append("VALUES(item_id_seq.nextval, ?,?,?)");
			
			ps = con.prepareStatement(qry.toString());	
	      	setPS(ps, "add");       
	      	
			//SQL문 실행
	      	ps.executeUpdate();
	      	ps.close();				
	      
			//아이템번호를 얻기위한 SQL문 작성
			ps = con.prepareStatement("select max(item_id) itemId from items ");            
            rs = ps.executeQuery();				
			
			//아이템번호 얻기
			while (rs.next()) {				
				itemId = rs.getInt("itemId");	
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
	
    /** 해당 Item번호의 Item내역 수정
	 * @return 수정결과 여부
	 */
    public void modify() throws Exception {	    
		Connection con = null;
  		PreparedStatement ps = null;
  		StringBuffer qry = null;	
		try {	      	
			//데이터베이스 연결
			con = DBManager.getConnection();	

			//SQL문 작성
			qry = new StringBuffer(1024);
	      	qry.append("UPDATE items SET product_id = ?, order_id = ?, qty = ? ");
            qry.append("WHERE item_id = ?");
	
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
			try { if (ps!=null) ps.close(); } catch(SQLException sqle) {}
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
		}		
	} 
	
	/** 해당 Item번호의 Item내역 삭제
	 * @return 삭제결과 여부
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
	      	qry.append("DELETE FROM items WHERE item_id = ?");	
	      	ps = con.prepareStatement(qry.toString());			
	      	ps.setInt(1, itemId);      
						
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
		
	/** 모든 주문의 상품리스트 검색
	 * @return ItemBean 배열
	 */
	public ItemBean[] findAll() throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;
		ItemBean[] items = null;
		try {					
			//데이터베이스 연결
			con = DBManager.getConnection();		

			//SQL문 작성
			qry = new StringBuffer(1024);		
			qry.append("select * from items ");
			
            ps = con.prepareStatement(qry.toString());           	
			//SQL문 실행
			rs = ps.executeQuery();

			//SQL문 실행결과를 ItemBean 객체 배열에 저장
			items = setValuesForFind(rs);						
			ps.close();
			rs.close();			
			
        }
      	catch(Exception e) {      
			throw e;
		}
		finally {			
			try { if (rs!=null) rs.close(); } catch(SQLException sqle) {}
			try { if (ps!=null) ps.close(); } catch(SQLException sqle) {}
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
		}
		return items;
    }
	
	/** 주문번호별 상품리스트 검색
	 * @param 주문번호 int
	 * @return ItemBean 배열
	 */
	public ItemBean[] findByOrderId(int order_id) throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;
		ItemBean[] items = null;
		try {						
			//데이터베이스 연결
			con = DBManager.getConnection();		

			//SQL문 작성
			qry = new StringBuffer(1024);		
			qry.append("select * from items where order_id = ?");
			
            ps = con.prepareStatement(qry.toString());    
            ps.setInt(1, order_id);

			//SQL문 실행
			rs = ps.executeQuery();

			//SQL문 실행결과를 ItemBean 객체 배열에 저장
			items = setValuesForFind(rs);			
			
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
		return items;
    }
	
	/** ResultSet 값을 ItemBean 배열에 저장
	 * @param ResultSet
	 * @return ItemBean 배열
	 */
	private ItemBean[] setValuesForFind(ResultSet rs) throws Exception {
		ItemBean[] items = null; 
		ItemBean item = null;
		LinkedList lkl = null;			

		//ResultSet 값을 LinkedList에 저장
		lkl = new LinkedList();		        		
        for(; rs.next(); lkl.add(item)) {                
	        item = new ItemBean();			
			item.setValues(rs);		
        }  
		//LinkedList를 ItemBean 배열로 변환
        items = new ItemBean[lkl.size()];
        items = (ItemBean[])lkl.toArray(items);			
		return items;
	}
	
	/** ResultSet 객체에 값을 멤버변수에 저장
	 * @param ResultSet
	 */
	private void setValues(ResultSet rs) throws Exception {      	        
        itemId = rs.getInt("item_id");
        productId = rs.getInt("product_id");        
        orderId = rs.getInt("order_id");              
		qty = rs.getInt("qty");   				
    }
	
	/** 멤버변수 값을 PreparedStatement 객체에 설정
	 * @param PreparedStatement
	 * @param 타입(insert/modify)
	 */
	private void setPS(PreparedStatement ps, String type) throws Exception {
		ps.clearParameters();	   	
	   	ps.setInt(1, productId);
	   	ps.setInt(2, orderId);	   	
	   	ps.setInt(3, qty);
	   	if (type.equals("modify")) {
			ps.setInt(4, itemId);             
		}
    }
}