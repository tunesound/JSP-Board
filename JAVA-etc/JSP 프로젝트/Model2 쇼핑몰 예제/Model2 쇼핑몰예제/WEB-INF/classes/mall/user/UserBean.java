package mall.user;
import java.sql.*;
import java.util.*;
import mall.util.*;
/**
 * ����� ���� �ڹٺ� 
 */	
public class UserBean {
  	private int userId = 0;				//����ڹ�ȣ
  	private String name = "";			//����ڸ�
  	private String company = "";		//ȸ���
  	private String dept = "";			//�μ���
  	private String title = "";			//����
  	private String id = "";				//ID
  	private String password = "";		//��й�ȣ
  	private String email = "";			//���ڿ���
  	private String ssn = "";			//�ֹε�Ϲ�ȣ
	private int status = 2;				//����
	private int isAdmin = 0;	//�����ڿ���(1:������, 0:��)
	private String zipcode = "";		//�����ȣ
	private String address1 = "";		//�ּ�1
	private String address2 = "";		//�ּ�2
	private String companyPhone = "";	//ȸ����ȭ��ȣ
	private String homePhone = "";		//����ȭ��ȣ
	
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
    
	/** 0:�α��μ���, 1:�α���ID ���� */
	public static final int LOGIN_SUCCESS = 0, LOGIN_IDNOTEXIST = 1;	
	/** 2:��к�ȣ Ʋ��, 3:�����ڿ� ���� Ȯ�� �ȵ� */
	public static final int LOGIN_WRONGPASSWORD = 2, LOGIN_INACTIVE = 3;  
	/** 1:��밡�� 2:������� */
	public static final int ACTIVE = 1, INACTIVE = 2;     

    /** ������ */
	public UserBean() {}		
    
  	/** ����ڹ�ȣ�� ��ȸ
	 * @param ����ڹ�ȣ int
	 * @return ��ȸ���
	 */
	public void init(int user_id) throws Exception {
	    Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;			
	    try {     				
			//�����ͺ��̽� ����
			con = DBManager.getConnection();  

			//SQL�� �ۼ�
			qry = new StringBuffer(1024);		    
			qry.append("SELECT * FROM users WHERE user_id = ? ");        	    	
		
	    	ps  = con.prepareStatement(qry.toString());
			ps.setInt(1, user_id);

			//SQL�� ����
	      	rs = ps.executeQuery();	     
			
			//SQL�� �������� UserBean ��ü�� ����
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
  
    /** �ű� ����ڳ��� �߰�
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
	      	qry.append("INSERT INTO users (user_id, name, company, dept, title, id, password, ");
			qry.append("email, ssn, status, is_admin, zipcode, address1, ");		
			qry.append("address2, company_phone, home_phone) ");
			qry.append("VALUES(user_id_seq.nextval, ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
			ps = con.prepareStatement(qry.toString());	
	      	setPS(ps, "add");   
			
	      	//SQL�� ����
	      	ps.executeUpdate();
	      	ps.close();
				
	      	//����ڹ�ȣ�� ������� SQL�� �ۼ�
			ps = con.prepareStatement("select max(user_id) userId from users ");            
            rs = ps.executeQuery();				
			
			//����ڹ�ȣ ���
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
	
    /** �ش� ����ڹ�ȣ�� ����ڳ��� ����
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
	      	qry.append("UPDATE users SET name = ?, company = ?, dept = ?, title = ?, id = ?, ");
            qry.append("password = ?, email = ?, ssn = ?, status = ?, is_admin = ?, ");    
            qry.append("zipcode = ?, address1 = ?, address2 = ?, company_phone = ?, home_phone = ? ");   
            qry.append("WHERE user_id = ?");
	
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
	
	/** �ش� ����ڹ�ȣ�� ����ڳ��� ����
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
	      	qry.append("DELETE FROM users WHERE user_id = ?");	
	      	ps = con.prepareStatement(qry.toString());			
	      	ps.setInt(1, userId);      
						
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
	
	/** ����� �α���
	 * @param �����Id	String
	 * @param ��й�ȣ	String
	 * @return 0(����), 1(Id������), 2(��й�ȣ�� Ʋ��)
	 */	
	public int login(String id, String password) throws Exception {  
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;        
		int ret = 4;
        try {
            //�����ͺ��̽� ����
			con = DBManager.getConnection();
			
			//SQL�� �ۼ�
			qry = new StringBuffer(1024);
	      	qry.append("SELECT * FROM users WHERE (id = ? or email = ?) ");	
            ps = con.prepareStatement(qry.toString());
            ps.setString(1, id);
            ps.setString(2, id);

			//SQL�� ����
            rs = ps.executeQuery();
            //����ڹ�ȣ�� ���°��
			if(!rs.next()) {
                ret = UserBean.LOGIN_IDNOTEXIST;                
            }
			else {
	            //���������� �α���
				if(password.equals(rs.getString("password"))) {	               
					if (rs.getInt("status") == ACTIVE) {
						init(rs.getInt("user_id"));
						ret = UserBean.LOGIN_SUCCESS; 
					}
					else {
						ret = UserBean.LOGIN_INACTIVE;
					}
					                             
	            }
				else {	//��й�ȣ�� Ʋ�����
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
	
	/** �����Id�� �̹̻�������� üũ�Ѵ�.
	 * @param �����Id String
	 * @return ��ȸ���
	 */
	public boolean isExist(String id) throws Exception {
	    Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null;		
		boolean flag = false;
	    try {     				
			//�����ͺ��̽� ����
			con = DBManager.getConnection();  

			//SQL�� �ۼ�
			qry = new StringBuffer(1024);		    
			qry.append("SELECT * FROM users WHERE id = ? ");        	    	
		
	    	ps  = con.prepareStatement(qry.toString());
			ps.setString(1, id);

			//SQL�� ����
	      	rs = ps.executeQuery();	     
			
			//SQL�� �������� UserBean ��ü�� ����
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

	/** ��� ����� ����Ʈ �˻�	
	 * @return UserBean�� �迭
	 */	
	public UserBean[] findAll() throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null; 
		UserBean[] users = null;
		try {					
			//�����ͺ��̽� ����
			con = DBManager.getConnection();		

			//SQL�� �ۼ�
			qry = new StringBuffer(1024);		
			qry.append("select * from users ");
			
            ps = con.prepareStatement(qry.toString());    
			
			//SQL�� ����
			rs = ps.executeQuery();

			//SQL�� �������� UserBean ��ü�迭�� ����
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
		
	/** ���� ����ڸ���Ʈ �˻�
	 * @param ���� String
	 * @return UserBean �迭
	 */
	public UserBean[] findByName(String name) throws Exception {                				
		Connection con = null;
  		PreparedStatement ps = null;
  		ResultSet rs = null;
  		StringBuffer qry = null; 	
		UserBean[] users = null;
		try {		
			//�����ͺ��̽� ����
			con = DBManager.getConnection();	
			
			//SQL�� �ۼ�
			qry = new StringBuffer(1024);		
			qry.append("select * from users where name like ?");
			
            ps = con.prepareStatement(qry.toString());     
            ps.setString(1, name);

			//SQL�� ����
			rs = ps.executeQuery();

			//SQL�� �������� UserBean ��ü�迭�� ����
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
    
	/** ResultSet ���� UserBean �迭�� ����
	 * @param ResultSet
	 * @return UserBean �迭
	 */	
	private UserBean[] setValuesForFind(ResultSet rs) throws Exception {	    
		UserBean[] users = null;	
		UserBean user = null;
		LinkedList lkl = null;	        	
		
		//ResultSet ���� LinkedList�� ����
		lkl = new LinkedList();		
        for(; rs.next(); lkl.add(user)) {                
	        user = new UserBean();			
			user.setValues(rs);		
        }           
		//LinkedList�� UserBean �迭�� ��ȯ
        users = new UserBean[lkl.size()];
        users = (UserBean[])lkl.toArray(users);			
		return users;
	}
	
	/** ResultSet ��ü�� ���� ��������� ����
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
	
	/** ������� ���� PreparedStatement ��ü�� ����
	 * @param PreparedStatement
	 * @param Ÿ��(insert/modify)
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