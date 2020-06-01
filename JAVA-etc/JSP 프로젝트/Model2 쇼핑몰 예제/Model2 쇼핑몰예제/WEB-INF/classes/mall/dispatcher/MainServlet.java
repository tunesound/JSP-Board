package mall.dispatcher;
import java.util.*;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import mall.user.*;
import mall.order.*;
import mall.product.*;
import mall.util.*;
/**
 * ���� ���� 
 */
public class MainServlet extends HttpServlet {
    /** ���� ���� init �޼ҵ�
     * @param config        ServletConfig     	
     */
	public void init(ServletConfig conf) throws ServletException  {
        super.init(conf);
    }    

    /** ���� ���� service �޼ҵ�
     * @param req           HttpServletRequest   
     * @param res           HttpServletResponse 
     */
	public void service(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {        	
        HttpSession session = null;
		UserBean user = null;
		String url = "";
		String cmd = "";
		try {
            //���ǰ�ü ����
			session = req.getSession(false);
			if (session == null) {
                res.sendRedirect(req.getContextPath() + "/MainServlet?cmd=LOGIN");
				return;
            }                
            user = (UserBean)session.getAttribute("user");               

            //HTTP request command parameter�� ���� ����
			cmd = req.getParameter("cmd").toUpperCase();
			
            //ù������
			if (cmd.equals("HOME")) {
                setHomePage(req);
				url = "/main/home.jsp";
                forward(req, res, url);
            }
			//�α���
            else if (cmd.equals("LOGIN")) {
                int result = 0;
				String msg = "";
	            if (req.getParameter("username") !=null) {		        
		            if (user == null) {
                        user = new UserBean();
                    } 
		            result = user.login(req.getParameter("username"), req.getParameter("password"));	  if (result == UserBean.LOGIN_SUCCESS) {
			            setHomePage(req);
						session.setAttribute("user", user);

						//�������ΰ�� ùȭ���� �ֹ�����Ʈȭ��
						if (user.getIsAdmin() == 1) {
							res.sendRedirect(req.getContextPath() + "/OrderServlet?cmd=ORDER_LIST");
							return;
						}
						else {
							url = "/main/home.jsp";
						}			            
		            }	        	
		            else if (result == 1) {
		                msg = "����� ID�� �����ϴ�.";
						url = "/main/login.jsp";
		            }		        
					else if (result == 2) {
		                msg = "��й�ȣ�� Ʋ���ϴ�.";
						url = "/main/login.jsp";
					}
					else if (result == 3) {
		                msg = "�����ڿ� ���� Ȯ���� ���� �ʾҽ��ϴ�. �����ڿ��� ���ǹٶ��ϴ�.";
						url = "/main/login.jsp";
					}
	            }   	        
	            else {	            
	                url = "/main/login.jsp";
	            }	  
				req.setAttribute("msg", msg);
                forward(req, res, url);  
            }
			//�α׾ƿ�
            else if (cmd.equals("LOGOUT")) {
                session.invalidate();
				setHomePage(req);
                url = "/main/home.jsp";
                forward(req, res, url);                
            }
			//����ڵ��ȭ��
            else if (cmd.equals("ADD")) {            
                UserBean userbean = new UserBean();
                req.setAttribute("userbean", userbean);
                
                url = "/users/regist_user.jsp";
                forward(req, res, url);
                
            }
            else if (cmd.equals("MODIFY")) {            
                UserBean userbean = new UserBean();
                userbean.init(Integer.parseInt(req.getParameter("key")));
                req.setAttribute("userbean", userbean);
                
                url = "/users/regist_user.jsp";
                forward(req, res, url);
                
            }
            else if (cmd.equals("UPDATE")) {            
                UserBean userbean = new UserBean();
                
                userbean.setUserId(Integer.parseInt(req.getParameter("userId")));
	            userbean.setName(req.getParameter("name"));
	            userbean.setCompany(req.getParameter("company"));
	            userbean.setDept(req.getParameter("dept"));
	            userbean.setTitle(req.getParameter("title"));
	            userbean.setId(req.getParameter("id"));
	            userbean.setPassword(req.getParameter("password"));
	            userbean.setEmail(req.getParameter("email"));
	            userbean.setSsn(req.getParameter("ssn"));
	            userbean.setStatus(Integer.parseInt(req.getParameter("status")));	
	            int isAdmin = 0;
	            if (req.getParameter("isAdmin").equals("1")) isAdmin = 1;	
	            userbean.setIsAdmin(isAdmin);	
	            userbean.setZipcode(req.getParameter("zipcode"));
	            userbean.setAddress1(req.getParameter("address1"));
	            userbean.setAddress2(req.getParameter("address2"));
	            userbean.setCompanyPhone(req.getParameter("companyPhone"));
	            userbean.setHomePhone(req.getParameter("homePhone"));	
            	
				boolean is_dup = false;
	            //����� ���� �߰�
	            if(userbean.getUserId() == 0) {		
					if (userbean.isExist(userbean.getId()) == false) {
						userbean.add();
					}
					else {
						is_dup = true;
					}		            
	            }
	            //����� ���� ����
	            else {		
		            userbean.modify();
	            }
            	
        	    userbean.setName(Han.Ksc2Uni(userbean.getName()));
        	    userbean.setCompany(Han.Ksc2Uni(userbean.getCompany()));
        	    userbean.setDept(Han.Ksc2Uni(userbean.getDept()));
        	    userbean.setTitle(Han.Ksc2Uni(userbean.getTitle()));
        	    userbean.setAddress1(Han.Ksc2Uni(userbean.getAddress1()));
        	    userbean.setAddress2(Han.Ksc2Uni(userbean.getAddress2()));
            	
        	    req.setAttribute("userbean", userbean);
				if (is_dup == true) {
					req.setAttribute("msg", userbean.getId() + "���̵̹�ϵǾ��ֽ��ϴ�.");
				}
				else {
					req.setAttribute("msg", "ȸ����ϿϷ�Ǿ����ϴ�.");
				}			
            	
	            url =  "/users/regist_user.jsp";        	
                forward(req, res, url);            
            }
        }
        catch(Exception e) {
			e.printStackTrace();
            req.setAttribute("javax.servlet.jsp.jspException", e);
            forward(req, res, "/error.jsp");
        }
    }        
    
    private void setHomePage(HttpServletRequest req) throws Exception {
        ProductBean pb = new ProductBean();
		ListObject lo = new ListObject();
		ProductBean[] specials = pb.findForSpecial();
		for(int i=0; i<specials.length; i++) {
			pb = new ProductBean();
			pb.init(specials[i].getProductId());
			specials[i] = pb;
		}
		lo.add(specials);
		req.setAttribute("lo", lo);
    }

	private void forward(HttpServletRequest req, HttpServletResponse res, String url)
		throws ServletException, IOException {
      
		ServletContext sc = getServletContext();
        RequestDispatcher rd = sc.getRequestDispatcher(url);           
        rd.forward(req, res); 
    }

	private void logger(String msg) {
        System.out.println(msg);
    }
}

