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
 * 사용자 서블릿
 *
 * @since 2002/2/9
 * @version 1.0
 * @author 이창열 <a href="mailto:bready@j2eeschool.com">bready@j2eeschool.com</a> 
 */
public class UserServlet extends HttpServlet {
    /** 사용자 서블릿 init 메소드
     * @param config        ServletConfig     	
     */
	public void init(ServletConfig conf) throws ServletException  {
        super.init(conf);
    }    
    
    /** 사용자 서블릿 service 메소드
     * @param req           HttpServletRequest   
     * @param res           HttpServletResponse 
     */
	public void service(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {    
        HttpSession session = null;
		UserBean user = null;
		String cmd = "";
		String url = "";
		try {
            //세션객체 생성
			session = req.getSession(false);
            if (session == null) {
                res.sendRedirect(req.getContextPath() + "/MainServlet?cmd=LOGIN");
				return;
            }        
            
            user = (UserBean)session.getAttribute("user");
                   
            if (user == null) {
                user = new UserBean();
            }  
            
            if (user.getUserId() == 0) {
                res.sendRedirect(req.getContextPath() + "/MainServlet?cmd=LOGIN");
				return;
            }     
                          
            //HTTP request command parameter로 값을 얻음
			cmd = req.getParameter("cmd").toUpperCase();    
            
            //사용자 리스트 검색
			if (cmd.equals("USER_LIST")) {                         
				UserBean[] list = null;
				ListObject lo = null;

				lo = new ListObject();
				//사용자명으로 검색했을 경우
				if (req.getParameter("find") != null) {				
					list = user.findByName("%" + req.getParameter("find") + "%");
				}
				//모든 사용자를 검색했을 경우
				else {
					list = user.findAll();	
				}
				lo.add(list);
				req.setAttribute("lo", lo);	
				url = "/users/list_users.jsp";
				forward(req, res, url); 
            }
			//사용자 상세 화면
            else if (cmd.equals("USER_DETAIL")) {
                ProductBean pb = null;

				pb = new ProductBean();
                pb.init(Integer.parseInt(req.getParameter("key")));
                req.setAttribute("pb", pb);           
                
                url = "/users/form_user.jsp";
                forward(req, res, url);
            }
			//사용자 신규 추가화면
            else if (cmd.equals("ADD")) {            
                UserBean userbean = null;    
				
				userbean = new UserBean();   
                req.setAttribute("userbean", userbean);
                
                url = "/users/form_user.jsp";
                forward(req, res, url);            
            }
			//사용자 수정화면
            else if (cmd.equals("MODIFY")) {            
                UserBean userbean = null;

				userbean = new UserBean();
                userbean.init(Integer.parseInt(req.getParameter("key")));
                req.setAttribute("userbean", userbean);
                
                url = "/users/form_user.jsp";
                forward(req, res, url);         
            }
			//사용자 삭제 실행
            else if (cmd.equals("REMOVE")) {            
                UserBean userbean = null;

				userbean = new UserBean();
                userbean.init(Integer.parseInt(req.getParameter("key")));
                userbean.remove();           
                
                url = "/users/list_users.jsp";
                forward(req, res, url);          
            }
			//사용자 신규 추가 또는 수정 실행
            else if (cmd.equals("UPDATE")) {            
                UserBean userbean = null;
				int isAdmin = 0;
                
				userbean = new UserBean();
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
	            
	            if (req.getParameter("isAdmin").equals("1")) {
					isAdmin = 1;	
				}
	            userbean.setIsAdmin(isAdmin);	
	            userbean.setZipcode(req.getParameter("zipcode"));
	            userbean.setAddress1(req.getParameter("address1"));
	            userbean.setAddress2(req.getParameter("address2"));
	            userbean.setCompanyPhone(req.getParameter("companyPhone"));
	            userbean.setHomePhone(req.getParameter("homePhone"));	

            	boolean is_dup = false;
	            //사용자 정보 추가
	            if(userbean.getUserId() == 0) {		
		            if (userbean.isExist(userbean.getId()) == false) {
						userbean.add();
					}
					else {
						is_dup = true;
					}
	            }
	            //사용자 정보 수정
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
					req.setAttribute("msg", userbean.getId() + "는이미등록되어있습니다.");
				}
				else {
					req.setAttribute("msg", "사용자등록완료되었습니다.");
				}
            	
	            url =  "/users/form_user.jsp";            	
                forward(req, res, url);           
            }
        }
        catch(Exception e) {
            req.setAttribute("javax.servlet.jsp.jspException", e);
            forward(req, res, req.getContextPath() + "/error.jsp");
        }            
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

