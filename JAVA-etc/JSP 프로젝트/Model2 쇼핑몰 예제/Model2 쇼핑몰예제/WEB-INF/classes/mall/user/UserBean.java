package mall.user;
import java.sql.*;
import java.util.*;
import mall.util.*;
/**
 * 사용자 관련 자바빈 
 */	
public class UserBean {
  	private int userId = 0;				//사용자번호
  	private String name = "";			//사용자명
  	private String company = "";		//회사명
  	private String dept = "";			//부서명
  	private String title = "";			//직급
  	private String id = "";				//ID
  	private String password = "";		//비밀번호
  	private String email = "";			//전자우편
  	private String ssn = "";			//주민등록번호
	private int status = 2;				//상태
	private int isAdmin = 0;	//관리자여부(1:관리자, 0:고객)
	private String zipcode = "";		//우편번호
	private String address1 = "";		//주소1
	private String address2 = "";		//주소2
	private String companyPhone = "";	//회사전화번호
	private String homePhone = "";		//집전화번호
	
	public int getUserId(){ return this.userId;}
	public void setUserId(int userId) { this.userId = userId; }
	public String getName(){return this.name;}  
	public void setName(String name){this.name = name;}
	public String getCompany(){return this.company;}  
	public void setCompany(String company){this.company = company;}
	public String getDept(){return this.dept;}  
	public void setDept(String dept){this.dept = dept;}
	public String getTitle(){return this.title;}  
	public void setTitle(String title){this.title = title;}
	public String getId(){return this.id;}  
	public void setId(String id){this.id = id;}
	public String getPassword(){return this.password;}  
	public void setPassword(String password){this.password = password;}
	public String getEmail(){return this.email;}  
	public void setEmail(String email){this.email = email;}
	public String getSsn(){return this.ssn;}  
	public void setSsn(String ssn){this.ssn = ssn;}
	public int getStatus(){ return this.status;}
	public void setStatus(int status) { this.status = status; }
	public int getIsAdmin(){ return this.isAdmin;}
	public void setIsAdmin(int isAdmin) { this.isAdmin = isAdmin; }
	public String getZipcode(){return this.zipcode;}  
	public void setZipcode(String zipcode){this.zipcode = zipcode;}
	public String getAddress1(){return this.address1;}  
	public void setAddress1(String address1){this.address1 = address1;}
	public String getAddress2(){return this.address2;}  
	public void setAddress2(String address2){this.address2 = address2;}
	public String getCompanyPhone(){return this.companyPhone;}  
	public void setCompanyPhone(String companyPhone){this.companyPhone = companyPhone;}
	public String getHomePhone(){return this.homePhone;}  
	public void setHomePhone(String homePhone){this.homePhone = homePhone;}	
    
	/** 0:로그인성공, 1:로그인ID 없음 */
	public static final int LOGIN_SUCCESS = 0, LOGIN_IDNOTEXIST = 1;	
	/** 2:비밀빈호 틀림, 3:관리자에 의해 확인 안됨 */
	public static final int LOGIN_WRONGPASSWORD = 2, LOGIN_INACTIVE = 3;  
	/** 1:사용가능 2:사용정지 */
	public static final int ACTIVE = 1, INACTIVE = 2;     

    /** 생성자 */
	public UserBean() {}		
    
  	/** 사용자번호로 조회
	 * @param 사용자번호 int
	 * @return 조회결과
	 */
	public void init(int user_id) throws Exception {
	    Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;			
	    try {     				
			//데이터베이스 연결
			con = DBManager.getConnection();  

			//SQL문 작성
			qry = new StringBuffer(1024);		    
			qry.append("SELECT * FROM users WHERE user_id = ? ");        	    	
		
	    	ps  = con.prepareStatement(qry.toString());
			ps.setInt(1, user_id);

			//SQL문 실행
	      	rs = ps.executeQuery();	     
			
			//SQL문 실행결과를 UserBean 객체에 저장
	      	while (rs.next()) {
	        	setValues(rs);				    
	     	}
	     	rs.close();
	     	ps.close();	      	 
	    }
	    catch(Exception e) {	      	
			throw e; 	
	    }
		finally {			
			try { if (rs!=null) rs.close(); } catch(SQLException sqle) {}
			try { if (ps!=null) ps.close(); } catch(SQLException sqle) {}
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
		}
    }
  
    /** 신규 사용자내역 추가
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
	      	qry.append("INSERT INTO users (user_id, name, company, dept, title, id, password, ");
			qry.append("email, ssn, status, is_admin, zipcode, address1, ");		
			qry.append("address2, company_phone, home_phone) ");
			qry.append("VALUES(user_id_seq.nextval, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps = con.prepareStatement(qry.toString());	
	      	setPS(ps, "add");   
			
	      	//SQL문 실행
	      	ps.executeUpdate();
	      	ps.close();
				
	      	//사용자번호를 얻기위한 SQL문 작성
			ps = con.prepareStatement("select max(user_id) userId from users ");            
            rs = ps.executeQuery();				
			
			//사용자번호 얻기
			while (rs.next()) {				
				userId = rs.getInt("userId");	
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
	
    /** 해당 사용자번호의 사용자내역 수정
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
	      	qry.append("UPDATE users SET name = ?, company = ?, dept = ?, title = ?, id = ?, ");
            qry.append("password = ?, email = ?, ssn = ?, status = ?, is_admin = ?, ");    
            qry.append("zipcode = ?, address1 = ?, address2 = ?, company_phone = ?, home_phone = ? ");   
            qry.append("WHERE user_id = ?");
	
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
	
	/** 해당 사용자번호의 사용자내역 삭제
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
	      	qry.append("DELETE FROM users WHERE user_id = ?");	
	      	ps = con.prepareStatement(qry.toString());			
	      	ps.setInt(1, userId);      
						
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
	
	/** 사용자 로그인
	 * @param 사용자Id	String
	 * @param 비밀번호	String
	 * @return 0(성공), 1(Id가없음), 2(비밀번호가 틀림)
	 */	
	public int login(String id, String password) throws Exception {  
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;        
		int ret = 4;
        try {
            //데이터베이스 연결
			con = DBManager.getConnection();
			
			//SQL문 작성
			qry = new StringBuffer(1024);
	      	qry.append("SELECT * FROM users WHERE (id = ? or email = ?) ");	
            ps = con.prepareStatement(qry.toString());
            ps.setString(1, id);
            ps.setString(2, id);

			//SQL문 실행
            rs = ps.executeQuery();
            //사용자번호가 없는경우
			if(!rs.next()) {
                ret = UserBean.LOGIN_IDNOTEXIST;                
            }
			else {
	            //성공적으로 로그인
				if(password.equals(rs.getString("password"))) {	               
					if (rs.getInt("status") == ACTIVE) {
						init(rs.getInt("user_id"));
						ret = UserBean.LOGIN_SUCCESS; 
					}
					else {
						ret = UserBean.LOGIN_INACTIVE;
					}
					                             
	            }
				else {	//비밀번호가 틀린경우
					ret = UserBean.LOGIN_WRONGPASSWORD;
				}
			}           
		}
        catch(Exception e) {      
			throw e;
		}
		finally	{			
			try { if (rs!=null) rs.close(); } catch(SQLException sqle) {}
			try { if (ps!=null) ps.close(); } catch(SQLException sqle) {}
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
		}
		return ret;
    }	
	
	/** 사용자Id가 이미사용중인지 체크한다.
	 * @param 사용자Id String
	 * @return 조회결과
	 */
	public boolean isExist(String id) throws Exception {
	    Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;		
		boolean flag = false;
	    try {     				
			//데이터베이스 연결
			con = DBManager.getConnection();  

			//SQL문 작성
			qry = new StringBuffer(1024);		    
			qry.append("SELECT * FROM users WHERE id = ? ");        	    	
		
	    	ps  = con.prepareStatement(qry.toString());
			ps.setString(1, id);

			//SQL문 실행
	      	rs = ps.executeQuery();	     
			
			//SQL문 실행결과를 UserBean 객체에 저장
	      	if (rs.next()) {
	        	flag = true;				    
	     	}
	     	rs.close();
	     	ps.close();	      	 
	    }
	    catch(Exception e) {	      	
			throw e; 	
	    }
		finally {			
			try { if (rs!=null) rs.close(); } catch(SQLException sqle) {}
			try { if (ps!=null) ps.close(); } catch(SQLException sqle) {}
			try { if (con!=null) con.close(); } catch(SQLException sqle) {}
		}
		return flag;
    }

	/** 모든 사용자 리스트 검색	
	 * @return UserBean의 배열
	 */	
	public UserBean[] findAll() throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null; 
		UserBean[] users = null;
		try {					
			//데이터베이스 연결
			con = DBManager.getConnection();		

			//SQL문 작성
			qry = new StringBuffer(1024);		
			qry.append("select * from users ");
			
            ps = con.prepareStatement(qry.toString());    
			
			//SQL문 실행
			rs = ps.executeQuery();

			//SQL문 실행결과를 UserBean 객체배열에 저장
			users = setValuesForFind(rs);			
			
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
		return users;
    }
		
	/** 성명별 사용자리스트 검색
	 * @param 성명 String
	 * @return UserBean 배열
	 */
	public UserBean[] findByName(String name) throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null; 	
		UserBean[] users = null;
		try {		
			//데이터베이스 연결
			con = DBManager.getConnection();	
			
			//SQL문 작성
			qry = new StringBuffer(1024);		
			qry.append("select * from users where name like ?");
			
            ps = con.prepareStatement(qry.toString());     
            ps.setString(1, name);

			//SQL문 실행
			rs = ps.executeQuery();

			//SQL문 실행결과를 UserBean 객체배열에 저장
			users = setValuesForFind(rs);			
			
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
		return users;
    }
    
	/** ResultSet 값을 UserBean 배열에 저장
	 * @param ResultSet
	 * @return UserBean 배열
	 */	
	private UserBean[] setValuesForFind(ResultSet rs) throws Exception {	    
		UserBean[] users = null;	
		UserBean user = null;
		LinkedList lkl = null;	        	
		
		//ResultSet 값을 LinkedList에 저장
		lkl = new LinkedList();		
        for(; rs.next(); lkl.add(user)) {                
	        user = new UserBean();			
			user.setValues(rs);		
        }           
		//LinkedList를 UserBean 배열로 변환
        users = new UserBean[lkl.size()];
        users = (UserBean[])lkl.toArray(users);			
		return users;
	}
	
	/** ResultSet 객체에 값을 멤버변수에 저장
	 * @param ResultSet
	 */
	private void setValues(ResultSet rs) throws Exception {  
        userId = rs.getInt("user_id");
        name = rs.getString("name");
        company = rs.getString("company");
        dept =rs.getString("dept");
        title = rs.getString("title");
        id = rs.getString("id");
        password = rs.getString("password");
        email = rs.getString("email");      
        ssn = rs.getString("ssn"); 
		status = rs.getInt("status");   
		isAdmin = rs.getInt("is_admin"); 
		zipcode = rs.getString("zipcode");
		address1 = rs.getString("address1");
		address2 = rs.getString("address2");
		companyPhone = rs.getString("company_phone");
		homePhone = rs.getString("home_phone");		
    }
	
	/** 멤버변수 값을 PreparedStatement 객체에 설정
	 * @param PreparedStatement
	 * @param 타입(insert/modify)
	 */
	private void setPS(PreparedStatement ps, String type) throws Exception {
		ps.clearParameters();	   	
	   	ps.setString(1, Han.Uni2Ksc(name));
	   	ps.setString(2, Han.Uni2Ksc(company));
	   	ps.setString(3, Han.Uni2Ksc(dept));
	   	ps.setString(4, Han.Uni2Ksc(title));
	   	ps.setString(5, id);
	   	ps.setString(6, password);
	   	ps.setString(7, email);	 
	   	ps.setString(8, ssn);	   
	   	ps.setInt(9, status);
	   	ps.setInt(10, isAdmin);
	   	ps.setString(11, zipcode);
	   	ps.setString(12, Han.Uni2Ksc(address1));
	   	ps.setString(13, Han.Uni2Ksc(address2));
	   	ps.setString(14, companyPhone);
	   	ps.setString(15, homePhone);	   	
	   	if (type.equals("modify")) {
			ps.setInt(16, userId);             
		}
    }

}