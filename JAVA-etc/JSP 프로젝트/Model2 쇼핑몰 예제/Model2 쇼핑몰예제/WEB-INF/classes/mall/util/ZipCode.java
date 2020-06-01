package mall.util;
import java.io.*;
import java.sql.*;
import java.util.*;
/**
 * �����ȣ Ŭ���� 
 */
public class ZipCode {
	private String postalCode;  //�����ȣ
	private String region1;     //�ּ�1
	private String region2;     //�ּ�2
	
	 /** ������ */
	public ZipCode() {}	

	public String getPostalCode() { return this.postalCode; }
	public void setPostalCode(String postalCode){ this.postalCode = postalCode; }
	
	public String getRegion1() { return this.region1; }
	public void setRegion1(String region1){ this.region1 = region1; }
	
	public String getRegion2() { return this.region2; }
	public void setRegion2(String region2){ this.region2 = region2; }
	
	/** ���̸����� �ּ� �˻� ��) %���%
	 * @param ���̸� String
	 * @return ZipCode�� �迭
	 */	
	public ZipCode[] findBy(String region) throws Exception {                				
		Connection con = null;	
		PreparedStatement ps = null;	
		ResultSet rs = null;		
		StringBuffer qry = null;		
		LinkedList lkl = null;
		ZipCode zipcode = null;		
		ZipCode[] zipcodes = null;
		try {				
			//�����ͺ��̽� ����
			con = DBManager.getConnection();					
			
			//SQL�� �ۼ�
			qry = new StringBuffer(1024);		    
			qry.append("select * from zipcode where region1 like ? or region2 like ? ");	

            ps = con.prepareStatement(qry.toString());            
			ps.setString(1, Han.Uni2Ksc(region));
			ps.setString(2, Han.Uni2Ksc(region));
			
            //SQL�� ���� �� ����� LinkedList�� ����
			lkl = new LinkedList();	          			
            for(rs = ps.executeQuery(); rs.next(); lkl.add(zipcode)) {                
                zipcode = new ZipCode();
				zipcode.setPostalCode(rs.getString("postal_code"));
				zipcode.setRegion1(rs.getString("region1"));	
				zipcode.setRegion2(rs.getString("region2"));						
            }           
            //LinkedList���� ZipCode �迭�� ��ȯ
			zipcodes = new ZipCode[lkl.size()];
            zipcodes = (ZipCode[])lkl.toArray(zipcodes);			
            
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
		return zipcodes;
    }
	
	/** �����ȣ���� �ּ� �˻� ��) %123123%
	 * @param �����ȣ  String
	 * @return ZipCode�� �迭
	 */	
	public ZipCode[] findByPostalCode(String postal_code) throws Exception {                				
		Connection con = null;	
		PreparedStatement ps = null;
		ResultSet rs = null;	
		StringBuffer qry = null;
		LinkedList lkl = null;
		ZipCode zipcode = null;		
		ZipCode[] zipcodes = null;
		try {				
			//�����ͺ��̽� ����
			con = DBManager.getConnection();	
			
			//SQL�� �ۼ�
			qry = new StringBuffer(1024);		    
			qry.append("select * from postal_code where address1 like ? ");

            ps = con.prepareStatement(qry.toString());           
			ps.setString(1, postal_code);			
			
            //SQL�� ���� �� ����� LinkedList�� ����
			lkl = new LinkedList();		            
			for(rs = ps.executeQuery(); rs.next(); lkl.add(zipcode)) {                
                zipcode = new ZipCode();
				zipcode.setPostalCode(rs.getString("postal_code"));
				zipcode.setRegion1(rs.getString("region1"));	
				zipcode.setRegion2(rs.getString("region2"));						
            }           
            //LinkedList ���� ZipCode �迭�� ��ȯ
			zipcodes = new ZipCode[lkl.size()];
            zipcodes = (ZipCode[])lkl.toArray(zipcodes);			
            
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
		return zipcodes;
    }
}
