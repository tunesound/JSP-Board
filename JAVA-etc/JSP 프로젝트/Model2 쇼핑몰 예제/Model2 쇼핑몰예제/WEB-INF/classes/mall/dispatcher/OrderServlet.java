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
 * 주문 처리 서블릿 
 */
public class OrderServlet extends HttpServlet {
    /** 주문 서블릿 init 메소드
     * @param config        ServletConfig     	
     */
	public void init(ServletConfig conf) throws ServletException {
        super.init(conf);
    }  
    
    /** 주문 서블릿 service 메소드
     * @param req           HttpServletRequest   
     * @param res           HttpServletResponse 
     */
	public void service(HttpServletRequest req, HttpServletResponse res)
        throws ServletException, IOException {        
        HttpSession session = null;
		UserBean user = null;
		String url = "";
		String cmd = "";
		try
        {
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
            
            //주문리스트
			if (cmd.equals("ORDER_LIST")) {
				OrderBean order = new OrderBean();
				OrderBean[] list = null;
				ListObject lo = new ListObject();
				//관리자인 경우
				if (user.getIsAdmin()==1) {
					//상태로 검색할 경우
					if (req.getParameter("find") != null) {
						if (req.getParameter("find").equals("3")) {
							list = order.findAll();		
						}
						else {
							list = order.findByStatus(Integer.parseInt(req.getParameter("find")));		
						}
					}
					//전체 주문을 검색할 경우
					else {
						list = order.findAll();		
					}
				}
				//일반 사용자인 경우
				else {
					//일반사용자의 해당 주문을 상태로 검색할 경우
					if (req.getParameter("find") != null) {
						if (req.getParameter("find").equals("3")) {
							list = order.findByUserId(user.getUserId());		
						}
						else {
							list = order.findByStatus(Integer.parseInt(req.getParameter("find")), user.getUserId());				
						}
					}
					//일반사용자의 전체 주문을 검색할 경우
					else {
						list = order.findByUserId(user.getUserId());	
					}
				}
				lo.add(list);
				req.setAttribute("lo", lo);				
                url = "/orders/list_orders.jsp";				
                forward(req, res, url);
            }
			//주문 상세
            else if (cmd.equals("ORDER_DETAIL")) {
                OrderBean order = null;
				UserBean userbean = null;
				ItemBean item = null;
				ListObject lo = null;
				ProductBean pb = null;
				ItemBean[] itemList = null;
				ProductBean[] pbList = null;

                order = new OrderBean();
				userbean = new UserBean();
				item = new ItemBean();
				order.init(Integer.parseInt(req.getParameter("key")));
				userbean.init(order.getUserId());
				itemList = item.findByOrderId(order.getOrderId());
				pbList = new ProductBean[itemList.length];
				for(int i=0; i<itemList.length; i++) {			
					pb = new ProductBean();
					pb.init(itemList[i].getProductId());
					pbList[i] = pb;
				}
                req.setAttribute("order", order);     
				req.setAttribute("userbean", userbean);
                
				lo = new ListObject();
				lo.add(itemList);
				req.setAttribute("loItem", lo);
				lo = new ListObject();
				lo.add(pbList);
				req.setAttribute("loPb", lo);
                url = "/orders/view_order.jsp";
                forward(req, res, url);
            }
			//주문확인 실행
            else if (cmd.equals("CONFIRM")) {
                OrderBean order = new OrderBean();
                order.init(Integer.parseInt(req.getParameter("key")));
	            //상태를 주문처리로 변경
	            order.setStatus(OrderBean.COMPLETED);
	            order.modify();	
                url = "/orders/list_orders.jsp";
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

