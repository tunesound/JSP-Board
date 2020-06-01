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
 * 상품 처리 서블릿
 */
public class ProductServlet extends HttpServlet {
    /** 상품 서블릿 init 메소드
     * @param config        ServletConfig     	
     */
	public void init(ServletConfig conf) throws ServletException  {
        super.init(conf);
    }    
  
    /** 상품 서블릿 service 메소드
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
            
            //상품리스트
			if (cmd.equals("PRODUCT_LIST")) {
                ProductBean pb = new ProductBean();
				ProductBean[] list = null;
				ListObject lo = null;

				lo = new ListObject();
				//상품명으로 검색할 경우
				if (req.getParameter("find") != null) {
					list = pb.findByProductName("%" + req.getParameter("find") + "%");
				}
				//전체 상품을 검색
				else {
					list = pb.findAll();	
				}
				lo.add(list);
				req.setAttribute("lo", lo);		
				url = "/products/list_products.jsp";
                forward(req, res, url); 
            }        
			//상품추가화면
            else if (cmd.equals("ADD")) {            
                ProductBean pb = null;    
				
				pb = new ProductBean();  
                req.setAttribute("pb", pb);
                
                url = "/products/form_product.jsp";
                forward(req, res, url);          
            }
			//상품추가화면
            else if (cmd.equals("MODIFY")) {            
                ProductBean pb = null;

				pb = new ProductBean();
                pb.init(Integer.parseInt(req.getParameter("key")));
                req.setAttribute("pb", pb);
                
                url = "/products/form_product.jsp";
                forward(req, res, url);           
            }
			//상품 삭제 실행
            else if (cmd.equals("REMOVE")) {            
                ProductBean pb = null;
                ProductBean[] list = null;
				ListObject lo = null;

				pb = new ProductBean();
				pb.init(Integer.parseInt(req.getParameter("key")));
                pb.remove();          			
				
				lo = new ListObject();
				//상품명으로 검색할 경우
				if (req.getParameter("find") != null) {
					list = pb.findByProductName("%" + req.getParameter("find") + "%");
				}
				//전체 상품을 검색
				else {
					list = pb.findAll();	
				}
				lo.add(list);
				req.setAttribute("lo", lo);				
				
				url = "/products/list_products.jsp";
                forward(req, res, url);           
            }
			//상품 신규 추가 또는 수정 실행
            else if (cmd.equals("UPDATE")) {            
                ProductBean pb = null;
                
				pb = new ProductBean(); 
                pb.setProductId(Integer.parseInt(req.getParameter("productId")));
	            pb.setProduct(req.getParameter("product"));
	            pb.setModel(req.getParameter("model"));
	            pb.setSeller(req.getParameter("seller"));
	            pb.setPrice(Double.parseDouble(req.getParameter("price")));
	            pb.setUnit(req.getParameter("unit"));
	            pb.setDeliveryTerm(Integer.parseInt(req.getParameter("deliveryTerm")));
	            pb.setStock(Integer.parseInt(req.getParameter("stock")));
	            pb.setImage(req.getParameter("image"));
	            pb.setStatus(Integer.parseInt(req.getParameter("status")));	
            		
	            //상품 정보 추가
	            if(pb.getProductId() == 0) {
		            pb.add();
				}	            
	            else {	//상품 정보 수정
		            pb.modify();
				}
            	
        	    pb.setProduct(Han.Ksc2Uni(pb.getProduct()));
        	    pb.setModel(Han.Ksc2Uni(pb.getModel()));
        	    pb.setSeller(Han.Ksc2Uni(pb.getSeller()));
        	    pb.setUnit(Han.Ksc2Uni(pb.getUnit()));
        	    pb.setImage(Han.Ksc2Uni(pb.getImage()));
            	
        	    req.setAttribute("pb", pb);
				req.setAttribute("msg", "상품등록완료되었습니다.");
            	
	            url =  "/products/form_product.jsp";
            	
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

