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
 * 상품 검색 서블릿
 */
public class SearchServlet extends HttpServlet {
    /** 상품 검색 서블릿 init 메소드
     * @param config        ServletConfig     	
     */
	public void init(ServletConfig conf) throws ServletException  {
        super.init(conf);
    }

    /** 상품 검색 서블릿 service 메소드
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
            //세션객체 생성
			session = req.getSession(false);
            if (session == null) {
                res.sendRedirect(req.getContextPath() + "/MainServlet?cmd=LOGIN");
				return;
            }        
                          
            //HTTP request command parameter로 값을 얻음
			cmd = req.getParameter("cmd").toUpperCase();        
            
            //상품검색
			if (cmd.equals("SEARCH_LIST")) {
				ProductBean pb = null;
				ProductBean[] list = null;
				ListObject lo = null;

				pb = new ProductBean();
				lo = new ListObject();
				//상품명으로 검색할 경우
				if (req.getParameter("find") != null) {
					list = pb.findByProductName("%" + req.getParameter("find") + "%");		
				}
				//전체 상품명으로 검색
				else {
					list = pb.findAll();
				}
				lo.add(list);
				req.setAttribute("lo", lo);
                url = "/products/search.jsp";
                forward(req, res, url); 
            }
			//상품 상세
            else if (cmd.equals("PRODUCT_DETAIL")) {
                ProductBean pb = null;

				pb = new ProductBean();
                pb.init(Integer.parseInt(req.getParameter("key")));
                req.setAttribute("pb", pb);           
                
                url = "/products/view_product.jsp";
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

